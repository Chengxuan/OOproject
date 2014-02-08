package ie.gmit.encode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Subclass of {@link AbstractEncoder}, use Lempel-Ziv algorithm, implements
 * {@link Encoder}.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */
public class LempelZivEncoder extends AbstractEncoder {
	private Map<Integer, Map<String, Integer>> dictionary;
	private int index;

	public LempelZivEncoder() {
		this.index = 0;
		this.dictionary = new HashMap<Integer, Map<String, Integer>>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ie.gmit.cloud.Encoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String input) throws EncodeException {
		String output = this.getOutput();
		try {
			Map<String, Integer> repeatMap = new HashMap<String, Integer>();
			this.dictionary.clear();// initialize the dictionary
			String buf = "";
			// get and add new words to the dictionary
			for (int i = 0; i < input.length(); i++) {
				// the current character is char
				if (Character.isLetter(input.charAt(i))) {
					boolean exist = false;
					String tmpChar = String.valueOf(input.charAt(i));
					buf += tmpChar;
					Set<Integer> key = this.dictionary.keySet();
					Iterator<Integer> it = key.iterator();
					while (it.hasNext()) {
						int j = it.next();
						Map<String, Integer> m = this.dictionary.get(j);
						if (m.containsKey(tmpChar)
								&& repeatMap.containsKey(buf)) {
							index = repeatMap.get(buf); // word exist
							exist = true;
						}
					}
					if (!exist) {
						int cIndex = this.dictionary.size() + 1;
						repeatMap.put(buf, cIndex);
						buf = "";
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put(tmpChar, index);
						// add the new char and its index into the dictionary
						this.dictionary.put(cIndex, map);
						// add the encoded string in hex format with
						// preprocessor 'C'
						output += "C" + Integer.toHexString(cIndex);
						index = 0;
					} else {
						if (i == input.length() - 1) {
							// add the encoded string in hex format with
							// preprocessor 'C'
							output += "C" + Integer.toHexString(index);
						}
					}
				} else {
					if (!buf.isEmpty()) {
						// add the encoded string in hex format with
						// preprocessor 'C'
						output += "C" + Integer.toHexString(index);
						index = 0;
						buf = "";
					}
					// if it's not letter, directly add them as a new word
					boolean exist = false;
					String tmpChar = String.valueOf(input.charAt(i));
					Set<Integer> key = this.dictionary.keySet();
					Iterator<Integer> it = key.iterator();
					while (it.hasNext()) {
						int j = it.next();
						Map<String, Integer> m = this.dictionary.get(j);
						if (m.containsKey(tmpChar)) {
							index = j;
							exist = true;
						}
					}
					if (!exist) {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put(tmpChar, 0);
						int cIndex = this.dictionary.size() + 1;
						this.dictionary.put(cIndex, map);
						// add the encoded string in hex format with
						// preprocessor 'C'
						output += "C" + Integer.toHexString(cIndex);
					} else {
						// add the encoded string in hex format with
						// preprocessor 'C'
						output += "C" + Integer.toHexString(index);
						index = 0;
					}

				}

			}
			String mapString = "";
			Set<Integer> key = this.dictionary.keySet();
			Iterator<Integer> it = key.iterator();
			while (it.hasNext()) {
				int j = it.next();
				Map<String, Integer> m = this.dictionary.get(j);
				Set<String> key1 = m.keySet();
				Iterator<String> it1 = key1.iterator();
				while (it1.hasNext()) {
					String s = it1.next();
					// add the dictionary words as a string with preprocessor
					// 'M' and 'c'
					mapString += "M" + s + m.get(s) + "c" + j;
				}

			}
			output += mapString;
		} catch (Exception e) {
			throw new EncodeException("LempelZiv LZ78 Encode Failed!", e);
		}

		return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ie.gmit.cloud.Encoder#decode(java.lang.String)
	 */
	@Override
	public String decode(String input) throws EncodeException {
		String output = this.getOutput();
		try {
			this.dictionary.clear();
			int cCount = 0, mCount = 0;
			int[] cPos = new int[input.length() / 2], mPos = new int[input
					.length() / 2];
			// get the position of preprocessor 'C' and 'M'
			for (int i = 0; i < input.length(); i++) {
				if (i == 0) {
					cPos[cCount++] = i;
				} else {
					if (input.charAt(i) == 'C' && input.charAt(i - 1) != 'M') {
						cPos[cCount++] = i;
					}
					if (input.charAt(i) == 'M' && input.charAt(i - 1) != 'M') {
						mPos[mCount++] = i + 1;
					}
				}
			}
			// get the dictionary
			for (int i = 0; i < mCount; i++) {
				String tmpChar = String.valueOf(input.charAt(mPos[i]));
				String indexString = "";
				if (i != mCount - 1) {
					indexString = input.substring(mPos[i] + 1, mPos[i + 1] - 1);
				} else {
					indexString = input.substring(mPos[i] + 1);
				}
				// get the index of the word it based on
				int prefix = Integer.parseInt(indexString.substring(0,
						indexString.indexOf('c')));
				// get the index of current word
				int index = Integer.parseInt(indexString.substring(indexString
						.indexOf('c') + 1));
				Map<String, Integer> m = new HashMap<String, Integer>();
				m.put(tmpChar, prefix);
				this.dictionary.put(index, m);
			}
			// convert the hex string to int string and interpret them using
			// the dictionary
			for (int i = 0; i < cCount; i++) {
				if (i != cCount - 1) {
					int a = Integer.parseInt(
							input.substring(cPos[i] + 1, cPos[i + 1]), 16);
					output += getString(a, new String());
				} else {
					int a = Integer.parseInt(
							input.substring(cPos[i] + 1, mPos[0] - 1), 16);
					output += getString(a, new String());
				}
			}
		} catch (Exception e) {
			throw new EncodeException("Lempel Ziv LZ78 Decode Failed!", e);
		}

		return output;
	}

	private String getString(int index, String buffer) {
		// recover the word
		Map<String, Integer> m = new HashMap<String, Integer>();
		if (this.dictionary.containsKey(index)) {
			m = this.dictionary.get(index);
			Set<String> key = m.keySet();
			Iterator<String> it = key.iterator();
			if (it.hasNext()) {
				String s = it.next();
				buffer = s + buffer;
				if (m.get(s) != 0) {// if this word is based on another word
					buffer = getString(m.get(s), buffer);
				}
			}
		} else {
			buffer = "*";
		}
		return buffer;
	}
}

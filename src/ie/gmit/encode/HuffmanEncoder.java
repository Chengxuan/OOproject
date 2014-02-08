package ie.gmit.encode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Subclass of {@link AbstractEncoder}, use Huffman algorithm, implements
 * {@link Encoder}.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */
public class HuffmanEncoder extends AbstractEncoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ie.gmit.cloud.Encoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String input) throws EncodeException {
		String output = this.getOutput();
		try {
			// Try to encode the string
			Map<String, Integer> weightMap = new HashMap<String, Integer>();
			// get each characters' frequency
			for (int i = 0; i < input.length(); i++) {
				String key = String.valueOf(input.charAt(i));
				if (weightMap.containsKey(key)) {
					int weight = weightMap.get(key);
					weight++;
					weightMap.remove(key);
					weightMap.put(key, weight);
				} else {
					weightMap.put(key, 1);
				}
			}
			// generate a HuffmanTree use the characters and their frequency
			HuffmanTree ht = new HuffmanTree(weightMap);
			// get the encode map
			Map<String, String> stringMap = ht.getHuffmanMap();
			String mapString = "";
			Set<String> key = stringMap.keySet();
			Iterator<String> it = key.iterator();
			int longest = 0;
			while (it.hasNext()) {
				String s = it.next();
				int tmpInt = s.length();
				if (tmpInt > longest) {
					longest = tmpInt;
				}
				// get the map with preprocessor 'C' in front of each value
				mapString += "C" + s + stringMap.get(s);
			}

			for (int i = 0; i < input.length(); i++) {
				// encoding the string using the encode map
				output += stringMap.get(String.valueOf(input.charAt(i)));
			}
			String dataString = "";
			// convert the binary string to hex string, four bits as one hex
			for (int i = 0; i < output.length();) {
				if (i + 4 < output.length()) {
					dataString += Integer.toHexString(Integer.parseInt(
							output.substring(i, i + 4), 2));
					i += 4;

				} else {
					if (i + 4 == output.length()) {
						dataString += Integer.toHexString(Integer.parseInt(
								output.substring(i), 2));
						i += 4;

					} else {
						// if string has a reminder string whose length is not 4
						// separate them by '-' and keep them as binary
						dataString += '-' + String.valueOf(output.charAt(i++));

					}
				}
			}
			// combine the dataString and mapString
			output = dataString.toLowerCase() + mapString;
		} catch (Exception e) {
			throw new EncodeException("Huffman Encode Failed!", e);
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
			int count = 0;
			int[] pos = new int[input.length() / 2];
			// get all the position of characters in encode map
			for (int i = 1; i < input.length(); i++) {
				if (input.charAt(i) == 'C' && input.charAt(i - 1) != 'C')
					// i is the position of preprocessor 'C'
					pos[count++] = i + 1;
			}

			output = input.substring(0, pos[0] - 1);
			String hexString = "", bitString = "";
			if (output.contains("-")) {
				// take out the hex string and remind binary string
				hexString = output.substring(0, output.indexOf('-'));
				bitString = output.substring(output.indexOf('-'));
				// recover the binary string by removing the '-'
				bitString = bitString.replaceAll("-", "");
			} else {
				// if there is no remind binary string
				hexString = output;
			}
			String tempString = "";

			for (int i = 0; i < hexString.length(); i++) {
				// transfer the hex string to binary string
				char tmp = output.charAt(i);
				switch (tmp) {
				case '0':
					tempString += "0000";
					break;
				case '1':
					tempString += "0001";
					break;
				case '2':
					tempString += "0010";
					break;
				case '3':
					tempString += "0011";
					break;
				case '4':
					tempString += "0100";
					break;
				case '5':
					tempString += "0101";
					break;
				case '6':
					tempString += "0110";
					break;
				case '7':
					tempString += "0111";
					break;
				case '8':
					tempString += "1000";
					break;
				case '9':
					tempString += "1001";
					break;
				case 'a':
					tempString += "1010";
					break;
				case 'b':
					tempString += "1011";
					break;
				case 'c':
					tempString += "1100";
					break;
				case 'd':
					tempString += "1101";
					break;
				case 'e':
					tempString += "1110";
					break;
				case 'f':
					tempString += "1111";
					break;
				}

			}
			// add the extra binary string
			output = tempString + bitString;
			// generate the encode map
			Map<String, String> stringMap = new HashMap<String, String>();
			for (int i = 0; i < count - 1; i++) {
				stringMap.put(input.substring(pos[i] + 1, pos[i + 1] - 1),
						String.valueOf(input.charAt(pos[i])));
			}
			// substring method different at the last one
			stringMap.put(input.substring(pos[count - 1] + 1),
					String.valueOf(input.charAt(pos[count - 1])));
			Set<String> key = stringMap.keySet();
			Iterator<String> it = key.iterator();
			int longest = 0;
			while (it.hasNext()) { // get the longest length of the encoded
									// binary string
				int tmpInt = it.next().length();
				if (tmpInt > longest) {
					longest = tmpInt;
				}
			}
			// decode the string
			for (int j = 0; j < output.length();) {
				for (int i = longest + j; i > j; i--) {
					if (i < output.length()) {
						if (stringMap.containsKey(output.substring(j, i))) {
							String remindString = output.substring(0, j);
							String readyString = output.substring(i);
							output = remindString
									+ stringMap.get(output.substring(j, i))
									+ readyString;
							j++;
							break;

						}
					} else {
						// the left string is not longer than the longest
						// encoded binary string
						if (stringMap.containsKey(output.substring(j))) {
							output = output.substring(0, j)
									+ output.substring(j).replaceFirst(
											output.substring(j),
											stringMap.get(output.substring(j)));
							j++;
							break;
						}
					}

				}
			}
		} catch (Exception e) {
			throw new EncodeException("Huffman Decode Failed!", e);
		}

		return output;
	}
}

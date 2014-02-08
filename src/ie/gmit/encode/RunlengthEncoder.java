package ie.gmit.encode;

/**
 * Subclass of {@link AbstractEncoder}, use Run-length algorithm, implements
 * {@link Encoder}.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */
public class RunlengthEncoder extends AbstractEncoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ie.gmit.cloud.Encoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String input) throws EncodeException {
		String output = this.getOutput();
		try {
			String tmp = "";
			int count = 1;
			// encode the string and use 'C' as preprocessor
			for (int i = 0; i < input.length(); i++) {
				if (i != 0) {
					tmp = String.valueOf(input.charAt(i - 1));

					if (tmp.equals(String.valueOf(input.charAt(i)))) {
						count++;
					} else {
						output += count + "C" + String.valueOf(input.charAt(i));
						count = 1;
					}
					if (i == input.length() - 1) {
						output += count;
					}
				} else {
					output = "C" + String.valueOf(input.charAt(i));
				}
			}
		} catch (Exception e) {
			throw new EncodeException("RunLength Encode Failed!", e);
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
			int[] pos = new int[input.length()];
			// get the position of preprocessor 'C'
			for (int i = 0; i < input.length(); i++) {
				if (i == 0) {
					pos[count++] = i + 1;
				} else {
					if (input.charAt(i) == 'C' && input.charAt(i - 1) != 'C')
						pos[count++] = i + 1;
				}
			}
			// decode the string
			for (int i = 0; i < count; i++) {
				if (i < count - 1) {
					for (int j = 0; j < Integer.parseInt(input.substring(
							pos[i] + 1, pos[i + 1] - 1)); j++) {
						output += String.valueOf(input.charAt(pos[i]));
					}
				} else {
					for (int j = 0; j < Integer.parseInt(input
							.substring(pos[i] + 1)); j++) {
						output += String.valueOf(input.charAt(pos[i]));
					}
				}
			}
		} catch (Exception e) {
			throw new EncodeException("RunLength Decode Failed!", e);
		}

		return output;
	}
}

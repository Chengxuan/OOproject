package ie.gmit.encode;

/**
 * A interface abstracted basic behaviors of an Encoder
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */
public interface Encoder {
	/**
	 * Encode a string using selecting Algorithm
	 * 
	 * @param input
	 *            The string need to be encoded.
	 * @return The encoded String.
	 * @throws Exception
	 */
	public String encode(String input) throws Exception;

	/**
	 * Decode a string using selecting Algorithm
	 * 
	 * @param input
	 *            The string need to be decoded.
	 * @return The decoded String.
	 * @throws Exception
	 */
	public String decode(String input) throws Exception;

}

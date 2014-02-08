package ie.gmit.encode;

/**
 * Factory for generating different algorithms' encoders. Can be call by<br/>
 * {@code EncoderFactory ef = EncoderFactory.getInstance();}<br/>
 * {@code Encoder en = ef.getEncoder("Huffman"); //return a Huffman Encoder that can process String only.}
 * <br/>
 * {@code en = ef.getFileEncoder("Huffman"); //return a Huffman Encoder that can process Files.}
 * <br/> 
 * {@code en = ef.getURLEncoder("Huffman"); //return a Huffman Encoder that can process URLs.}
 * <br/>
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */
public class EncoderFactory {
	private static EncoderFactory ef;

	// private constructor to prevent creating its instance by "new"
	private EncoderFactory() {
	}

	/**
	 * Get an instance of {@link EncoderFactory}.
	 * 
	 * @return An instance of {@link EncoderFactory}.
	 */
	public static synchronized EncoderFactory getInstance() {
		if (ef == null) { // check if ef is point to null
			synchronized (EncoderFactory.class) {// if not, double check
				if (ef == null) {
					// sign value with private constructor
					ef = new EncoderFactory();
				}
			}
		}
		return ef;
	}

	/**
	 * Get a specific algorithm {@link Encoder} by specific command.
	 * 
	 * @param command
	 *            A string use to generate specific {@link Encoder}.<br/>
	 *            "LempelZiv" for {@link LempelZivEncoder}<br/>
	 *            "Huffman" for {@link HuffmanEncoder}<br/>
	 *            "RunLength" for {@link RunlengthEncoder}<br/>
	 *            "Base64" for {@link Base64Encoder}
	 * @return A specific {@link Encoder}.
	 */
	public Encoder getEncoder(String command) throws CommandException {
		Encoder en = null;
		// give back instance depends on the command
		switch (command.toLowerCase()) {
		case "lempelziv":
			en = new LempelZivEncoder();
			break;
		case "huffman":
			en = new HuffmanEncoder();
			break;
		case "runlength":
			en = new RunlengthEncoder();
			break;
		case "base64":
			en = new Base64Encoder();
			break;
		default:
			throw new CommandException(
					"Can't find the Algorithm, Wrong command input");
		}
		return en;
	}

	/**
	 * Get a specific algorithm {@link FileEncoder} by specific command.
	 * 
	 * @param command
	 *            A string use to generate specific algorithm
	 *            {@link FileEncoder}.<br/>
	 *            "LempelZiv" for Lempel-Ziv LZ78 {@link FileEncoder}.<br/>
	 *            "Huffman" for Huffman {@link FileEncoder}.<br/>
	 *            "RunLength" for Run-Length {@link FileEncoder}.<br/>
	 *            "Base64" for Base64 {@link FileEncoder}.
	 * @return A specific {@link FileEncoder}.
	 */
	public Encoder getFileEncoder(String command) throws CommandException {
		Encoder en = null;
		en = new FileEncoder(ef.getEncoder(command)); // return a FileEncoder
		return en;
	}

	/**
	 * Get a specific algorithm {@link URLEncoder} by specific command.
	 * 
	 * @param command
	 *            A string use to generate specific algorithm {@link URLEncoder}
	 *            .<br/>
	 *            "LempelZiv" for Lempel-Ziv LZ78 {@link URLEncoder}.<br/>
	 *            "Huffman" for Huffman {@link URLEncoder}.<br/>
	 *            "RunLength" for Run-Length {@link URLEncoder}.<br/>
	 *            "Base64" for Base64 {@link URLEncoder}.
	 * @return A specific {@link FileEncoder}.
	 */
	public Encoder getURLEncoder(String command) throws CommandException {
		Encoder en = null;
		en = new URLEncoder(ef.getEncoder(command));// return a URLEncoder
		return en;
	}

}

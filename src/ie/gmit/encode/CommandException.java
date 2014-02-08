package ie.gmit.encode;

/**
 * Exception caused by User Command used in the {@link EncoderFactory}.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 */
public class CommandException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CommandException() {
		super();
	}

	/**
	 * Constructor passed in error message.
	 * 
	 * @param message
	 *            Message show the error.
	 */
	public CommandException(String message) {
		super(message);
	}

}

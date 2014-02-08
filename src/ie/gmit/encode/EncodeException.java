package ie.gmit.encode;

/**
 * Exception caused by Encode.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 */
public class EncodeException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public EncodeException() {
		super();
	}

	/**
	 * Constructor passed in error message and cause.
	 * 
	 * @param message
	 *            Message show the error.
	 * @param cause
	 *            The cause of this Exception.
	 */
	public EncodeException(String message, Throwable cause) {
		super(message, cause);
	}

}

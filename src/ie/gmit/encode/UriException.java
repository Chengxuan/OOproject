package ie.gmit.encode;

/**
 * Exception caused by wrong Uri.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 */
public class UriException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UriException() {
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
	public UriException(String message, Throwable cause) {
		super(message, cause);
	}

}
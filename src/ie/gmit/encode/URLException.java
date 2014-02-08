package ie.gmit.encode;

/**
 * Exception caused by wrong URL.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 */
public class URLException extends UriException {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public URLException() {
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
	public URLException(String message, Throwable cause) {
		super(message, cause);
	}
}

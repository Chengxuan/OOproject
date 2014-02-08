package ie.gmit.encode;

/**
 * Exception caused by wrong file path.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 */
public class FileException extends UriException {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public FileException() {
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
	public FileException(String message, Throwable cause) {
		super(message, cause);
	}

}

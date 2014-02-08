package ie.gmit.encode;

public abstract class UriEncoder extends AbstractEncoder {
	private Encoder en;

	/**
	 * Constructor
	 * 
	 * @param en
	 *            An instance of {@link Encoder}. Delegate encode and decode
	 *            methods from it.
	 */
	public UriEncoder(Encoder en) {
		this.en = en;
	}

	/**
	 * Encode the content of the given URI.
	 * 
	 * @param uri
	 *            The URI need to be encoded, will encode the content of this
	 *            URI.
	 * @return The string of encoded content.
	 * @throws Exception
	 */
	@Override
	public String encode(String uri) throws Exception {
		return en.encode(this.parseUri(uri));
	}

	/**
	 * Decode the content of the given URI.
	 * 
	 * @param uri
	 *            The URI need to be decoded, will decode the content of this
	 *            URI.
	 * @return The string of decoded content.
	 * @throws Exception
	 */
	@Override
	public String decode(String uri) throws Exception {
		return en.decode(this.parseUri(uri));
	}

	/**
	 * Parse the URI and get the content string of this URI.
	 * 
	 * @param uri
	 *            URI that need to be processing.
	 * @return The content of the URI as a String
	 * @throws UriException
	 */
	protected abstract String parseUri(String uri) throws UriException;

}

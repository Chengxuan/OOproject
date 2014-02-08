package ie.gmit.encode;

import org.apache.commons.codec.binary.Base64;

/**
 * Subclass of {@link AbstractEncoder}, use an Adapter, implements
 * {@link Encoder}. Reference: <a href=
 * "http://commons.apache.org/proper/commons-codec/apidocs/overview-summary.html"
 * >Apache Base64 API</a>.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * @see <a
 *      href="http://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/binary/Base64.html">Base64</a>
 */
public class Base64Encoder extends AbstractEncoder {
	private Base64 bs; // Use an Object of Apache Base64 class, this class is an
						// adapter

	/**
	 * Default Constructor, initialize property <i>bs</i> which is a instance of
	 * <a href=
	 * "http://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/binary/Base64.html"
	 * >Base64</a> from Apache.
	 */
	public Base64Encoder() {
		bs = new Base64(); // initialize the object
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ie.gmit.cloud.Encoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String input) throws EncodeException {
		String output = this.getOutput();
		try {
			// Try to encode the string using delegate method
			byte[] inputeBytes = input.getBytes();
			output = bs.encodeToString(inputeBytes);
		} catch (Exception e) {
			throw new EncodeException("Base64 Encode Failed!", e);
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
			// Try to decode the string using delegate method
			output = new String(bs.decode(input));
		} catch (Exception e) {
			throw new EncodeException("Base64 Decode Failed!", e);
		}
		return output;
	}

}

package ie.gmit.encode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Subclass of {@link AbstractEncoder}, implements {@link Encoder}. Can parse
 * string as URL.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */

public class URLEncoder extends UriEncoder {
	/**
	 * Constructor
	 * 
	 * @param en
	 *            An instance of {@link Encoder}. Delegate encode and decode
	 *            methods from it.
	 */
	public URLEncoder(Encoder en) {
		super(en);
	}

	@Override
	protected String parseUri(String address) throws URLException {
		String output = "";
		// get the content string of an URL
		BufferedReader in;
		try {
			URL url = new URL(address);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String temp;
			while ((temp = in.readLine()) != null) {
				output += temp;
			}
			in.close();
		} catch (IOException e) {
			throw new URLException("URL read failed!", e);
		}
		return output;
	}

}

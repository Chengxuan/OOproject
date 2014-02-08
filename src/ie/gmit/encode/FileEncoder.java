package ie.gmit.encode;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Subclass of {@link AbstractEncoder}, implements {@link Encoder}. Can parse
 * string as File path.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */

public class FileEncoder extends UriEncoder {

	/**
	 * Constructor
	 * 
	 * @param en
	 *            An instance of {@link Encoder}. Delegate encode and decode
	 *            methods from it.
	 */
	public FileEncoder(Encoder en) {
		super(en);
	}

	@Override
	protected String parseUri(String address) throws FileException {
		String output = "";
		Scanner s;
		try {
			// try to convert the string to a path
			Path path = Paths.get(address);
			s = new Scanner(path);
			s.useDelimiter(System.getProperty("line.separator"));
			// get the content of the file
			while (s.hasNext()) {
				output += s.next();
			}
			s.close();
		} catch (IOException e) {
			throw new FileException("File read failed!", e);
		}

		return output;
	}

}

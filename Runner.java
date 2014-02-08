import ie.gmit.encode.Encoder;
import ie.gmit.encode.EncoderFactory;

public class Runner {
	public static void main(String[] args) throws Exception {

		EncoderFactory ef = EncoderFactory.getInstance();
		String s = "aaaaaaaaaaaaaaaabbbbbbbbbbbbbbbb";
		System.out.println("Before encoding: " + s);
		Encoder en = ef.getEncoder("runlength"); // return a Runlength Encoder
													// that
													// can process String.

		System.out.print("After Runlength encoding: ");
		System.out.println(en.encode(s));

		en = ef.getEncoder("Huffman"); // return a huffman Encoder that can
										// process String.
		System.out.print("After Huffman encoding: ");
		System.out.println(en.encode(s));

		en = ef.getEncoder("lempelziv"); // return a Runlength Encoder
		// that
		// can process String.
		System.out.print("After LempelZiv LZ78 encoding: ");
		System.out.println(en.encode(s));
		en = ef.getEncoder("base64"); // return a Runlength Encoder
		// that
		// can process String.
		System.out.print("After Base64 encoding: ");
		System.out.println(en.encode(s));
		System.out.println();
		System.out.println();
		
		en = ef.getFileEncoder("Huffman"); // return a Huffman Encoder that can
											// // process Files.
		try {
			System.out.println("build.xml after huffman Encoding:");
			System.out.println(en.encode("build.xml"));
			System.out.println();
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		en = ef.getURLEncoder("Huffman"); // return a Huffman Encoder that can

		try {
			System.out.println("Google website after huffman Encoding:");
			System.out.println(en.encode("http://www.google.ie/"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}

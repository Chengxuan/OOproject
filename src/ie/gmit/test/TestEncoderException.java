package ie.gmit.test;

import ie.gmit.encode.EncodeException;
import ie.gmit.encode.Encoder;
import ie.gmit.encode.EncoderFactory;
import ie.gmit.encode.FileException;
import ie.gmit.encode.URLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEncoderException {
	private EncoderFactory ef = null;
	private Encoder en = null;

	@Before
	public void createFactory() {
		ef = EncoderFactory.getInstance();
	}

	@After
	public void destroyFactory() {
		en = null;
		ef = null;
	}

	@Test(expected = URLException.class)
	public void wrongURL() throws Exception {
		en = ef.getURLEncoder("Huffman");
		en.encode("wrong URL");
	}

	@Test(expected = FileException.class)
	public void wrongFilePath() throws Exception {
		en = ef.getFileEncoder("Huffman");
		en.encode("wrong File path");
	}

	@Test(expected = EncodeException.class)
	public void wrongDecodeString() throws Exception {
		en = ef.getEncoder("Huffman");
		en.decode("I am not a Huffman format encoded string");
	}
}

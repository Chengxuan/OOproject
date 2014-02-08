package ie.gmit.test;

import static org.junit.Assert.assertEquals;
import ie.gmit.encode.CommandException;
import ie.gmit.encode.Encoder;
import ie.gmit.encode.EncoderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFactoryCommand {
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

	@Test
	public void correctCommand() throws CommandException {
		en = ef.getEncoder("Huffman");
		assertEquals(en != null, true);
		en = ef.getEncoder("LempelZiv");
		assertEquals(en != null, true);
		en = ef.getEncoder("Runlength");
		assertEquals(en != null, true);
		en = ef.getEncoder("Base64");
		assertEquals(en != null, true);
	}

	@Test(expected = CommandException.class)
	public void wrongCommand() throws CommandException {
		en = ef.getEncoder("wrong");
	}
}

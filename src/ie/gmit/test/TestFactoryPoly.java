package ie.gmit.test;

import static org.junit.Assert.assertEquals;
import ie.gmit.encode.Base64Encoder;
import ie.gmit.encode.CommandException;
import ie.gmit.encode.Encoder;
import ie.gmit.encode.EncoderFactory;
import ie.gmit.encode.FileEncoder;
import ie.gmit.encode.HuffmanEncoder;
import ie.gmit.encode.LempelZivEncoder;
import ie.gmit.encode.RunlengthEncoder;
import ie.gmit.encode.URLEncoder;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestFactoryPoly {
	private EncoderFactory ef = null;
	private boolean match;
	private String cmd;

	@Before
	public void createFactory() {
		ef = EncoderFactory.getInstance();
	}

	@After
	public void destroyFactory() {
		ef = null;
	}

	public TestFactoryPoly(String cmd, boolean match) {
		this.cmd = cmd;
		this.match = match;
	}

	@Test
	public void verifyPolymorphysm() throws CommandException {
		// different command should get instance from different class
		Encoder en = ef.getEncoder(cmd);
		boolean equal;
		switch (cmd.toLowerCase()) {
		case "lempelziv":
			equal = en instanceof LempelZivEncoder;
			break;
		case "huffman":
			equal = en instanceof HuffmanEncoder;
			break;
		case "runlength":
			equal = en instanceof RunlengthEncoder;
			break;
		case "base64":
			equal = en instanceof Base64Encoder;
			break;
		default:
			throw new CommandException(
					"Can't find the Algorithm, Wrong command input");
		}
		assertEquals(equal, match);
		en = ef.getFileEncoder(cmd);
		equal = en instanceof FileEncoder;
		assertEquals(equal, match);
		en = ef.getURLEncoder(cmd);
		equal = en instanceof URLEncoder;
		assertEquals(equal, match);

	}

	@Parameters
	public static Collection<Object[]> regExValues() {
		return Arrays.asList(new Object[][] { { "huffman", true },
				{ "lempelziv", true }, { "base64", true },
				{ "runlength", true }, });
	}

}
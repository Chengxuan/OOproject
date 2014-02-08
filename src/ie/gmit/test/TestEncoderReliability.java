package ie.gmit.test;

import static org.junit.Assert.assertEquals;
import ie.gmit.encode.Encoder;
import ie.gmit.encode.EncoderFactory;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestEncoderReliability {
	private EncoderFactory ef = null;
	private Encoder en = null;
	private String cmd;
	private String originalString;

	@Before
	public void createFactory() {
		ef = EncoderFactory.getInstance();
	}

	@After
	public void destroyFactory() {
		en = null;
		ef = null;
	}

	public TestEncoderReliability(String cmd, String originalString) {
		this.cmd = cmd;
		this.originalString = originalString;
	}

	@Test
	public void strongReliability() throws Exception {
		// String shouldn't change after encode and decodeS
		en = ef.getEncoder(cmd);
		assertEquals(en.decode(en.encode(originalString))
				.equals(originalString), true);
	}

	@Parameters
	public static Collection<Object[]> regExValues() {
		return Arrays.asList(new Object[][] { { "huffman", "huffman String" },
				{ "lempelziv", "lempelziv String" },
				{ "base64", "base64 String" },
				{ "runlength", "runlength String" }, });
	}
}

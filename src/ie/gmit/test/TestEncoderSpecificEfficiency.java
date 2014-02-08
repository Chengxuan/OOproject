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
public class TestEncoderSpecificEfficiency {
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

	public TestEncoderSpecificEfficiency(String cmd, String originalString) {
		this.cmd = cmd;
		this.originalString = originalString;
	}

	@Test
	public void goodEfficiency() throws Exception {
		// there are three encode algorithm may reduce the length of the
		// original string after encoding under some specific codition
		// 1, huffman, short string when letters repeat a lot of times
		// 2, Runlenght, short string when same letter continues appear
		// 3, LempelZiv LZ78,short string when same word repeat a lot of times
		en = ef.getEncoder(cmd);
		assertEquals(
				en.encode(originalString).length() < originalString.length(),
				true);
	}

	@Parameters
	public static Collection<Object[]> regExValues() {
		return Arrays
				.asList(new Object[][] {
						{ "huffman",
								"asdasdasdasdasdasdasdasdasdasdsadasdsadsadsadsa" },
						{
								"lempelziv",
								"a as asd asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf" },
						{ "runlength", "aaaaaaaaaaaaaaaddddddddddddddddssssss" }, });
	}
}

package ie.gmit.encode;

/**
 * An abstract class of an encoder, implements {@link Encoder}, base class of
 * all other encoder classes.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */
public abstract class AbstractEncoder implements Encoder {
	private String output; // General string used to store and return output
							// data

	/**
	 * Get the current value of <i>output</i> property
	 * 
	 * @return An empty string to use.
	 */
	public String getOutput() {
		// give out a new string
		output = "";
		return output;
	}

}

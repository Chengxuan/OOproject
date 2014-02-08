package ie.gmit.encode;

/**
 * Defines {@link HuffmanLeaf} in a {@link HuffmanTree}, subclass of
 * {@link HuffmanUnit}.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */
public class HuffmanLeaf extends HuffmanUnit {
	private String value;// the value of the leaf

	/**
	 * Constructor of {@link HuffmanLeaf}.
	 * 
	 * @param weight
	 *            Used to set the value of <i>weight</i> property.
	 * @param value
	 *            Used to set the value of <i>value</i> property.
	 */
	public HuffmanLeaf(int weight, String value) {
		super(weight);
		this.value = value;
	}

	/**
	 * Get the current value of <i>value</i> property.
	 * 
	 * @return The current value of <i>value</i> property.
	 */
	public String getValue() {
		return value;
	}

}

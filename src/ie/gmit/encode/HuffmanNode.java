/**
 * 
 */
package ie.gmit.encode;

/**
 * Defines {@link HuffmanNode} in a {@link HuffmanTree}, subclass of
 * {@link HuffmanUnit}.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */
public class HuffmanNode extends HuffmanUnit {
	private HuffmanUnit leftNode, rightNode; // two children node

	/**
	 * Constructor of {@link HuffmanNode}
	 * 
	 * @param leftNode
	 *            Used to set the <i>leftNode</i> property of
	 *            {@link HuffmanNode}.
	 * @param rightNode
	 *            Used to set the <i>rightNode</i> property of
	 *            {@link HuffmanNode}.
	 */
	public HuffmanNode(HuffmanUnit leftNode, HuffmanUnit rightNode) {
		// set the weight as the sum of two node
		super(leftNode.getWeight() + rightNode.getWeight());
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get the current <i>leftNode</i>.
	 * 
	 * @return The current <i>leftNode</i>.
	 */
	public HuffmanUnit getLeftNode() {
		return leftNode;
	}

	/**
	 * Get the current <i>rightNode</i>.
	 * 
	 * @return The current <i>rightNode</i>.
	 */
	public HuffmanUnit getRightNode() {
		return rightNode;
	}

}

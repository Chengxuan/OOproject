package ie.gmit.encode;

/**
 * Abstract class of the Units in a {@link HuffmanTree}.
 * 
 * @version 1.0
 * @author Chengxuan Xing
 */
public abstract class HuffmanUnit implements Comparable<HuffmanUnit> {
	private int weight; // all huffman unit should have weight

	/**
	 * Constructor of HuffmanUnit class, initialize the value of <i>weight</i>.
	 * 
	 * @param weight
	 *            Used to set the value of <i>weight</i> property.
	 */
	public HuffmanUnit(int weight) {
		super();
		this.weight = weight;
	}

	/**
	 * Get the current value of the <i>weight</i> property. The <i>weight</i>
	 * property determines the priority of a {@link HuffmanUnit} object.
	 * 
	 * @return The current value of the <i>weight</i> property
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Set the value of the <i>weight</i> property. The <i>weight</i> property
	 * determines the priority of a {@link HuffmanUnit} object.
	 * 
	 * @param weight
	 *            The <i>weight</i> to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(HuffmanUnit unit) {
		// first compare the weight to decide the priority
		int dif = this.weight - unit.weight;
		if (dif == 0) {// if they have same weight
			if (unit instanceof HuffmanNode && this instanceof HuffmanNode) {
				// if they both are node, compare their depth
				HuffmanNode node = (HuffmanNode) unit;
				HuffmanNode thisNode = (HuffmanNode) this;
				int myDepth = this.getDepth(thisNode.getLeftNode(), 0)
						+ this.getDepth(thisNode.getRightNode(), 0);
				int compareDepth = this.getDepth(node.getLeftNode(), 0)
						+ this.getDepth(node.getRightNode(), 0);
				dif = compareDepth - myDepth;
			} else {
				if (this instanceof HuffmanNode) {
					// they other is a leaf, should let it firstly be chosen
					return 1;
				} else {
					return -1;
				}

			}

		}
		return dif;
	}

	private int getDepth(HuffmanUnit hu, int depth) {
		// get the depth of a huffman Node, leaf have a depth of 1
		if (hu instanceof HuffmanLeaf) {
			depth++;

		} else if (hu instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) hu;

			depth += getDepth(node.getLeftNode(), depth);
			depth += getDepth(node.getRightNode(), depth);
		}
		return depth;
	}

}

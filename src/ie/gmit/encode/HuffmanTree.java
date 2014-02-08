package ie.gmit.encode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * HuffmanTree used to store and sort {@link HuffmanUnit}(can either be
 * {@link HuffmanLeaf} or {@link HuffmanNode}).
 * 
 * @version 1.0
 * @author Chengxuan Xing
 * 
 */
public class HuffmanTree {
	private PriorityQueue<HuffmanUnit> pq; // priority queue to auto order the
											// units

	/**
	 * Constructor of {@link HuffmanTree}.
	 * 
	 * @param inputMap
	 *            A map that stores {@link HuffmanLeaf}'s weight and value;
	 */
	public HuffmanTree(Map<String, Integer> inputMap) {
		// put the input value and weight into a priority queue
		this.pq = new PriorityQueue<HuffmanUnit>();
		Set<String> key = inputMap.keySet();
		Iterator<String> it = key.iterator();
		while (it.hasNext()) {
			String s = it.next();
			this.pq.add(new HuffmanLeaf(inputMap.get(s), s));
		}
		// generate the optimize binary tree
		optimiseTree();

	}

	/**
	 * Get the Huffman Encoding Map of the HuffmanTree
	 * 
	 * @return A Huffman Encoding Map
	 */
	public Map<String, String> getHuffmanMap() {
		Map<String, String> hufMap = new HashMap<String, String>();
		// generate the encode map
		generateMap(this.pq.peek(), new String(), hufMap);
		return hufMap;
	}

	private void generateMap(HuffmanUnit hu, String prefix,
			Map<String, String> hufMap) {
		if (hu instanceof HuffmanLeaf) {
			// store the binary code into the map if it's a leaf
			HuffmanLeaf leaf = (HuffmanLeaf) hu;
			if (prefix.length() == 0) {
				prefix += "0"; // if the tree only have one unit
			}
			hufMap.put(leaf.getValue(), prefix.toString());

		} else if (hu instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) hu;
			// recurse method with left node and a index '0'
			prefix += "0";
			generateMap(node.getLeftNode(), prefix, hufMap);
			prefix = prefix.substring(0, prefix.length() - 1);
			// recurse method with left node and a index '1S'
			prefix += "1";
			generateMap(node.getRightNode(), prefix, hufMap);
			prefix = prefix.substring(0, prefix.length() - 1);
		}
	}

	private void optimiseTree() {
		while (this.pq.size() > 1) {
			// combine the unit until there's only one unit left
			HuffmanUnit left = this.pq.poll();
			HuffmanUnit right = this.pq.poll();
			this.pq.add(new HuffmanNode(left, right));
		}
	}

}

package ie.gmit.test;

import static org.junit.Assert.assertEquals;
import ie.gmit.encode.HuffmanTree;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

public class TestHuffmanTree {
	private HuffmanTree ht;

	@After
	public void destroyHuffmanTree() {
		ht = null;
	}

	public TestHuffmanTree() {

	}

	@Test
	public void onlyOneUnit() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 1);
		ht = new HuffmanTree(map);
		Map<String, String> encodeMap = ht.getHuffmanMap();
		System.out.println(encodeMap.get("a"));
		assertEquals(encodeMap.get("a").equals("0"), true);
	}

	@Test
	public void bestOptimisedTree() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 3);
		map.put("b", 2);
		map.put("c", 5);
		map.put("d", 6);
		map.put("e", 1);
		// if the compareTo method only judge weight but not also the depth of
		// existing node, the longest length of encoded string will be more than
		// 3, have done the calculating on a paper
		ht = new HuffmanTree(map);
		Map<String, String> encodeMap = ht.getHuffmanMap();
		assertEquals(encodeMap.get("a").length() < 4, true);
		assertEquals(encodeMap.get("b").length() < 4, true);
		assertEquals(encodeMap.get("c").length() < 4, true);
		assertEquals(encodeMap.get("d").length() < 4, true);
		assertEquals(encodeMap.get("e").length() < 4, true);
	}
}

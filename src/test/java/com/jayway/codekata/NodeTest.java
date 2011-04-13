package com.jayway.codekata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NodeTest {

	@Test
	public void testBecomeNeighbourTo() {
		Node cat = new Node("cat");
		cat.setDistance(0);
		Node cot = new Node("cot");

		cat.becomeNeighbourTo(cot);

		assertEquals(cot, cat.getNeighbours().get(0));
		assertEquals(cat, cot.getNeighbours().get(0));

		assertEquals(0, cat.getDistance());
		assertEquals(1, cot.getDistance());

	}

	@Test
	public void testIsNeighbour() throws Exception {
		Node node1 = new Node("cat");
		Node node2 = new Node("cot");
		assertTrue(node1.isNeighbour(node2));
	}

	@Test
	public void testDifferentSize() throws Exception {
		Node node1 = new Node("cat");
		Node node2 = new Node("cats");
		assertFalse(node1.isNeighbour(node2));
	}

}

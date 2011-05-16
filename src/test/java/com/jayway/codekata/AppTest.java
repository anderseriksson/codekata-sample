package com.jayway.codekata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

	private WordChainPathFinder app;

	@Before
	public void setup() {
		app = new App();
	}

	@Test
	public void testFindShortestPathOfMinimalDictionary() throws Exception {
		List<String> dictionary = Arrays.asList("cat", "cot");
		List<String> answer = Arrays.asList("cat", "cot");

		List<String> shortestPath = app.findShortestPath("cat", "cot", dictionary);
		assertEquals(answer, shortestPath);
	}

	@Test
	public void testFindShortestPath() throws Exception {
		List<String> dictionary = Arrays.asList("cat", "dog", "car", "hat", "cot", "cog");
		List<String> answer = Arrays.asList("cat", "cot", "cog", "dog");

		List<String> shortestPath = app.findShortestPath("cat", "dog", dictionary);
		assertEquals(answer, shortestPath);
	}

	@Test
	public void testLinkNodeInNodeGraph() throws Exception {
		Node cat = new Node("cat");
		Node dog = new Node("dog");
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(cat);
		nodes.add(dog);
		Node cot = new Node("cot");
		App.linkUpNodeInNodeGraph(nodes, cot);
		assertEquals(cat.getNeighbours().get(0), cot);
		assertEquals(cot.getNeighbours().get(0), cat);
		assertTrue(nodes.contains(cot));

	}

	@Test
	public void testApplyDistance() throws Exception {
		Node cat = new Node("cat");
		Node cot = new Node("cot");
		Node cog = new Node("cog");
		Node dog = new Node("dog");
		cat.becomeNeighbourTo(cot);
		cot.becomeNeighbourTo(cog);
		cog.becomeNeighbourTo(dog);

		App.applyDistance(cat, 0, null);

		assertEquals(0, cat.getDistance());
		assertNull(cat.getFromNode());
		assertEquals(1, cot.getDistance());
		assertEquals(cat, cot.getFromNode());
		assertEquals(2, cog.getDistance());
		assertEquals(cot, cog.getFromNode());
		assertEquals(3, dog.getDistance());
		assertEquals(cog, dog.getFromNode());

	}

}

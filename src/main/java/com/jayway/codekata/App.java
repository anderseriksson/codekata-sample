package com.jayway.codekata;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Files;

/**
 * Hello words! ;)
 * 
 * This is an implementation of the Code Kata Nineteen - Word Chains
 * http://codekata.pragprog.com/2007/01/kata_nineteen_w.html
 * 
 */
public class App implements WordChainPathFinder {
	public static void main(String[] args) throws IOException {
		WordChainPathFinder app = new App();
		List<String> words = getWordsFromFile();
		System.out.println(words.size());
		List<String> shortestPath = app.findShortestPath("cat", "dig", words);
		System.out.println(shortestPath);
	}

	private static List<String> getWordsFromFile() throws IOException {
		List<String> words = Files.readLines(new File("english.txt"), Charset.forName("ISO-8859-1"));
		return words;
	}

	/* (non-Javadoc)
	 * @see com.jayway.codekata.WordChainPathFinder#findShortestPath(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public List<String> findShortestPath(String start, String end, List<String> dictionary) {

		Node startNode = new Node(start);
		Node endNode = new Node(end);

		List<Node> processedNodes = new ArrayList<Node>();

		linkUpNodeInNodeGraph(processedNodes, startNode);
		linkUpNodeInNodeGraph(processedNodes, endNode);

		for (String word : dictionary) {
			if (word.length() != start.length() || word.equals(startNode) || word.equals(endNode)) {
				continue;
			}
			Node node = new Node(word);
			linkUpNodeInNodeGraph(processedNodes, node);
		}

		if ((startNode.hasNeighbours()) && (endNode.hasNeighbours())) {
			applyDistance(startNode, 0, null);

			if (endNode.getDistance() < Integer.MAX_VALUE) {
				List<String> response = new ArrayList<String>();
				Node currentNode = endNode;
				while (!currentNode.equals(startNode)) {
					response.add(0, currentNode.getWord());
					currentNode = currentNode.getFromNode();
				}
				response.add(0, startNode.getWord());
				return response;
			}
		}
		return null;
	}

	static void linkUpNodeInNodeGraph(List<Node> processedNodes, Node node) {
		for (Node processedNode : processedNodes) {
			if (processedNode.isNeighbour(node)) {
				processedNode.becomeNeighbourTo(node);
			}
		}
		processedNodes.add(node);
	}

	static void applyDistance(Node node, int distance, Node fromNode) {
		if (node.getDistance() > distance) {
			node.setDistance(distance);
			node.setFromNode(fromNode);
			for (Node neighbour : node.getNeighbours()) {
				applyDistance(neighbour, distance + 1, node);
			}
		}
	}

}

package com.jayway.codekata.impl1;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private final String word;
	private int distance = 0;
	private final List<Node> neighbours = new ArrayList<Node>();
	private Node backNode = null;

	public Node(String word) {
		this.word = word;
		distance = Integer.MAX_VALUE;
	}

	public boolean isNeighbour(Node node2) {
		if (word.length() != node2.word.length()) {
			return false;
		}
		int distance = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != node2.word.charAt(i)) {
				distance++;
			}
		}
		return distance == 1;
	}

	public void becomeNeighbourTo(Node otherNode) {
		neighbours.add(otherNode);
		otherNode.neighbours.add(this);
		if (otherNode.distance < this.distance) {
			this.distance = otherNode.distance + 1;
		} else if (otherNode.distance > this.distance) {
			otherNode.distance = this.distance + 1;
		}
	}

	public boolean hasNeighbours() {
		return !neighbours.isEmpty();
	}

	public List<Node> getNeighbours() {
		return neighbours;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setFromNode(Node fromNode) {
		this.backNode = fromNode;
	}

	public Node getFromNode() {
		return backNode;
	}

	public String getWord() {
		return word;
	}

}

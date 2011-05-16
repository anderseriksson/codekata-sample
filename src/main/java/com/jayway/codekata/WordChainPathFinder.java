package com.jayway.codekata;

import java.util.List;

public interface WordChainPathFinder {

	public abstract List<String> findShortestPath(String start, String end, List<String> dictionary);

}
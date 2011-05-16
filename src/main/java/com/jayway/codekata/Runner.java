package com.jayway.codekata;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.io.Files;
import com.jayway.codekata.impl1.App;

public class Runner {

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


}

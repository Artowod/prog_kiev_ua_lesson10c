package ua.prog.java.lesson10c;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsciiArt {
	private List<String> charInArt;
	private String dataPath;
	private Map<String, List<String>> charAsciiArtDependency = new HashMap<>();
	private List<String> allDataFromFile = new ArrayList<>();

	public AsciiArt(String dataPath) {
		super();
		this.dataPath = dataPath;
		readAllDataFromFile();
		createAllCharsAsciiArtDependency();
	}

	public AsciiArt() {

	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public Map<String, List<String>> getCharAsciiArtDependency() {
		return charAsciiArtDependency;
	}

	public List<String> getAllDataFromFile() {
		return allDataFromFile;
	}

	private void readAllDataFromFile() {
		String lineFromFile;
		try (BufferedReader bufReader = new BufferedReader(new FileReader(dataPath))) {
			for (; (lineFromFile = bufReader.readLine()) != null;) {
				allDataFromFile.add(lineFromFile);
			}
		} catch (IOException ex) {
			System.out.println("IO Error in readAllDataFromFile method.");
		}
	}

	private boolean isKeyString(String checkedLine) {
		if (!checkedLine.equals("*") && checkedLine.length() == 1) {
			return true;
		} else {
			return false;
		}
	}

	private List<String> createCharLines(String currentKey, int keyPosition) {
		List<String> charLines = new ArrayList<>();
		for (int j = keyPosition + 1; j < allDataFromFile.size() && !isKeyString(allDataFromFile.get(j)); j++) {
			charLines.add(allDataFromFile.get(j));
		}
		return charLines;
	}

	private void createAllCharsAsciiArtDependency() {
		for (int i = 0; i < allDataFromFile.size(); i++) {
			String currentLine = allDataFromFile.get(i);
			if (isKeyString(currentLine)) {
				charAsciiArtDependency.put(currentLine, createCharLines(currentLine, i));
			}
		}
	}

	public List<String> getCharInAsciiArt(String convertedCharacter) {
		charInArt = charAsciiArtDependency.get(convertedCharacter);
		for (String ff : charInArt) {
			System.out.println(ff);
		}
		return charInArt;
	}

	public void getSentenceInAsciiArt(String convertedSentence) {
		List<List<String>> allCharactersList = new ArrayList<>();
		for (char eachCharacter : convertedSentence.toCharArray()) {
			charInArt = charAsciiArtDependency.get(String.valueOf(eachCharacter));
			allCharactersList.add(charInArt);
		}

		String[] sentenceInAsciiArt = new String[10];
		for (int i = 0; i < 10; i++) {
			String concatAllCharsByLine = "";
			for (int j = 0; j < convertedSentence.length(); j++) {
				concatAllCharsByLine += allCharactersList.get(j).get(i) + " ";
			}
			sentenceInAsciiArt[i] = concatAllCharsByLine;
		}

		for (String eachSentenceLine : sentenceInAsciiArt) {
			System.out.println(eachSentenceLine);
		}
	}
}

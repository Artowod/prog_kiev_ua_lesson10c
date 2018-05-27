package ua.prog.java.lesson10c;

public class Main {

	public static void main(String[] args) {

		AsciiArt charOne = new AsciiArt("C:/Java/Lesson10/asciiart.txt/");
		charOne.getCharInAsciiArt("Q");
		charOne.getSentenceInAsciiArt("You LIKE Java!.. Don`t you?");
	}
}
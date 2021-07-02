package com.teguh.sejarahislam.model;


import com.teguh.sejarahislam.themes.Theme;

/**
 * This is instance of active playing game
 * 
 * @author sromku
 */
public class Game {

	/**
	 * The board configuration
	 */
	public BoardConfiguration boardConfiguration;

	/**
	 * The selected theme
	 */
	public Theme theme;

	public GameState gameState;

	public  int id;

	public static String[] keys;

	public String textQuestion;

	public String textAnswer;

	public int maxPresCounter;

	public int presCounter = 0;


	public String getTextQuestion() {
		return textQuestion;
	}

	public void setTextQuestion(String textQuestion) {
		this.textQuestion = textQuestion;
	}

	public String getTextAnswer() {
		return textAnswer;
	}

	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}

	public int getMaxPresCounter() {
		return maxPresCounter;
	}

	public void setMaxPresCounter(int maxPresCounter) {
		this.maxPresCounter = maxPresCounter;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}
}

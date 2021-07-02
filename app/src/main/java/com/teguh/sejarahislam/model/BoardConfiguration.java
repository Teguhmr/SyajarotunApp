package com.teguh.sejarahislam.model;

public class BoardConfiguration {

	public int difficulty;
	public final int time;

	public BoardConfiguration(int difficulty) {
		this.difficulty = difficulty;
		switch (difficulty) {
		case 1:
			time = 30;
			break;
		case 2:
			time = 40;
			break;
		case 3:
			time = 50;
			break;
		case 4:
			time = 60;
			break;
		default:
			throw new IllegalArgumentException("Select one of predefined sizes");
		}
	}

}

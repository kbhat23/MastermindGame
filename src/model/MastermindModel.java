package model;

import java.util.HashMap;
import java.util.Random;

/**
 * 
 * @author Kaushal Bhat
 * 
 *         File: MastermindModel.java
 * 
 *         Purpose: model component of MVC of mastermind game. This contains
 *         functions:
 * 
 *         constructor to build the correct answer (either accepted through an
 *         argument or in the case of no argument, is randomly generated.
 * 
 *         Get the color at an index of the answer. (used when comparing guesses
 *         to answer)
 *
 */
public class MastermindModel {
	private String answer;

	/**
	 * Constructor for MastermindModel that sets a random 4-color string as the
	 * answer.
	 * 
	 * Makes a HashMap to map each color to an integer between [0,5], then selects 4
	 * integers randomly, then uses the map to convert those numbers into a string
	 * of 4 chars, each representing a color.
	 */
	public MastermindModel() {
		String answer = "";
		HashMap<Integer, Character> numToColor = new HashMap<Integer, Character>();
		numToColor.put(0, 'r');
		numToColor.put(1, 'o');
		numToColor.put(2, 'y');
		numToColor.put(3, 'g');
		numToColor.put(4, 'b');
		numToColor.put(5, 'p');
		Random r = new Random();
		for (int i = 0; i < 4; i++) {

			answer += numToColor.get(r.nextInt(6));

		}
		this.answer = answer;

	}

	/**
	 * Special constructor for testing; sets a solution based on parameter.
	 * 
	 * Just sets the parameter as the answer field.
	 * 
	 * 
	 * @param answer A string that represents the four color solution
	 */
	public MastermindModel(String answer) {
		this.answer = answer;

	}

	/**
	 * Returns the color at the position index
	 * 
	 * Answer is a string, so just returns the char at index index in the answer
	 * string.
	 * 
	 * @param index an integer, the index at which we want to get the color.
	 * @return Returns the color at position index as a char.
	 * 
	 * 
	 * 
	 */
	public char getColorAt(int index) {

		return answer.charAt(index);

	}

}

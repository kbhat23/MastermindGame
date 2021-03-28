package controller;

import java.util.Arrays;
import java.util.HashSet;

import model.MastermindModel;

/**
 * 
 * @author Kaushal Bhat
 * 
 *         File: MastermindController.java
 * 
 *         Purpose: Controller component of MVC for mastermind game. This
 *         contains a function to determine if a guess is correct and functions
 *         to determine the number of correct colors that are in the correct
 *         spot and number of correct colors that are in the wrong spot.
 *
 */
public class MastermindController {

	private MastermindModel model;
	private HashSet<Character> validColors = new HashSet<Character>(Arrays.asList('r', 'o', 'y', 'g', 'b', 'p'));

	/**
	 * Constructor for MastermindController. Takes model as parameter.
	 * 
	 * @param model the MastermindModel object from which to build the
	 *              MastermindController object.
	 * 
	 *              Takes a MastermindModel object and constructs a
	 *              MastermindController class. All this does is take the model as a
	 *              private field in this function.
	 */
	public MastermindController(MastermindModel model) {

		this.model = model;

	}

	/**
	 * Checks if the guess is correct. 
	 * 
	 * Checks by looping through each char of the
	 * guess string and comparing to each char of the correct string.
	 * 
	 * @param guess the guess String to evaluate.
	 * @return true if the guess is correct, false otherwise
	 * @throws MastermindIllegalLengthException if the length of guess is not 4
	 * @throws MastermindIllegalColorException if one of the colors in guess is not valid
	 */
	public boolean isCorrect(String guess) throws MastermindIllegalLengthException, MastermindIllegalColorException {

		if (guess.length() != 4) {
			throw new MastermindIllegalLengthException("Guess is length " + guess.length() + "; should be length 4.");
		}

		for (int i = 0; i < 4; i++) {
			if (!validColors.contains(guess.charAt(i))) {
				throw new MastermindIllegalColorException("Color '" + guess.charAt(i) + "' is not valid.");
			}
			if (guess.charAt(i) != model.getColorAt(i)) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Returns number of correct color in correct place.
	 * 
	 * Does this by looping through and comparing each char of the guess string
	 * individually with each char of the correct string and counting the ones
	 * that match.
	 * 
	 * @param guess the guess String to evaluate.
	 * @return the number of correct color in correct place.
	 * @throws MastermindIllegalLengthException if the length of guess is not 4
	 * @throws MastermindIllegalColorException if one of the colors in guess is not valid
	 */
	public int getRightColorRightPlace(String guess)
			throws MastermindIllegalLengthException, MastermindIllegalColorException {

		if (guess.length() != 4) {
			throw new MastermindIllegalLengthException("Guess is length " + guess.length() + "; should be length 4.");
		}

		int result = 0;
		for (int i = 0; i < 4; i++) {

			if (!validColors.contains(guess.charAt(i))) {
				throw new MastermindIllegalColorException("Color '" + guess.charAt(i) + "' is not valid.");
			}

			if (guess.charAt(i) == model.getColorAt(i)) {
				result++;
			}
		}

		return result;
	}

	/**
	 * Returns the number of correct color in wrong place.
	 * 
	 * Returns the number of correct colors that are in the wrong place in
	 * the guess string (as compared to the answer string). Does this by
	 * excluding correct colors in correct place, and then seeing if there
	 * are any other matches (regardless of position) and counting them up.
	 * 
	 * @param guess the guess String to evaluate.
	 * @return the number of correct color in wrong place.
	 * @throws MastermindIllegalLengthException if the length of guess is not 4
	 * @throws MastermindIllegalColorException if one of the colors in guess is not valid
	 */
	public int getRightColorWrongPlace(String guess)
			throws MastermindIllegalColorException, MastermindIllegalLengthException {

		if (guess.length() != 4) {
			throw new MastermindIllegalLengthException("Guess is length " + guess.length() + "; should be length 4.");
		}

		int result = 0;

		StringBuilder guessWrongPart = new StringBuilder("");
		StringBuilder answerWrongPart = new StringBuilder("");

		// This loop gets rid of correct colors in correct place so we can examine
		// just the incorrect part.
		for (int i = 0; i < 4; i++) {

			if (!validColors.contains(guess.charAt(i))) {
				throw new MastermindIllegalColorException("Color '" + guess.charAt(i) + "' is not valid.");
			}

			if (guess.charAt(i) != model.getColorAt(i)) {
				guessWrongPart.append(guess.charAt(i));
				answerWrongPart.append(model.getColorAt(i));
			}
		}

		// For each letter of guess, we look for a match in
		// the answer. If match is found, we increment count,
		// remove that letter from answer (to avoid double-counting), and check the
		// next letter of guess.
		for (int i = 0; i < guessWrongPart.length(); i++) {
			for (int j = 0; j < answerWrongPart.length(); j++) {

				if (guessWrongPart.charAt(i) == answerWrongPart.charAt(j)) {
					result++;
					answerWrongPart.deleteCharAt(j);

					break;
				}
			}
		}

		return result;
	}

}

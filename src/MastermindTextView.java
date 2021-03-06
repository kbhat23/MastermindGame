import java.util.Scanner;

import controller.MastermindController;
import controller.MastermindIllegalColorException;
import controller.MastermindIllegalLengthException;
import model.MastermindModel;

/**
 * @author kaushalbhat
 * 
 *         File: MastermindTextView.java
 * 
 *         Purpose: Contains the text-based view component of the MVC of the
 *         Mastermind game. User must enter guesses as a 4-letter string, each
 *         letter representing a color. User has 10 guesses to guess the correct
 *         string, and with each guess is provided the number of colors that
 *         are: right color right place right color wrong place The colors to
 *         choose from are r, o, y, g, b, p.
 *
 */
public class MastermindTextView {

	/**
	 * The text view method of the Mastermind Game.
	 */
	public static void runTextView() {
		System.out.println("*******\nWelcome to Mastermind!\n*******\n");

		// Scanner that will read keyboard input for the whole game.
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Would you like to play? ");
		String response = keyboard.next();
		while (response.equals("yes")) {

			// Model and controller are constructed. Enter a string into the
			// model construction argument to use a predefined rather than random
			// color string as the correct answer.
			MastermindModel model = new MastermindModel();
			MastermindController controller = new MastermindController(model);

			int guessesUsed = 0;
			boolean playerWon = false;

			// Guessing loop runs until user guesses 10 times or guesses correctly
			while (guessesUsed < 10) {

				System.out.print("\nEnter guess number " + (guessesUsed + 1) + ": ");
				String guess = keyboard.next();

				// If correct, exits loop.
				try {
					if (controller.isCorrect(guess)) {
						playerWon = true;
						break;
					}

					// If wrong, prints information and loops back.
					else {

						int numRightPlace = controller.getRightColorRightPlace(guess);
						int numWrongPlace = controller.getRightColorWrongPlace(guess);
						System.out.println("Right color, right place: " + numRightPlace);
						System.out.println("Right color, wrong place: " + numWrongPlace);

						guessesUsed++;
					}
				} catch (MastermindIllegalLengthException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					System.out.println("Please try again.");

				} catch (MastermindIllegalColorException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					System.out.println("Please try again. (remember: acceptable colors include r,o,y,g,b,p)");

				}

			}

			// Decides if player won and prints according message, then restarts program to
			// ask if user would like to play again.
			if (playerWon) {
				System.out.println("\n\nYou won! Great job!\n\n");
			} else {
				System.out.println("\n\nYou lost! You suck at this!\n\n");
			}

			System.out.print("Would you like to play again? ");
			response = keyboard.next();

		}

		// User doesn't want to play:
		System.out.println("Goodbye!");
		keyboard.close();

	}

}

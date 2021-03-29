import controller.MastermindController;
import controller.MastermindIllegalColorException;
import controller.MastermindIllegalLengthException;
import javafx.application.Application;
import model.MastermindModel;

/**
 * 
 * @author Kaushal Bhat
 * 
 *         File: Mastermind.java
 * 
 *         Purpose: The View component (and contains main function) of the MVC
 *         of the Mastermind game. Computer generates a 4-color string (for
 *         example "goyb" for green orange yellow blue. The user has 10 guesses
 *         to guess the correct string. For each guess, if the guess is wrong,
 *         the user is told how many colors are correct and in the right place,
 *         and how many are correct but in the wrong place. The game ends when
 *         the user guesses correctly (and wins) or runs out of guesses (and
 *         loses). The user is then asked if they want to play again.
 * 
 *         If the command line argument is "-text", the game is launched in the
 *         console in a text-only mode. If the command line argument is
 *         "-window", the game is launched in a GUI window. If no command line
 *         argument is specified or it is something else, the default is the GUI
 *         window.
 *
 */
public class Mastermind {

	/**
	 * The main view method of the Mastermind Game Functionality described in file
	 * header comment.
	 * 
	 * @param args command line arguments; none necessary for this program
	 */
	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("-text")) {
			MastermindTextView.runTextView();
		} else {
			Application.launch(MastermindGUIView.class, args);
		}
	}
}
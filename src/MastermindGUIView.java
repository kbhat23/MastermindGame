import java.util.HashMap;
import controller.MastermindController;
import controller.MastermindIllegalColorException;
import controller.MastermindIllegalLengthException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.MastermindModel;

public class MastermindGUIView extends Application {
	private BorderPane window = new BorderPane();
	private Button guessBtn = new Button("Guess");
	private VBox centerBox = new VBox();
	private GridPane bottomPane = new GridPane();
	private Circle[] guessChoices = new Circle[4];
	private int numGuesses = 0;
	private HashMap<Integer, Color> colorMap = new HashMap<Integer, Color>();
	private HashMap<Integer, Character> colorCharMap = new HashMap<Integer, Character>();
	private HashMap<Circle, Integer> curGuessColors = new HashMap<Circle, Integer>();
	private int RCRP, RCWP;
	MastermindModel model;
	MastermindController controller;

	@Override
	public void start(Stage stage) throws Exception {
		model = new MastermindModel();
		controller = new MastermindController(model);
		setupBackground();
		setupColors();
		setupBottomPane();

		Scene scene = new Scene(window, 400, 600);
		stage.setScene(scene);
		stage.setTitle("Mastermind");
		stage.show();
	}

	private void setupColors() {
		colorMap.put(-1, Color.BLACK);
		colorMap.put(0, Color.RED);
		colorMap.put(1, Color.ORANGE);
		colorMap.put(2, Color.YELLOW);
		colorMap.put(3, Color.GREEN);
		colorMap.put(4, Color.BLUE);
		colorMap.put(5, Color.PURPLE);
		colorCharMap.put(0, 'r');
		colorCharMap.put(1, 'o');
		colorCharMap.put(2, 'y');
		colorCharMap.put(3, 'g');
		colorCharMap.put(4, 'b');
		colorCharMap.put(5, 'p');
	}

	private void setupBackground() {
		window.setCenter(centerBox);

		BackgroundFill bgfill = new BackgroundFill(Color.TAN, null, null);
		centerBox.setBackground(new Background(bgfill));
		centerBox.setPadding(new Insets(10));
		centerBox.setSpacing(10);
	}

	private void setupBottomPane() {
		window.setBottom(bottomPane);

		// Setting up button and its functionality
		guessBtn.setOnAction((event) -> {
			processGuess();
		});

		// Making and adding four guess circles
		for (int i = 0; i < 4; i++) {
			guessChoices[i] = new Circle(20);
			curGuessColors.put(guessChoices[i], -1);
			Circle tmpCircle = guessChoices[i];
			tmpCircle.setOnMouseClicked((event) -> {
				cycleColor(tmpCircle);
			});
		}
		bottomPane.addRow(0, guessChoices[0], guessChoices[1], guessChoices[2], guessChoices[3], guessBtn);

		// Padding and spacing
		bottomPane.setPadding(new Insets(5, 10, 5, 30));
		ColumnConstraints bottomConstraints = new ColumnConstraints();
		bottomConstraints.setPercentWidth(20);
		for (int i = 0; i < 5; i++) {
			bottomPane.getColumnConstraints().add(bottomConstraints);
		}
	}

	private void processGuess() {
		if (guessIsValid()) {
			makeNewGuessRow();
			if (guessIsCorrect()) {
				endGameWithAlert("You won!");
			} else if (numGuesses >= 10) {
				endGameWithAlert("You lost!");
			}
		} else {
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setTitle("Incomplete Guess");
			a.setHeaderText("Incomplete Guess");
			a.setContentText("You must choose all 4 colors to proceed.");
			a.showAndWait();
		}
		resetGuessCircles();
	}

	private void endGameWithAlert(String string) {
		window.getChildren().remove(bottomPane);
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setTitle("Message");
		a.setHeaderText("Message");
		a.setContentText(string);
		a.showAndWait();
	}

	private boolean guessIsCorrect() {
		String guess = getGuessAsString();
		try {
			return controller.isCorrect(guess);
		} catch (MastermindIllegalLengthException | MastermindIllegalColorException e) {
			e.printStackTrace();
		}
		return false;
	}

	private String getGuessAsString() {
		String guess = "";
		for (Circle circle : guessChoices) {
			guess += colorCharMap.get(curGuessColors.get(circle));
		}

		return guess;
	}

	private boolean guessIsValid() {

		for (Circle circle : guessChoices) {
			int color = curGuessColors.get(circle);
			if (color < 0)
				return false;
		}
		return true;
	}

	private void cycleColor(Circle circle) {
		int curColor = curGuessColors.get(circle);

		if (curColor >= 5) {
			curColor = 0;
		} else {
			curColor++;
		}
		curGuessColors.put(circle, curColor);
		circle.setFill(colorMap.get(curColor));
	}

	private void resetGuessCircles() {
		for (Circle circle : guessChoices) {
			curGuessColors.put(circle, -1);
			circle.setFill(Color.BLACK);
		}
	}

	private void makeNewGuessRow() {
		numGuesses++;

		// Making the guess row
		GridPane guessRow = new GridPane();
		centerBox.getChildren().add(guessRow);

		// Making 4 circles with the guessed color
		Circle[] curGuess = new Circle[4];
		for (int i = 0; i < 4; i++) {
			curGuess[i] = new Circle(20, guessChoices[i].getFill());
		}

		// Making the GridPane with guess statistics
		GridPane guessStats = makeGuessStats();

		// Adding everything to the new row
		Text guessNum = new Text(Integer.toString(numGuesses));
		guessNum.setFont(new Font(20));
		guessRow.addRow(0, guessNum, curGuess[0], curGuess[1], curGuess[2], curGuess[3], guessStats);

		// Padding and spacing
		guessRow.setPadding(new Insets(0, 10, 0, 30));
		ColumnConstraints bottomColConstraints = new ColumnConstraints();
		bottomColConstraints.setPercentWidth(16.67);
		for (int i = 0; i < 6; i++) {
			guessRow.getColumnConstraints().add(bottomColConstraints);
		}
	}

	private GridPane makeGuessStats() {
		GridPane guessStats = new GridPane();
		guessStats.setHgap(5);
		guessStats.setVgap(5);
		// Specification says 5px Insets, but 10 looks more centered imo.
		guessStats.setPadding(new Insets(10));

		// Getting RCRP and RCWP
		String guess = getGuessAsString();
		try {
			RCRP = controller.getRightColorRightPlace(guess);
			RCWP = controller.getRightColorWrongPlace(guess);
		} catch (MastermindIllegalLengthException | MastermindIllegalColorException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				guessStats.add(new Circle(5, nextStatPegColor()), i, j);
			}
		}
		return guessStats;
	}

	private Paint nextStatPegColor() {
		if (RCRP > 0) {
			RCRP--;
			return Color.BLACK;
		}
		if (RCWP > 0) {
			RCWP--;
			return Color.WHITE;
		}
		return Color.TAN;
	}
}

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.MastermindController;
import controller.MastermindIllegalColorException;
import controller.MastermindIllegalLengthException;
import model.MastermindModel;

/**
 * This class collects all of the test methods for our controller.
 * 
 * In eclipse, running it should run it under JUnit. Running the Mastermind
 * class (since it is our main class) will actually run the program. If you're
 * not using eclipse, you'll need to run this under JUnit 5.
 * 
 * @author Kaushal Bhat
 *
 */
class MastermindTest {

	/**
	 * Test method for {@link MastermindController#isCorrect(java.lang.String)}.
	 */
	@Test
	void testIsCorrect() {
		// Build a model with a known answer, using our special testing constructor
		MastermindModel answer = new MastermindModel("rrrr");
		// Build the controller from the model
		MastermindController controllerUnderTest = new MastermindController(answer);

		try {
			// For a properly working controller, this should return true
			assertTrue(controllerUnderTest.isCorrect("rrrr"));

			// For a properly working controller, these should be false
			assertFalse(controllerUnderTest.isCorrect("oooo"));
			assertFalse(controllerUnderTest.isCorrect("rrrg"));
			assertFalse(controllerUnderTest.isCorrect("grrr"));
		} catch (MastermindIllegalLengthException | MastermindIllegalColorException e) {
			e.printStackTrace();
		}
		
		// This should throw a MastermindIllegalColorException
		assertThrows(
				MastermindIllegalColorException.class,
				() -> {
					System.out.println("assertthrows is working 1");
					controllerUnderTest.isCorrect("xxxx");
				}
		);
		
		// This should throw a MastermindIllegalLengthException
		assertThrows(
				MastermindIllegalLengthException.class,
				() -> {
					System.out.println("assertthrows is working 2");
					controllerUnderTest.isCorrect("rrrrr");
				}
		);

	}

	/**
	 * Test method for
	 * {@link MastermindController#getRightColorRightPlace(java.lang.String)}.
	 */
	@Test
	void testGetRightColorRightPlace() {
		// Build a model with a known answer, using our special testing constructor
		MastermindModel answer = new MastermindModel("rrrr");
		// Build the controller from the model
		MastermindController controllerUnderTest = new MastermindController(answer);

		try {
			// For a properly working controller, this should return 4
			assertEquals(controllerUnderTest.getRightColorRightPlace("rrrr"), 4);
			
			// For a properly working controller, this should return 0
			assertEquals(controllerUnderTest.getRightColorRightPlace("oooo"), 0);
			
			// For a properly working controller, this should return 2
			assertEquals(controllerUnderTest.getRightColorRightPlace("roro"), 2);
			
			// For a properly working controller, this should return 1
			assertEquals(controllerUnderTest.getRightColorRightPlace("robo"), 1);
			
			// For a properly working controller, this should return 3
			assertEquals(controllerUnderTest.getRightColorRightPlace("rgrr"), 3);
			
			// For a properly working controller, this should return 1
			assertEquals(controllerUnderTest.getRightColorRightPlace("pbrg"), 1);
			
			// For a properly working controller, this should return 2
			assertEquals(controllerUnderTest.getRightColorRightPlace("brrb"), 2);
			
		} catch (MastermindIllegalLengthException | MastermindIllegalColorException e) {
			e.printStackTrace();
		}
		
		// This should throw a MastermindIllegalColorException
		assertThrows(
				MastermindIllegalColorException.class,
				() -> {
					System.out.println("assertthrows is working 3");
					controllerUnderTest.getRightColorRightPlace("xxxx");
				}
		);
		
		// This should throw a MastermindIllegalLengthException
		assertThrows(
				MastermindIllegalLengthException.class,
				() -> {
					System.out.println("assertthrows is working 4");
					controllerUnderTest.getRightColorRightPlace("rrrrr");
				}
		);
	}

	/**
	 * Test method for
	 * {@link MastermindController#getRightColorWrongPlace(java.lang.String)}.
	 */
	@Test
	void testGetRightColorWrongPlace() {
		// Build a model with a known answer, using our special testing constructor
		MastermindModel answer = new MastermindModel("royg");
		// Build the controller from the model
		MastermindController controllerUnderTest = new MastermindController(answer);
		
		try {
			// For a properly working controller, this should return 4
			assertEquals(controllerUnderTest.getRightColorWrongPlace("gyor"), 4);
			
			// For a properly working controller, this should return 2
			assertEquals(controllerUnderTest.getRightColorWrongPlace("gyog"), 2);
			
			// For a properly working controller, this should return 3
			assertEquals(controllerUnderTest.getRightColorWrongPlace("gyob"), 3);
			
			// For a properly working controller, this should return 1
			assertEquals(controllerUnderTest.getRightColorWrongPlace("gooo"), 1);
			
			// For a properly working controller, this should return 0
			assertEquals(controllerUnderTest.getRightColorWrongPlace("oooo"), 0);
			
			// For a properly working controller, this should return 0
			assertEquals(controllerUnderTest.getRightColorWrongPlace("bbbb"), 0);
			
		} catch (MastermindIllegalLengthException | MastermindIllegalColorException e) {
			e.printStackTrace();
			
		}
		
		// This should throw a MastermindIllegalColorException
		assertThrows(
				MastermindIllegalColorException.class,
				() -> {
					System.out.println("assertthrows is working 5");
					controllerUnderTest.getRightColorWrongPlace("xxxx");
				}
		);
		
		// This should throw a MastermindIllegalLengthException
		assertThrows(
				MastermindIllegalLengthException.class,
				() -> {
					System.out.println("assertthrows is working 6");
					controllerUnderTest.getRightColorWrongPlace("rrrrr");
				}
		);
	}
	
	/**
	 * Test method for no argument constructor for MastermindModel.
	 */
	@Test
	void testModelRandomConstructor() {
		// Build a model with a random answer using the constructor that is actually
		// used in game.
		MastermindModel answer = new MastermindModel();
	}

}

package exceptions;

public class InvalidMovementException extends InvalidInputException{

	public InvalidMovementException(String input) {
		super("Invalid input.  Please enter a number between 1 and 4.  This is what you entered: " + input);
	}
}

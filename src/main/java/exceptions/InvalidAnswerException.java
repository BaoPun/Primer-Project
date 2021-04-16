package exceptions;

public class InvalidAnswerException extends InvalidInputException{
	public InvalidAnswerException(String input) {
		super("Invalid option: Please type 0, 1, 2, 3, or 4.  Your input: " + input);
	}
}

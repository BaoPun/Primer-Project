package exceptions;

/**
 * This exception will occur when we don't answer properly, i.e we don't answer 0, 1, 2, 3, 4.
 * @author baoph
 *
 */
public class InvalidAnswerException extends InvalidInputException{
	public InvalidAnswerException(String input) {
		super("Invalid option: Please type 0, 1, 2, 3, or 4.  Your input: " + input);
	}
}

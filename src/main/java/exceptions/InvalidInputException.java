package exceptions;

/**
 * This base exception occurs when we have an invalid input
 * @author baoph
 *
 */
public class InvalidInputException extends Exception{
	
	public InvalidInputException(String input) {
		super("Invalid input: " + input);
	}
}

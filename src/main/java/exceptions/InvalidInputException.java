package exceptions;

/**
 * This base exception occurs when we have an invalid input of any type.
 * The inherited inputs will be passed as an input parameter to the exception.
 * @author baoph
 *
 */
public abstract class InvalidInputException extends Exception{
	
	public InvalidInputException(String input) {
		super("Invalid input: " + input);
	}
}

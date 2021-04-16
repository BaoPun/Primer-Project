package exceptions;

/**
 * This exception occurs when we attempt to enter anything that's not "1" or "0" when starting the game.
 * @author baoph
 *
 */
public class InvalidStartException extends InvalidInputException{
	
	public InvalidStartException(String input) {
		super("Invalid option: Please type 1 or 0.  This is what you typed: \"" + input + "\"");
	}
}

package exceptions;

public class InvalidDirectionException extends InvalidInputException{
	public InvalidDirectionException(String input) {
		super("Error, you cannot move " + input + ", as you would be out of bounds otherwise.  Please choose a different direction");
	}
}

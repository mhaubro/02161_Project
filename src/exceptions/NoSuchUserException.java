package exceptions;

/**
 * 
 * @author Mathias Gammelmark
 * @author Martin Haubro
 *
 */
public class NoSuchUserException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSuchUserException(String string) {
		super(string);
	}
}

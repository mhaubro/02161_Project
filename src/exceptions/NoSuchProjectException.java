package exceptions;

/**
 * 
 * @author Mathias Gammelmark
 * @author Martin Haubro
 *
 */
public class NoSuchProjectException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSuchProjectException(String string) {
		super(string);
	}
}

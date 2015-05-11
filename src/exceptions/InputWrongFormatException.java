package exceptions;

/**
 * 
 * @author Mathias Gammelmark
 * @author Martin Haubro
 *
 */
public class InputWrongFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public InputWrongFormatException(String string) {
		super(string);
	}

}

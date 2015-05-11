package exceptions;

/**
 * 
 * @author Mathias Gammelmark
 * @author Martin Haubro
 *
 */
public class UserAlreadyPlannedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserAlreadyPlannedException(String string) {
		super(string);
	}
}

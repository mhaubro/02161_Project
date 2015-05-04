package exceptions;

public class UserAlreadyPlannedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserAlreadyPlannedException(String string) {
		super(string);
	}
}

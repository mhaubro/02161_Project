package exceptions;

public class NoSuchUserException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSuchUserException(String string) {
		super(string);
	}
}

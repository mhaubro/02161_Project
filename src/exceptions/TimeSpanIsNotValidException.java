package exceptions;

public class TimeSpanIsNotValidException extends Exception {

	private static final long serialVersionUID = 1L;

	public TimeSpanIsNotValidException(String string) {
		super(string);
	}
}


package exceptions;

/**
 * 
 * @author Mathias Gammelmark
 * @author Martin Haubro
 *
 */
public class OperationNotAllowedException extends Exception {
	private static final long serialVersionUID = 1L;

	public OperationNotAllowedException(String string){
		super(string);
	}

}

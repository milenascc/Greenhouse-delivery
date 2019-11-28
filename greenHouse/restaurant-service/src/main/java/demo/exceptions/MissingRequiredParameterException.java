package demo.exceptions;

@SuppressWarnings("serial")
public class MissingRequiredParameterException extends Exception {
	public MissingRequiredParameterException(String msg) {
		super(msg);
	}
}

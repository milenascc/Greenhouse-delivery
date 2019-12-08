package demo.exceptions;

@SuppressWarnings("serial")
public class EmptyInputFieldsException extends Exception{
	public EmptyInputFieldsException(String msg) {
		super(msg);
	}

}

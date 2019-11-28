package demo.exceptions;

@SuppressWarnings("serial")
public class EmptyRestaurantNameException extends Exception {
	public EmptyRestaurantNameException(String msg) {
		super(msg);
	}
}

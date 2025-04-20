package my.project.utils;

public class IncorrectRequestBodyException extends RuntimeException {
	public IncorrectRequestBodyException(String message) {
		super(message);
	}
}

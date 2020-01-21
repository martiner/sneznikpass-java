package cz.geek.sneznikpass;

public class SneznikPassClientException extends RuntimeException {

	public SneznikPassClientException(Throwable cause) {
		super(cause);
	}

	public SneznikPassClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public SneznikPassClientException(String message) {
		super(message);
	}
}

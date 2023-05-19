package exception;

public class ConnectionException extends Exception {

	public ConnectionException(String message) {

		super(message);
	}

	
	@Override
	public String getMessage() {
		String message = super.getMessage();
		return "System can't reach to the database ";
		
	}
}

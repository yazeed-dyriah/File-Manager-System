
package exception;

public class LoginException extends Exception {
    public LoginException(String message) {
        super(message);

    }
    @Override
    public String getMessage() {
        String message = super.getMessage();
        return "Wrong login,, please try again";

    }
}

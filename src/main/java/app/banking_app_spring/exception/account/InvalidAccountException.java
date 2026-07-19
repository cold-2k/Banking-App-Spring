package app.banking_app_spring.exception.account;

public class InvalidAccountException extends AccountException {
    public InvalidAccountException(String message) {
        super(message);
    }
}

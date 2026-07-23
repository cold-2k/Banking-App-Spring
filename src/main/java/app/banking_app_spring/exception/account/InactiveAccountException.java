package app.banking_app_spring.exception.account;

public class InactiveAccountException extends AccountException {
    public InactiveAccountException(String message) {
        super(message);
    }
}

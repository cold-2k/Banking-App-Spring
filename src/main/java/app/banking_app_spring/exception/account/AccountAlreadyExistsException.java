package app.banking_app_spring.exception.account;

public class AccountAlreadyExistsException extends AccountException {
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}

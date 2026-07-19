package app.banking_app_spring.exception.account;

public class AccountNotFoundException extends AccountException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

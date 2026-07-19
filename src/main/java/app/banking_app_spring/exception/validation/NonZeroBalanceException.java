package app.banking_app_spring.exception.validation;

import app.banking_app_spring.exception.account.AccountException;

public class NonZeroBalanceException extends AccountException {
    public NonZeroBalanceException(String message) {
        super(message);
    }
}

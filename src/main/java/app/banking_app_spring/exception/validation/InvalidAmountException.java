package app.banking_app_spring.exception.validation;

import app.banking_app_spring.exception.account.AccountException;

public class InvalidAmountException extends AccountException {
    public InvalidAmountException(String message) {
        super(message);
    }
}

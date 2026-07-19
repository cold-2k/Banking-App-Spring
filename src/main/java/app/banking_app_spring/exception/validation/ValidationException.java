package app.banking_app_spring.exception.validation;

import app.banking_app_spring.exception.BankingException;

public class ValidationException extends BankingException {
    public ValidationException(String message) {
        super(message);
    }
}

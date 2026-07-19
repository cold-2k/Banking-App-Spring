package app.banking_app_spring.exception.account;

import app.banking_app_spring.exception.BankingException;

public class InvalidAccountStatusException extends BankingException {
    public InvalidAccountStatusException(String message) {
        super(message);
    }
}

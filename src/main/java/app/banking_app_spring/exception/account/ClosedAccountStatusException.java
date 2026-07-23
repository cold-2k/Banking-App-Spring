package app.banking_app_spring.exception.account;

import app.banking_app_spring.exception.BankingException;

public class ClosedAccountStatusException extends BankingException {
    public ClosedAccountStatusException(String message) {
        super(message);
    }
}

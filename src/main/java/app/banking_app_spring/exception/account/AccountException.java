package app.banking_app_spring.exception.account;

import app.banking_app_spring.exception.BankingException;

public class AccountException extends BankingException {
    public AccountException(String message) {
        super(message);
    }
}

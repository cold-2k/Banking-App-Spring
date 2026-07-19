package app.banking_app_spring.exception.customer;

import app.banking_app_spring.exception.BankingException;

public class CustomerException extends BankingException {
    public CustomerException(String message) {
        super(message);
    }
}

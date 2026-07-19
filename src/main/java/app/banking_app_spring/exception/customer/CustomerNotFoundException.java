package app.banking_app_spring.exception.customer;

import app.banking_app_spring.exception.BankingException;

public class CustomerNotFoundException extends BankingException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}

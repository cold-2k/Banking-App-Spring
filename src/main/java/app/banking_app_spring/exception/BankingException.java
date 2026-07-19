package app.banking_app_spring.exception;

public class BankingException extends RuntimeException {
    public BankingException(String message) {
        super(message);
    }
}
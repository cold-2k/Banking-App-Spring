package app.banking_app_spring.exception.customer;

public class InvalidCustomerException extends CustomerException {
    public InvalidCustomerException(String message) {
        super(message);
    }
}

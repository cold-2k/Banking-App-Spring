package app.banking_app_spring.exception;

import app.banking_app_spring.dto.response.ErrorResponse;
import app.banking_app_spring.exception.account.AccountAlreadyExistsException;
import app.banking_app_spring.exception.account.AccountNotFoundException;
import app.banking_app_spring.exception.account.ClosedAccountStatusException;
import app.banking_app_spring.exception.account.InactiveAccountException;
import app.banking_app_spring.exception.customer.CustomerNotFoundException;
import app.banking_app_spring.exception.validation.InvalidAmountException;
import app.banking_app_spring.exception.validation.NonZeroBalanceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ErrorResponse buildErrorResponse(
            HttpStatus status,
            Exception ex,
            HttpServletRequest request
    ) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }


    @ExceptionHandler({
            AccountNotFoundException.class,
            CustomerNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler({
        // Account
        AccountAlreadyExistsException.class,
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleForbidden(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex, request);
    }

    @ExceptionHandler({
            // Account
            ClosedAccountStatusException.class,
            InactiveAccountException.class,
            // Validations
            InvalidAmountException.class,
            NonZeroBalanceException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalid(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(
            Exception ex,
            HttpServletRequest request
    ) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }
}

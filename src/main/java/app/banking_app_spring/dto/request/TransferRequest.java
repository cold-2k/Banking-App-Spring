package app.banking_app_spring.dto.request;

import app.banking_app_spring.enums.TransactionDestination;
import app.banking_app_spring.enums.TransactionSource;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotBlank
    private String fromAccountNumber;

    @NotBlank
    private String toAccountNumber;

    @NotBlank
    private BigDecimal amount;

    private TransactionSource source;

    private TransactionDestination destination;

    @Size(max = 100)
    private String reference;
}

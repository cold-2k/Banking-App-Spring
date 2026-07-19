package app.banking_app_spring.dto.request;

import app.banking_app_spring.enums.TransactionDestination;
import app.banking_app_spring.enums.TransactionSource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequest {
    @NotBlank
    private String accountNumber;

    @NotBlank
    private BigDecimal amount;

    @NotBlank
    private TransactionDestination destination;

    @Size(max = 100)
    private String reference;
}

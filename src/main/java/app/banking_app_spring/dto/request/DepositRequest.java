package app.banking_app_spring.dto.request;

import app.banking_app_spring.enums.TransactionSource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {
    @NotBlank
    private String accountNumber;

    @NotBlank
    @Positive
    private BigDecimal amount;

    @NotBlank
    private TransactionSource source;

    @Size(max = 100)
    private String reference;
}

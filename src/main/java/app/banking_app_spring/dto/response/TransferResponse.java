package app.banking_app_spring.dto.response;

import app.banking_app_spring.entity.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferResponse {
    @NotBlank
    private final Account fromAccount;
    @NotBlank
    private final Account toAccount;
    @NotBlank
    private final BigDecimal amount;
}


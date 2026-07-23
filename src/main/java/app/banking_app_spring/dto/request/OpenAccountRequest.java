package app.banking_app_spring.dto.request;

import app.banking_app_spring.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OpenAccountRequest {
    @NotBlank
    private Long customerId;
    @NotBlank
    private AccountType type;
}

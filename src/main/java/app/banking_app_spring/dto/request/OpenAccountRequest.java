package app.banking_app_spring.dto.request;

import app.banking_app_spring.enums.AccountType;
import lombok.Data;

@Data
public class OpenAccountRequest {
    private Long customerId;
    private AccountType type;
}

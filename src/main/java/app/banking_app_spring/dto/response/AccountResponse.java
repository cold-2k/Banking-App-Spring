package app.banking_app_spring.dto.response;

import app.banking_app_spring.enums.AccountStatus;
import app.banking_app_spring.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponse {
    private String accountNumber;
    private BigDecimal balance;
    private AccountType type;
    private AccountStatus status;
}

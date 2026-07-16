package dto.response;

import enums.AccountStatus;
import enums.AccountType;

import java.math.BigDecimal;

public class AccountResponse {
    private String accountNumber;
    private BigDecimal balance;
    private AccountType type;
    private AccountStatus status;
}

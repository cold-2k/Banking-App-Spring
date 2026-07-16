package dto.response;

import enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
    private String transactionId;
    private BigDecimal amount;
    private TransactionType type;
    private LocalDateTime timestamp;
    private String reference;
}

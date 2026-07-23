package app.banking_app_spring.enums;

// CR: TransactionDestination and TransactionSource have same enum values, why keep different enum classes?
public enum TransactionDestination {
    CASH,
    UPI,
    NEFT,
    IMPS,
    CHEQUE,
    CARD,
    SALARY,
    INTERNAL_TRANSFER,
    OTHER
}

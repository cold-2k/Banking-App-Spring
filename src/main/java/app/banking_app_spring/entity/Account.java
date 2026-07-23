package app.banking_app_spring.entity;

import app.banking_app_spring.enums.AccountStatus;
import app.banking_app_spring.enums.AccountType;
import app.banking_app_spring.exception.account.InactiveAccountException;
import app.banking_app_spring.exception.account.ClosedAccountStatusException;
import app.banking_app_spring.exception.validation.InvalidAmountException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "accounts")
public class Account {

    // ---------- Fields ----------

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        @Setter
        private String accountNumber;

        @JoinColumn(
                name = "customer_id",
                nullable = false
        )
        @ManyToOne(fetch = FetchType.LAZY)
        @Setter
        private Customer owner;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private AccountType type;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private AccountStatus status;

        @Column(
                nullable = false,
                precision = 19,
                scale = 2
        )
        private BigDecimal balance;

        @Column(nullable = false)
        private LocalDate dateOpened;

        @OneToMany(
                mappedBy = "account",
                cascade = CascadeType.ALL,
                orphanRemoval = true
        )
        private List<Transaction> transactions = new ArrayList<>();

    // ---------- Constructors ----------

        protected Account() {}

        public Account(AccountType type) {
            this.type = type;
            this.status = AccountStatus.ACTIVE;
            this.balance = BigDecimal.ZERO;
            this.dateOpened = LocalDate.now();
        }

    // ---------- Business methods ----------

    // 1. Validations

        // active account validation
        private void validateAccountIsActive() {
            if (status != AccountStatus.ACTIVE) {
                throw new InactiveAccountException("Account is NOT ACTIVE and cannot perform the requested operation.");
            }
        }

        // amount validation
        private void validateAmount(BigDecimal amount) {
            // amount must not be null or non-positive
            if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidAmountException("Invalid amount. An amount must be greater than 0.");
            }
        }

        // validate account is NOT CLOSED
        private void validateAccountIsNotClosed() {
            if (status == AccountStatus.CLOSED) {
                throw new ClosedAccountStatusException("Account is CLOSED and cannot perform the requested operation.");
            }
        }

        // validate zero balance in account
        private void validateZeroBalance() {
            if (balance.compareTo(BigDecimal.ZERO) != 0) {
                throw new InvalidAmountException("Invalid balance. Must be ZERO to proceed with the operation.");
            }
        }

        // validate (debit amount >= balance) for withdraws
        private void validateSufficientBalance(BigDecimal amount) {
            if (balance.compareTo(amount) < 0) {
                throw new InvalidAmountException("Insufficient balance for debit. Amount must be equal to or lesser than balance.");
            }
        }

        // validate a deposit
        private void validateCredit(BigDecimal amount) {
            // validate account status
            validateAccountIsActive();
            // validate amount to be credited to be greater than zero
            validateAmount(amount);
        }

        // validate a withdrawal
        private void validateWithdrawal(BigDecimal amount) {
            // validate account status
            validateAccountIsActive();
            // validate the amount
            validateAmount(amount);
            // validate amount to be debited to be greater than or equal to balance
            validateSufficientBalance(amount);
        }

    // 2. Utility methods

        // generate a new account number based on the account's ID and assign it
        public void generateAndAssignAccountNumber() {
            this.setAccountNumber(String.format("ACC%06d", id));
        }

        // add a new transaction to the account
        public void addTransaction(Transaction transaction) {
            transaction.setAccount(this);
            transactions.add(transaction);
        }

        // deposit an amount
        public void credit(BigDecimal amount) {
            // validate a debit for the given amount
            validateCredit(amount);

            // add amount to balance
            balance = balance.add(amount);
        }

        // withdraw an amount
        public void debit(BigDecimal amount) {
            // validate the withdrawal for the given amount
            validateWithdrawal(amount);

            // subtract amount to balance
            balance = balance.subtract(amount);
        }

        // close an account
        public void close() {
            // validate account is NOT CLOSED
            validateAccountIsNotClosed();

            // validate zero balance
            validateZeroBalance();

            // change the account status to CLOSED
            status = AccountStatus.CLOSED;
        }
}

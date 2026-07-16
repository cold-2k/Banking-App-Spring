package entity;

import enums.AccountStatus;
import enums.AccountType;
import exception.account.InvalidAccountException;
import exception.account.InvalidAccountStatusException;
import exception.validation.InvalidAmountException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    // ---------- Fields ----------

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String accountNumber;

        @JoinColumn(name = "customer_id", nullable = false)
        @ManyToOne(fetch = FetchType.LAZY)
        private Customer owner;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private AccountType type;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private AccountStatus status;

        @Column(nullable = false, precision = 19, scale = 2)
        private BigDecimal balance;

        @Column(nullable = false)
        private LocalDate dateOpened;

        @OneToMany(mappedBy = "account")
        private List<Transaction> transactions = new ArrayList<>();

    // ---------- Constructors ----------

    protected Account() {}

    public Account(Customer owner, AccountType type) {
        this.owner = owner;
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
                throw new InvalidAccountException("Account is NOT ACTIVE and cannot perform the requested operation.");
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
        private void validateAccountIsClosed() {
            if (status == AccountStatus.CLOSED) {
                throw new InvalidAccountStatusException("Account is CLOSED and cannot perform the requested operation.");
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
        private void validateDeposit(BigDecimal amount) {
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

        // add a new transaction to the account
        void addTransaction(Transaction transaction) {
            transaction.setAccount(this);
            transactions.add(transaction);
        }

        // deposit an amount
        public void deposit(BigDecimal amount) {
            // validate a debit for the given amount
            validateDeposit(amount);

            // add amount to balance
            balance = balance.add(amount);
        }

        // withdraw an amount
        public void withdraw(BigDecimal amount) {
            // validate the withdrawal for the given amount
            validateWithdrawal(amount);

            // subtract amount to balance
            balance = balance.subtract(amount);
        }

        // close an account
        public void close() {
            // validate account is NOT CLOSED
            validateAccountIsClosed();

            // validate zero balance
            validateZeroBalance();

            // change the account status to CLOSED
            status = AccountStatus.CLOSED;
        }

    // ---------- Getters ----------

        public Long getId() {
            return id;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public Customer getOwner() {
            return owner;
        }

        public AccountType getType() {
            return type;
        }

        public AccountStatus getStatus() {
            return status;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public LocalDate getDateOpened() {
            return dateOpened;
        }

        public List<Transaction> getTransactions() {
            return transactions;
        }

    // ---------- Setters ----------

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public void setOwner(Customer owner) {
            this.owner = owner;
        }
}

package app.banking_app_spring.entity;

import app.banking_app_spring.enums.TransactionDestination;
import app.banking_app_spring.enums.TransactionSource;
import app.banking_app_spring.enums.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="transactions")
public class Transaction {

    // ---------- Fields ----------

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String transactionId;

        @JoinColumn(
                name = "account_id",
                nullable = false
        )
        @ManyToOne(fetch = FetchType.LAZY)
        private Account account;

        @Column(nullable = false)
        private BigDecimal amount;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private TransactionType type;

        @Column(nullable = false)
        private LocalDateTime timestamp;

        @Column
        private TransactionSource source;

        @Column
        private TransactionDestination destination;

        @Column(length = 100)
        private String reference;

    // ---------- Constructors ----------

        protected Transaction() {}

        public Transaction(
                Account account,
                TransactionType type,
                BigDecimal amount,
                TransactionSource source,
                TransactionDestination destination,
                String reference
        ) {
            this.transactionId = UUID.randomUUID().toString();
            this.account = account;
            this.type = type;
            this.amount = amount;
            this.reference = reference;
            this.source = source;
            this.destination = destination;
            this.timestamp = LocalDateTime.now();
        }

    // ---------- Getters ----------

        public Long getId() {
            return id;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public Account getAccount() {
            return account;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public TransactionType getType() {
            return type;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public TransactionSource getSource() {
            return source;
        }

        public TransactionDestination getDestination() {
            return destination;
        }

        public String getReference() {
            return reference;
        }

    // ---------- Setters ----------

        void setAccount(Account account) {
            this.account = account;
        }
}

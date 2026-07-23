package app.banking_app_spring.service.impl;

import app.banking_app_spring.dto.request.DepositRequest;
import app.banking_app_spring.dto.request.OpenAccountRequest;
import app.banking_app_spring.dto.request.TransferRequest;
import app.banking_app_spring.dto.request.WithdrawRequest;
import app.banking_app_spring.dto.response.AccountResponse;
import app.banking_app_spring.dto.response.TransferResponse;
import app.banking_app_spring.entity.Account;
import app.banking_app_spring.entity.Customer;
import app.banking_app_spring.entity.Transaction;
import app.banking_app_spring.enums.TransactionDestination;
import app.banking_app_spring.enums.TransactionSource;
import app.banking_app_spring.enums.TransactionType;
import app.banking_app_spring.exception.account.AccountNotFoundException;
import app.banking_app_spring.exception.customer.CustomerNotFoundException;
import app.banking_app_spring.repository.CustomerRepository;
import app.banking_app_spring.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import app.banking_app_spring.repository.AccountRepository;
import app.banking_app_spring.service.AccountService;

import java.math.BigDecimal;

@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    // Dependency Injection

        private final AccountRepository accountRepository;
        private final CustomerRepository customerRepository;
        private final TransactionRepository transactionRepository;
        public AccountServiceImpl(
                AccountRepository accountRepository,
                CustomerRepository customerRepository,
                TransactionRepository transactionRepository
        ) {
            this.accountRepository = accountRepository;
            this.customerRepository = customerRepository;
            this.transactionRepository = transactionRepository;
        }

    // Utility methods

        // AccountResponse Mapper
        private AccountResponse mapToAccountResponse(Account account) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setAccountNumber(account.getAccountNumber());
            accountResponse.setType(account.getType());
            accountResponse.setBalance(account.getBalance());
            accountResponse.setStatus(account.getStatus());

            return accountResponse;
            }

        // TransferResponse Mapper
        private TransferResponse mapToTransferResponse(TransferRequest request) {
            TransferResponse transferResponse = new TransferResponse();

            transferResponse.setFromAccountNumber(request.getFromAccountNumber());
            transferResponse.setToAccountNumber(request.getToAccountNumber());
            transferResponse.setAmount(request.getAmount());
            transferResponse.setSource(request.getSource());
            transferResponse.setDestination(request.getDestination());
            transferResponse.setReference(request.getReference());

            return transferResponse;
        }

        // Find an account for an Account Number
        private Account findAccountByAccountNumber(String accountNumber) {
            return accountRepository.findByAccountNumber(accountNumber).orElseThrow(()->
                    new AccountNotFoundException("No account exists for Account Number: " + accountNumber)
            );
        }

        // Find a customer for a Customer ID
        private Customer findCustomerById(long customerId) {
            return customerRepository.findById(customerId).orElseThrow(()->
                    new CustomerNotFoundException("Customer not found for Customer ID: " + customerId)
            );
        }

        // Credit amount to an account
        private Transaction performCredit(Account account, BigDecimal amount, TransactionSource source, String reference) {
            account.credit(amount);

            Transaction txn = new Transaction(
                    account,
                    TransactionType.CREDIT,
                    amount,
                    source,
                    null,
                    reference
            );
            // add this transaction to account's transaction list
            account.addTransaction(txn);
            // save the transaction to transaction repo
            transactionRepository.save(txn);

            return txn;
        }

        // Debit Amount to an Account
        private Transaction performDebit(Account account, BigDecimal amount, TransactionDestination destination, String reference) {
                account.debit(amount);

                Transaction txn = new Transaction(
                        account,
                        TransactionType.DEBIT,
                        amount,
                        null,
                        destination,
                        reference
                );
                // add this transaction to account's transaction list
                account.addTransaction(txn);
                // save the transaction to transaction repo
                transactionRepository.save(txn);

            return txn;
        }

    // Service methods

        @Override
        public AccountResponse openAccount(OpenAccountRequest request) {
            Customer customer = findCustomerById(request.getCustomerId());

            Account account = new Account(request.getType());
            // save the account to repo
            accountRepository.save(account);
            // generate the account number using the account's ID
            account.generateAndAssignAccountNumber();
            // add the account to customer's account list
            customer.addAccount(account);

            log.info(
                    "Congratulations {}. Your {} account has been opened successfully.",
                    customer.getFirstName(),
                    account.getType()
            );

            return mapToAccountResponse(account);
        }

        @Override
        public AccountResponse deposit(DepositRequest request) {
            Account account = findAccountByAccountNumber(request.getAccountNumber());

            Transaction txn = performCredit(
                    account,
                    request.getAmount(),
                    request.getSource(),
                    request.getReference()
            );

            log.info(
                    "{} deposited to account {} successfully.",
                    request.getAmount(),
                    account.getAccountNumber()
            );

            return mapToAccountResponse(account);
        }

        @Override
        public AccountResponse withdraw(WithdrawRequest request) {
            Account account = findAccountByAccountNumber(request.getAccountNumber());

            Transaction txn = performDebit(
                    account,
                    request.getAmount(),
                    request.getDestination(),
                    request.getReference()
            );

            log.info(
                    "{} has been withdrawn from the account {} successfully.",
                    request.getAmount(),
                    account.getAccountNumber()
            );

            return mapToAccountResponse(account);
        }

        @Override
        public TransferResponse transfer(TransferRequest request) {
            // validation for transfer to different account than source
            if (request.getFromAccountNumber()
                    .equals(request.getToAccountNumber())) {
                throw new IllegalArgumentException(
                        "Cannot transfer to the same account."
                );
            }

            Account fromAccount = findAccountByAccountNumber(request.getFromAccountNumber());
            performDebit(
                    fromAccount,
                    request.getAmount(),
                    request.getDestination(),
                    request.getReference()
            );

            Account toAccount = findAccountByAccountNumber(request.getToAccountNumber());
            performCredit(
                    toAccount,
                    request.getAmount(),
                    request.getSource(),
                    request.getReference()
            );

            log.info(
                    "{} successfully transferred from {} to {}",
                    request.getAmount(),
                    fromAccount.getAccountNumber(),
                    toAccount.getAccountNumber()
            );

            return mapToTransferResponse(request);
        }
}

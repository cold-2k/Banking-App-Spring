package app.banking_app_spring.service;

import app.banking_app_spring.dto.request.DepositRequest;
import app.banking_app_spring.dto.request.OpenAccountRequest;
import app.banking_app_spring.dto.request.TransferRequest;
import app.banking_app_spring.dto.request.WithdrawRequest;
import app.banking_app_spring.dto.response.AccountResponse;
import app.banking_app_spring.dto.response.TransferResponse;
import app.banking_app_spring.entity.Account;

public interface AccountService {
    AccountResponse openAccount(
            OpenAccountRequest request
    );

    AccountResponse deposit(
            DepositRequest request
    );

    AccountResponse withdraw(
            WithdrawRequest request
    );

    TransferResponse transfer(
            TransferRequest request
    );
}

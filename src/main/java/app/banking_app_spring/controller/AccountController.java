package app.banking_app_spring.controller;

import app.banking_app_spring.dto.request.DepositRequest;
import app.banking_app_spring.dto.request.TransferRequest;
import app.banking_app_spring.dto.request.WithdrawRequest;
import app.banking_app_spring.dto.response.AccountResponse;
import app.banking_app_spring.dto.response.TransferResponse;
import app.banking_app_spring.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Deposit money into an account")
    @PostMapping("/deposit")
    public ResponseEntity<AccountResponse> deposit(@RequestBody DepositRequest request) {
        return ResponseEntity.ok(
                accountService.deposit(request)
        );
    }

    @Operation(summary = "Withdraw money from an account")
    @PostMapping("/withdraw")
    public ResponseEntity<AccountResponse> withdraw(@RequestBody WithdrawRequest request) {
        return ResponseEntity.ok(
                accountService.withdraw(request)
        );
    }

    @Operation(summary = "Transfer money from one account to another")
    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest request) {
        return ResponseEntity.ok(
                accountService.transfer(request)
        );
    }
}

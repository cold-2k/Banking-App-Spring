package app.banking_app_spring.controller;

import app.banking_app_spring.dto.request.DepositRequest;
import app.banking_app_spring.dto.request.TransferRequest;
import app.banking_app_spring.dto.request.WithdrawRequest;
import app.banking_app_spring.dto.response.AccountResponse;
import app.banking_app_spring.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController("accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @RequestParam DepositRequest request
    ){
        return ResponseEntity.ok(
                accountService.deposit(request)
        );
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountResponse> withdraw(WithdrawRequest request) {
        return ResponseEntity.ok(
                accountService.withdraw(request)
        );
    }

    @PostMapping("/transfer")
    public ResponseEntity<AccountResponse> transfer(TransferRequest request) {
        return ResponseEntity.ok(
                accountService.transfer(request)
        );
    }
}

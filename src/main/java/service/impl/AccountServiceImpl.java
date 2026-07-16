package service.impl;

import org.springframework.stereotype.Service;
import repository.AccountRepository;
import service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}

package com.example.services;

import com.example.models.Account;
import com.example.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerAccount(Account account) {
        accountRepository.saveAndFlush(account);
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
            Optional<Account> optional = accountRepository.findById(id);
            if(optional.isPresent()){
                Account account = optional.get();
                BigDecimal currentBalance = account.getBalance();
                if(currentBalance.compareTo(money) > 0){
                    account.setBalance(currentBalance.subtract(money));
                    this.accountRepository.saveAndFlush(account);
            }
            }
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
            Optional<Account> optional = this.accountRepository.findById(id);
            if(optional.isPresent()){
                Account account = optional.get();
                if(money.doubleValue() > 0){
                    account.setBalance(account.getBalance().add(money));
                    this.accountRepository.saveAndFlush(account);
                }
            }
    }
}

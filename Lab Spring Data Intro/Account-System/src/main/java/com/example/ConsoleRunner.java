package com.example;

import com.example.models.Account;
import com.example.models.User;
import com.example.services.AccountService;
import com.example.services.UserService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Gosho",46);
        Account account = new Account(BigDecimal.valueOf(4000));
       account.setUser(user);
        user.add(account);
        userService.registerUser(user);
       accountService.registerAccount(account);
       accountService.withdrawMoney(new BigDecimal(1679), 1L);
       accountService.transferMoney(new BigDecimal(333), 1L);
    }
}

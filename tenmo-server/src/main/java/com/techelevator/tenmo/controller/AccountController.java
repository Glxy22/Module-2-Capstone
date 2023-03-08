package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;

//import static jdk.internal.org.jline.utils.Colors.s;

@RestController
public class AccountController {
    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    //get balance
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public Account getBalance(Principal principal) {
        return accountDao.getBalance(principal.getName());
    }

}

package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
public class AccountController {
 private AccountDao accountDao;

 public AccountController(AccountDao accountDao){
     this.accountDao = accountDao;
 }
 @RequestMapping(path="/{id}/balance",method= RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable int id){
    return accountDao.getBalance(id);

 }

    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal) {
        System.out.println(principal.getName());
        return accountDao.getBalance(principal.getName());
    }

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public BigDecimal getaccount(Principal principal) {

        return accountDao.getBalance(principal.getName());
    }


}

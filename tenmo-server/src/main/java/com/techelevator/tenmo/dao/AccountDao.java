package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface AccountDao {
//    BigDecimal getBalancebyId(int id);
    Account getBalance(String name);
//    int getAccountByUserId(long userId);
    public Account withdrawBalance(int account_id, double amount);
}
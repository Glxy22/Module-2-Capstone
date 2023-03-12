package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface AccountDao {
//    BigDecimal getBalancebyId(int id);
    Account getBalance(String name);
//    int getAccountByUserId(long userId);
//    public Account withdrawBalance(int account_id, double amount);
    //transfer amount and adjust the balance accordingly
    public void transferFunds(Transfer transfer);
    public Account getAccountById(int id);
    public Account getAccountByUserID(int userId);
    public Account  getAccountByAccountID(int account_Id);


}
package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface AccountDao {
    BigDecimal getBalance(int id);
    BigDecimal getBalance(String name);
    //Long getAccountByUserId(int userId);
}

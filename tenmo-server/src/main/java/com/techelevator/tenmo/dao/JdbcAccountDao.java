package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //get the balance by account id

//    @Override
//    public BigDecimal getBalancebyId(int id) {
//        Account account= new Account();
////        BigDecimal balance = new BigDecimal("0.00");
//
//        String sql = "SELECT balance FROM account" +
//                "WHERE account_id= ?;";
//
//        SqlRowSet resource = jdbcTemplate.queryForRowSet(sql,id);
//
//        while (resource.next()) {
//            String s= resource.getString("balance");
//            account.setBalance(new BigDecimal(s));
//        }
//        return account.getBalance();
//    }

    @Override
    public Account withdrawBalance(int account_id, double amount){
        Account account = new Account();
        String sql = "UPDATE account SET balance = ? WHERE account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,double.class, amount, account_id);
        if(results.next()){
           account = mapToAccount(results);
           account.setBalance(account.getBalance() - amount);
        }

        return  account;
    }
    // get balance by user name
    @Override
    public Account getBalance(String name){
        Account account= new Account();
        String sql = "SELECT balance FROM account a "+
                "JOIN tenmo_user tu ON a.user_id = tu.user_id WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        if(results.next()){
            double s=  results.getDouble("balance");
            account.setBalance(s);
        }
        return account;
    }

    public Account mapToAccount(SqlRowSet rowSet){
        Account account = new Account();
        account.setAccount_id(rowSet.getInt("account_id"));
        account.setUser_id(rowSet.getInt("user_id"));
        account.setBalance(rowSet.getInt("balance"));
        return account;
    }


}




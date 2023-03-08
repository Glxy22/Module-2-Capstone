package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
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

    @Override
    public BigDecimal getBalance(int id) {
        Account account= new Account();
//        BigDecimal balance = new BigDecimal("0.00");

        String sql = "SELECT balance FROM account" +
                "WHERE account_id= ?;";

        SqlRowSet resource = jdbcTemplate.queryForRowSet(sql,id);

       while (resource.next()) {
            String s= resource.getString("balance");
            account.setBalance(new BigDecimal(s));
        }
        return account.getBalance();
               }

    //get account information by user id

    @Override
    public int getAccountByUserId(String username) {
        String sql = "SELECT account_id FROM account a "+
                "JOIN tenmo_user tu ON a.user_id = tu.user_id WHERE username = ?";
        SqlRowSet resource = jdbcTemplate.queryForRowSet(sql, username);
        Account account = null;
        while (resource.next()) {
            String s= resource.getString("account_id");
            account.setAccount_id(Integer.parseInt(s));
        }
        return account.getAccount_id();
    }

    // get balance by user name
    @Override
    public BigDecimal getBalance(String name){
        Account account= new Account();
        String sql = "SELECT balance FROM account a "+
                "JOIN tenmo_user tu ON a.user_id = tu.user_id WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        if(results.next()){
          String s=  results.getString("balance");
          account.setBalance(new BigDecimal(s));
        }
    return account.getBalance();
    }


    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));

        String accountBalance = rs.getString("balance");
        account.setBalance(new BigDecimal(accountBalance));

        return account;
    }


}





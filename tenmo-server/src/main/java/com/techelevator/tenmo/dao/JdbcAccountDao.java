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


//    private Account mapRowToAccount(SqlRowSet rs) {
//        Account account = new Account();
//        account.setAccount_id(rs.getInt("account_id"));
//        account.setUser_id(rs.getInt("user_id"));
//
//        String accountBalance = rs.getString("balance");
//        account.setBalance(new BigDecimal(accountBalance));
//
//        return account;
//    }


}




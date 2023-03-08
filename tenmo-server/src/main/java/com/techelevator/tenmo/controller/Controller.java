package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
public class Controller {
    private UserDao userDao;
    private AccountDao accountDao;

    public Controller(UserDao userDao, AccountDao accountDao){
        this.userDao= userDao; this.accountDao= accountDao;
    }


    //return user by ID

    @RequestMapping(path="user/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable int id){
        User user=userDao.getUserById(id);
        return user;
    }

    //return list of all users
    @RequestMapping(path="/tenmo_user", method = RequestMethod.GET)
    public List<User> list() {
        return userDao.findAll();
    }

    // return user by name
    @RequestMapping(path = "", method = RequestMethod.GET)
    public User findUser(@RequestParam String name) {
        User user = userDao.findByUsername(name);
        return user;
    }

//    @RequestMapping(path="tenmo_user/{id}/balance",method= RequestMethod.GET)
//    public double getBalance(@PathVariable int account_id){
//        return accountDao.getBalance(account_id);
//    }



//need to call jdbcAccount to get account info by id

//@RequestMapping(path="/account",method= RequestMethod.GET)
//       private Account getUserId(Principal principal) {
//        Account account;
//           String userString = principal.toString();
//           int startIndex = userString.indexOf("id=");
//           int endIndex = userString.indexOf(",", startIndex);
//           long l= Long.valueOf(userString.substring(startIndex + 3, endIndex));
//         return account= accountDao.getAccountByUserId(l);
//
//       }
    }



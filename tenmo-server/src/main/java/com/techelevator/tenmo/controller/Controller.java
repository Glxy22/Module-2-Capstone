package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@RestController
public class Controller {
    private UserDao userDao;
    private AccountDao accountDao;
    private TransferDao transferDao;

    public Controller(UserDao userDao, AccountDao accountDao, TransferDao transferDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.transferDao = transferDao;
    }


    //return user by ID

    @RequestMapping(path = "user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        User user = userDao.getUserById(id);
        return user;
    }

    //return list of all users
    @RequestMapping(path = "/tenmo_user", method = RequestMethod.GET)
    public List<User> list() {
        return userDao.findAll();
    }

    // return user by name
    @RequestMapping(path = "", method = RequestMethod.GET)
    public User findUser(@RequestParam String name) {
        User user = userDao.findByUsername(name);
        return user;
    }

    // creating transfer request
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create_transfer", method = RequestMethod.POST)
    public int createTransfer(@RequestBody Transfer transfer) {
        int transfer_id = transferDao.createTransfer(transfer);

        return transfer_id;
    }
    @RequestMapping(path = "/view_pending_requests", method = RequestMethod.GET)
    public List<Transfer> viewPendingRequests(Principal principal) {
        List<Transfer> transfers = null;
        transfers = transferDao.list_pending_transfer(principal.getName());

        return transfers;
    }

//returning transaction for current user

    @RequestMapping(path = "/list_transaction", method = RequestMethod.GET)
    public List<Transfer> listTransfers(Principal principal) {
        List<Transfer> transfers = null;
        transfers = transferDao.list_transfer_by_name(principal.getName());

        return transfers;
    }

    @RequestMapping(path = "/pending_transfer_status_change", method = RequestMethod.PUT)
    public void changeTransferStatus(Transfer transfer){
        System.out.println("SERVER TRANSFER ID:"+ transfer.getTransfer_id());
        System.out.println("SERVER STATUS ID:"+ transfer.getTransfer_status_id());
        transferDao.changeTransferStatus(transfer);
    }
//    @RequestMapping(path = "/withdrawTransfer/{id}", method = RequestMethod.PUT)
//    public void withdrawTransfer(@PathVariable int id) {
//        Transfer transfer1 = new Transfer();
//        double amount = 200;
//        Account account = null;
//        account = accountDao.withdrawBalance(id, amount);
//    }
}


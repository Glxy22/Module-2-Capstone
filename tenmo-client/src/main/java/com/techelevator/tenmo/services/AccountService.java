package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;

public interface AccountService {
    Account getBalance(AuthenticatedUser authenticatedUser);
}

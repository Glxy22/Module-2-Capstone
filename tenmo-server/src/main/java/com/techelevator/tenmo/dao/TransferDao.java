package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransferDao {
    public List<Transfer> transferHistory(int accountId);

    public Transfer viewTransfer(int transferId);

    public Transfer createTransfer(int type, int status, int from, int to, double amount);
}

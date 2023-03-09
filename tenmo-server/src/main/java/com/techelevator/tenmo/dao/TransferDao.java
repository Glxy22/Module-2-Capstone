package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransferDao {
    public List<Transfer> transferHistory();

    public List<Transfer> viewTransfer(String username);


    public Integer createTransfer(Transfer transfer);
    public void withdrawTransfer(int transferId);
}

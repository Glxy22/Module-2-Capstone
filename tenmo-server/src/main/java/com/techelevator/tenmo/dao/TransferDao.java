package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransferDao {
    public List<Transfer> transferHistory();

    public List<Transfer> list_transfer_by_name(String username);

    public int createTransfer(Transfer transfer);
    public void withdrawTransfer(int transferId);

    public void withdrawBalance(int transferId);
    public List<Transfer> list_pending_transfer(String username);
    public void changeTransferStatus(Transfer transfer);

}

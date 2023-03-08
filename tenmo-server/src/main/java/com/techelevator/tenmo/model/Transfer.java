package com.techelevator.tenmo.model;

public class Transfer {
    private int id;
    private int transfer_type_id;
    private int transfer_status_id;
    private int account_from;
    private int account_to;
    private Double balance;

    public Transfer(){}

    public Transfer(int id,int transfer_type_id,int transfer_status_id,
    int account_from,int account_to,Double balance){
        this.id=id;
        this.transfer_type_id = transfer_type_id;  this.transfer_status_id = transfer_status_id;
        this.account_from= account_from; this.account_to = account_to; this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransfer_type_id() {
        return transfer_type_id;
    }

    public void setTransfer_type_id(int transfer_type_id) {
        this.transfer_type_id = transfer_type_id;
    }

    public int getTransfer_status_id() {
        return transfer_status_id;
    }

    public void setTransfer_status_id(int transfer_status_id) {
        this.transfer_status_id = transfer_status_id;
    }

    public int getAccount_from() {
        return account_from;
    }

    public void setAccount_from(int account_from) {
        this.account_from = account_from;
    }

    public int getAccount_to() {
        return account_to;
    }

    public void setAccount_to(int account_to) {
        this.account_to = account_to;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

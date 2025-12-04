package com.teambank.bankingsystem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction
{
    private String transactionID;
    private String type;
    private BigDecimal amount;
    private Account fromAccount;
    private Account toAccount;
    private LocalDateTime timeStamp;
    private boolean status;
    public Transaction(String transactionID,String type,BigDecimal amount,Account fromAccount,Account toAccount)
    {
        this.transactionID = transactionID;
        this.type = type;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.timeStamp = LocalDateTime.now();
        this.status = false;
    }
    public boolean execute()
    {
        if(fromAccount.withdraw(this.amount))
        {
            toAccount.deposit(this.amount);
            this.status = true;
            System.out.println("Transaction "+this.transactionID+" has been executed successfully");
            return true;
        }
        else
        {
            this.status = false;
            System.out.println("Transaction "+this.transactionID+" has been failed");
            return false;
        }
    }

    public String getTransactionID() {
        return this.transactionID;
    }
    public void displayTransaction()
    {
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Type: " + type);
        System.out.println("Amount: " + amount);
        System.out.println("From Account: " + fromAccount);
        System.out.println("To Account: " + toAccount);
        System.out.println("Timestamp: " + timeStamp);
        System.out.println("Status: " + (status ? "Yes" : "No"));
    }
}

package com.teambank.bankingsystem;

import java.math.BigDecimal;

public abstract class Account
{
    private int account_no;
    private BigDecimal balance;
    private Customer owner;
    private String account_type;
    public Account(int account_no,Customer owner,String account_type)
    {
        this.account_no = account_no;
        this.balance = BigDecimal.ZERO;
        this.owner = owner;
        this.account_type = account_type;
        if(owner != null)
        {
            owner.addAccount(this);
        }
    }

    public void deposit(BigDecimal amount)
    {
        if(amount.compareTo(BigDecimal.ZERO)<=0)
        {
            System.out.println("Invalid amount");
            return;
        }
        this.balance = this.balance.add(amount);
        System.out.println("Deposit of " + amount + " successful. New balance: " + this.balance);
    }
    public abstract boolean withdraw(BigDecimal amount);
    public BigDecimal getBalance()
    {
        return this.balance;
    }
    public void displayAccountInformation()
    {
        System.out.println("Account no: " + account_no);
        System.out.println("Account type: " + account_type);
        System.out.println("Balance: " + balance);
    }

    public int getAccount_no()
    {
        return this.account_no;
    }

    public Customer getOwner() {
        return this.owner;
    }
    public String getAccount_type()
    {
        return this.account_type;
    }

    protected void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public abstract BigDecimal calculateInterest();
}

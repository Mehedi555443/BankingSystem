package com.teambank.bankingsystem;

import java.math.BigDecimal;

public class SavingsAccount extends Account
{
    private BigDecimal interestRate;
    private static BigDecimal min_balance=BigDecimal.ZERO;

    public SavingsAccount(int account_no,Customer owner,String account_type,BigDecimal interestRate)
    {
        super(account_no,owner,account_type);
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate()
    {
        return this.interestRate;
    }

    public BigDecimal calculateInterest()
    {
        BigDecimal interest = getBalance().multiply(this.interestRate);
        System.out.println("Calculated interest earned: " + interest);
        return interest;
    }

    @Override
    public boolean withdraw(BigDecimal amount)
    {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Withdrawal failed: Amount must be positive.");
            return false;
        }
        BigDecimal NewBalance = getBalance().subtract(amount);
        if (NewBalance.compareTo(min_balance) >= 0)
        {
            setBalance(NewBalance);
            System.out.println("Withdrawal successful on Savings Account " + getAccount_no());
            return true;
        }
        else
        {
            System.out.println("Withdrawal failed. Minimum balance of " + min_balance + " required.");
            return false;
        }
    }
}

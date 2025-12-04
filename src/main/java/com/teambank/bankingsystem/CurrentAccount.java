package com.teambank.bankingsystem;

import java.math.BigDecimal;

public class CurrentAccount extends Account
{
    private BigDecimal overDraftLimit;
    public CurrentAccount(int account_no,Customer owner,String account_type, BigDecimal overDraftLimit)
    {
        super(account_no,owner,account_type);
        if(overDraftLimit.compareTo(BigDecimal.ZERO)<=0)
        {
            this.overDraftLimit = BigDecimal.ZERO;
            System.out.println("Over Draft Limit is set to zero");
        }
        else
        {
            this.overDraftLimit = overDraftLimit;
        }
    }

    public BigDecimal getOverDraftLimit()
    {
        return this.overDraftLimit;
    }

    public BigDecimal calculateInterest()
    {
        return BigDecimal.ZERO;
    }

    @Override
    public boolean withdraw(BigDecimal amount)
    {
        if(amount.compareTo(BigDecimal.ZERO)<=0)
        {
            System.out.println("Withdrawal failed!");
            return false;
        }
        BigDecimal NewBalance = getBalance().subtract(amount);
        BigDecimal min_allowable_balance = this.overDraftLimit.negate();
        if(NewBalance.compareTo(min_allowable_balance)>=0)
        {
            setBalance(NewBalance);
            System.out.println("Withdrawal successful!");
            System.out.println("Current Balance is " + getBalance());
            return true;
        }
        else
        {
            System.out.println("Withdrawal failed! Exceeded overdraft limit");
            return false;
        }
    }
}

package com.teambank.bankingsystem;

import java.util.ArrayList;

public class Customer extends User
{
    private String customerName;
    private Long customerId;
    private String address;
    private String phone;
    private String email;
    private ArrayList<Account> accounts;
    public Customer(String username,String passwordHash,String role,String customerName, Long customerId, String address, String phone, String email)
    {
        super(username,passwordHash,role);
        this.customerName = customerName;
        this.customerId = customerId;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    public Long getCustomerId()
    {
        return this.customerId;
    }

    public String getCustomerName()
    {
        return this.customerName;
    }
    public String getAddress()
    {
        return this.address;
    }
    public String getPhone()
    {
        return this.phone;
    }
    public String getEmail()
    {
        return this.email;
    }

    public void addAccount(Account account)
    {
        this.accounts.add(account);
    }
    public void removeAccount(Account account)
    {
        this.accounts.remove(account);
    }
    public void displayinfo()
    {
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        if(accounts.size()>0)
        {
            for(Account account:accounts)
            {
                System.out.println("  > Account No: " + account.getAccount_no() +
                        " | Type: " + account.getAccount_type() +
                        " | Balance: " + account.getBalance());
            }

        }
        else
        {
            System.out.println("Customer owns no account");
        }
    }
}

package com.teambank.bankingsystem;

import java.util.ArrayList;

public class Bank
{
    private String bankName;
    private String branchCode;
    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;
    private ArrayList<Employee> employees;
    private ArrayList<Transaction> transactions;
    public Bank(String bankName, String branchCode)
    {
        this.bankName = bankName;
        this.branchCode = branchCode;
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }
    public void addCustomer(Customer customer)
    {
        this.customers.add(customer);
    }
    public void addAccount(Account account)
    {
        this.accounts.add(account);
    }
    public void addEmployee(Employee e)
    {
        this.employees.add(e);
    }
    public Account findAccount(int id)
    {
        for(Account account : this.accounts)
        {
            if(account.getAccount_no()==id)
            {
                return account;
            }
        }
        return null;

    }
    public Customer findCustomer(Long id)
    {
        for(Customer customer : this.customers)
        {
            if(customer.getCustomerId().equals(id))
            {
                return customer;
            }
        }
        return null;
    }
    public void processTransaction(Transaction transaction)
    {
        if(transaction.execute())
        {
            this.transactions.add(transaction);
            System.out.println("Transaction "+transaction.getTransactionID()+"successfully executed");
        }
        else
        {
            System.out.println("Transaction "+transaction.getTransactionID()+"failed to execute");
        }
    }
    public void displayAllAccounts()
    {
        System.out.println("\n===== ALL REGISTERED ACCOUNTS =====");
        if (this.accounts.isEmpty()) {
            System.out.println("No accounts found in the bank.");
            return;
        }
        for(Account account : this.accounts)
        {
            System.out.println("Account NO: "+account.getAccount_no()+" Owner"+account.getOwner());
            System.out.println("-----------------------------------");
        }
    }
    public  void displayAllEmployees()
    {
        System.out.println("\n===== ALL REGISTERED EMPLOYEES =====");
        if (this.employees.isEmpty()) {
            System.out.println("No employees found in the bank.");
            return;
        }
        for (Employee employee : this.employees)
        {
            System.out.println("Employee ID:"+employee.getEmployeeId()+" Name:"+employee.getName()+" Address:"+employee.getAddress()+" Email:"+employee.getEmail()+" Phone Number: "+employee.getPhone()+"Position:"+employee.getPosition());
            System.out.println("-----------------------------------");
        }
    }
    public void displayAllCustomers()
    {
        System.out.println("\n===== ALL REGISTERED CUSTOMERS =====");
        if (this.customers.isEmpty()) {
            System.out.println("No customers found in the bank.");
            return;
        }
        for (Customer customer : this.customers)
        {
            System.out.println("Customer ID:"+customer.getCustomerId()+" Name:"+customer.getCustomerName()+" Address: "+customer.getAddress()+"Phone: "+customer.getPhone()+"Email: "+customer.getEmail());
            System.out.println("-----------------------------------");
        }

    }
    public User findUser(String username)
    {
        for(Employee employee : this.employees)
        {
            if(employee.username.equalsIgnoreCase(username))
            {
                return employee;
            }
        }
        for(Customer customer : this.customers)
        {
            if(customer.username.equalsIgnoreCase(username))
            {
                return customer;
            }
        }
        return null;
    }
    public int getCustomerCount()
    {
        return this.customers.size();
    }
}

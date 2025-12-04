package com.teambank.bankingsystem;

public class Employee extends User
{
    private Long employeeId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String position;
    public  Employee(String username,String passwordHash,String role,Long employeeId, String name, String address, String email, String phone, String position)
    {
        super(username,passwordHash,role);
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.position = position;
    }

    public Long getEmployeeId()
    {
        return this.employeeId;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public String getPosition()
    {
        return this.position;
    }

    public boolean approveAccount(Bank bank,Account account)
    {
        if(account==null)
        {
            System.out.println("Account is null.Approval Failed!");
            return false;
        }
        if(bank.findAccount(account.getAccount_no())!=null)
        {
            System.out.println("Account already exists.Approval Failed!");
            return false;
        }
        bank.addAccount(account);
        System.out.println("Account approved successfully!");
        return true;
    }
    public boolean approveTransaction(Bank bank,Transaction transaction)
    {
        if(transaction==null)
        {
            System.out.println("Transaction is null.Approval Failed!");
            return false;
        }
        try
        {
            bank.processTransaction(transaction);
            System.out.println("Transaction approved successfully!");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Transaction approval failed due to system error!");
            return false;
        }
    }
    public void displayInformation()
    {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Position: " + position);
        System.out.println("Contact: " + phone + " | " + email);
        System.out.println("Login Role: " + this.role);
    }
}

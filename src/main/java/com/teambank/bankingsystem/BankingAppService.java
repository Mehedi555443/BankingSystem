package com.teambank.bankingsystem;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankingAppService
{
    public static Bank initializeBank(Scanner input)
    {
        System.out.println("\n--- Initializing Bank System Data ---");

        Bank centralBank = new Bank("Hudai Bank","AFDE2");

        Customer alice = new Customer("alice_user","pass_a","Customer","Mazedul Hasan Mahin", 41561L,"Gazipur","021xxxxx","mahin@mail");
        Customer bob = new Customer("bob_user", "pass_b", "Customer",
                "Bob Johnson", 1002L, "123 Lake View", "555-1002", "bob@mail.com");
        Employee manager = new Employee("manager_john", "mgr_pass", "Manager",
                500L, "John Manager", "Branch Manager", "100 Main HQ", "555-9000", "john@bank.com");
        centralBank.addCustomer(alice);
        centralBank.addCustomer(bob);
        centralBank.addEmployee(manager);

        SavingsAccount aliceSavings = new SavingsAccount(101, alice , "Savings", new BigDecimal("0.03"));
        aliceSavings.deposit(new BigDecimal("500.00")); // Initial deposit
        centralBank.addAccount(aliceSavings);


        CurrentAccount bobCurrent = new CurrentAccount(202, bob, "Current", new BigDecimal("200.00"));
        bobCurrent.deposit(new BigDecimal("100.00")); // Initial deposit
        centralBank.addAccount(bobCurrent);

        System.out.println("System initialized with 2 Customers, 1 Employee, and 2 Accounts.");
        return centralBank;
    }

    public static void adminPanel(Scanner input,Bank bank,Employee employee)
    {
        System.out.println("\n--- ADMIN PANEL ---");
        boolean running = true;
        while(running)
        {
            System.out.println("\nSelect Action:");
            System.out.println("1. Display All Customers");
            System.out.println("2. Display All Accounts");
            System.out.println("3. Create New Customer");
            System.out.println("4. Approve Transaction (Run Test)");
            System.out.println("5. Logout");

            String choice = input.nextLine();
            switch(choice)
            {
                case "1":
                    bank.displayAllCustomers();
                    break;
                case "2":
                    bank.displayAllAccounts();
                    break;
                case "3":
                    createCustomer(input, bank);
                    break;
                case "4":
                    //predefined for testing
                    System.out.println("\n--- Running Test Transaction T001 ---");
                    Account a1 = bank.findAccount(101); // Alice Savings
                    Account a2 = bank.findAccount(202); // Bob Current
                    if (a1 != null && a2 != null) {
                        Transaction testTxn = new Transaction("T001", "TEST_TRANSFER", new BigDecimal("10.00"), a1, a2);
                        employee.approveTransaction(bank, testTxn);
                    } else {
                        System.out.println("ERROR: Could not find test accounts 101 or 202.");
                    }
                    break;
                case "5":
                    employee.logout();
                    running = false;
                    break;
                default:
                    System.out.println("ERROR: Invalid choice.");
            }
        }
    }


    public static void userPanel(Scanner input,Bank bank, Customer customer)
    {
        System.out.println("\n--- USER PANEL ---");
        boolean running = true;
        while (running)
        {
            System.out.println("\nSelect Action:");
            System.out.println("1. View My Accounts");
            System.out.println("2. Make a Deposit/Withdrawal");
            System.out.println("3. Logout");
            String choice = input.nextLine();
            switch(choice)
            {
                case "1":
                    customer.displayinfo();
                    break;
                case "2":
                    approveTransaction(input, bank, customer);
                    break;
                case "3":
                    customer.logout();
                    running = false;
                    break;
                default:
                    System.out.println("ERROR: Invalid choice.");
            }
        }
    }
    public static void createCustomer(Scanner input,Bank bank)
    {
        System.out.println("New Customer Registration");

        Long newCustomerID = (long) bank.getCustomerCount() + 1000L;

        System.out.println("Customer Name");
        String name = input.nextLine();

        System.out.print("Enter New Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter Address: ");
        String address = input.nextLine();

        System.out.print("Enter Phone: ");
        String phone = input.nextLine();

        System.out.print("Enter Email: ");
        String email = input.nextLine();

        Customer newCustomer= new Customer(username,password,"Customer",name,newCustomerID,address,phone,email);

        bank.addCustomer(newCustomer);
        System.out.println("Customer created successfully");
        newCustomer.displayinfo();
        System.out.println("Customer ID: " + newCustomerID + " | Username: " + username);
    }
    public  static void approveTransaction(Scanner input,Bank bank,Customer customer)
    {
        System.out.println("\n--- Make Transaction ---");

        customer.displayinfo();
        System.out.println("Enter Account Number: ");
        String accNumStr = input.nextLine();

        int accNum;
        try
        {
            accNum = Integer.parseInt(accNumStr);
        }
        catch (NumberFormatException e)
        {
            System.out.println("ERROR: Invalid account number.");
            return;
        }

        Account targetAccount = bank.findAccount(accNum);

        if(targetAccount == null || !targetAccount.getOwner().equals(customer))
        {
            System.out.println("ERROR: Account does not belong to this account.");
            return;
        }

        System.out.println("Do you want to (D)eposit or (W)ithdraw? D/W:");

        String typeChoice = input.nextLine().toUpperCase();
        System.out.println("Enter amount:");
        String amountStr = input.nextLine();
        BigDecimal amount;
        try
        {
            amount = new BigDecimal(amountStr);
            if(amount.compareTo(BigDecimal.ZERO)<=0)
            {
                System.out.println("ERROR: Amount must be greater than 0.");
                return;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("ERROR: Invalid amount.");
            return;
        }

        if(typeChoice.equals("D"))
        {
            targetAccount.deposit(amount);
            System.out.println("Account has been deposited successfully");
        }
        else if(typeChoice.equals("W"))
        {
            boolean success = targetAccount.withdraw(amount);
            if(success)
            {
                System.out.println("SUCCESS: Withdrew " + amount + " from Account #" + accNum);
            }
            else
            {
                System.out.println("FAILURE: Withdrawal failed");
            }
        }
        else
        {
            System.out.println("ERROR: Invalid transaction type selected.");
        }
        System.out.println("New Balance for Account #" + accNum + ": " + targetAccount.getBalance());
    }
}

package com.teambank.bankingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BankingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);

        Scanner input = new Scanner(System.in);
        Bank centralBank = BankingAppService.initializeBank(input);
        User loggedInUser = null;
        while (loggedInUser == null) {
            System.out.println("LOGIN");
            System.out.println("Please enter your username:");
            String username = input.nextLine();
            System.out.println("Please enter your password:");
            String password = input.nextLine();
            User potentialUser = centralBank.findUser(username);
            if (potentialUser != null && potentialUser.login(username, password)) {
                loggedInUser = potentialUser;
                System.out.println("LOGIN SUCCESSFUL" + loggedInUser.username + " (" + loggedInUser.getRole() + ")");
                ;
            } else {
                System.out.println("LOGIN FAILED");
            }
        }

    }
}

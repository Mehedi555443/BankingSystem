package com.teambank.bankingsystem;

public class User
{
    protected String username;
    protected String passwordHash;
    protected String role;
    public User(String username, String passwordHash, String role)
    {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }
    public boolean login(String usernameInput, String passwordInput)
    {
        if(this.username.equals(usernameInput) && this.passwordHash.equals(passwordInput))
        {
            System.out.println(this.username+"Logged in successfully");
            return true;
        }
        System.out.println("Login failed");
        return false;
    }
    public void logout()
    {
        System.out.println(this.username+"Logged out successful");
    }

    public String getRole() {
        return this.role;
    }
}

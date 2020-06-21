/**
 * Dominic Lutz
 * 4/9/2019
 * Prof. Hira Herrington
 * ISYS 216 - 001
 * 
 *      This class creates an account with an id, balance, and dateCreated.
 * 
 * Variables:
 * id - private int - holds the account's id number;
 * balance - private double - holds the account's current balance;
 * annualInterestRate - private static double - holds the interest rate for all accounts;
 * dateCreated - final private - date of creation is assigned when the account 
 *      is created. Cannot be changed once assigned;
 * 
 * public interfaces:
 * Account() - no-arg constructor, sets id and balance to 0 and assigns dateCreated;
 * Account(int newId, double newBalance) - constructor that assigns parameters to id and balance;
 * setId(int newId) - void - sets id to newId;
 * setBalance(double newBalance) - void - sets balance to newBalance;
 * setAnnualInterestRate(double newAnnualInterestRate) - void - sets 
 *      annualInterestRate to newAnnualInterestRate;
 * getId() - int - returns id;
 * getBalance() - double - returns balance;
 * getAnnualInterestRate() - double static- returns annualInterestRate;
 * getDateCreated() - Date - returns dateCreated;
 * getMonthlyInterestRate() - double - returns annualInterestRate divided by 12;
 * withdraw(double withdrawal) - void - subtracts withdrawal from balance;
 * deposit(double deposit) - void - adds deposit to balance;
 */
package dominiclutzassignment4;

import java.util.Date;

public class Account {
    
    // Initialize class variables
    private int id;
    private double balance;
    private static double annualInterestRate = 0;
    final private Date dateCreated;
    
    // No-arg constructor
    public Account() {
        id = 0;
        balance = 0;
        dateCreated = new Date();
    }
    // Constructor that assigns parameters to id and balance
    public Account(int newId, double newBalance) {
        id = newId;
        balance = newBalance;
        dateCreated = new Date();
    }
    
    // sets id to newId
    public void setId(int newId) {
        id = newId;
    }
    
    // sets balance to newBalance
    public void setBalance(double newBalance) {
        balance = newBalance;
    }
    
    // sets annualInterestRate to newAnnualInterestRate
    public static void setAnnualInterestRate(double newAnnualInterestRate) {
        annualInterestRate = newAnnualInterestRate;
    }
    
    // returns id
    public int getId() {
        return id;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }
    
    public Date getDateCreated() {
        return dateCreated;
    }
    
    public double getMonthlyInterestRate() {
        return (annualInterestRate / 12);
    }
    
    public void withdraw(double withdrawal) {
        balance -= withdrawal;
    }
    
    public void deposit(double deposit) {
        balance += deposit;
    }
}

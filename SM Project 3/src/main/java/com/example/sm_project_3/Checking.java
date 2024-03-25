/**
 * Extends the account class without defining any additional instance variables, but defines
 * the constants for interest rate and fee.
 *
 *
 */

package com.example.sm_project_3;

/**
 * Class containing methods and operations for checking accounts.
 */
public class Checking extends Account{

    /**
     * Constant variable defining the interest rate (in decimal form) for checking accounts.
     *
     */
    final double INTEREST_RATE = .01;

    /**
     * Constant variable defining the fee for checking accounts.
     * This fee is waived if balance >= 1000, or if it's a college checking account
     */
    final double FEE = 12;


    /**
     * Constructor for the Checking account, takes in two parameters, a Profile instance
     * and a double variable.
     * @param h     Profile holder as defined in the abstract class Account
     * @param bal   balance variable as defined in the abstract class Account
     */
    public Checking(Profile h, double bal){
        holder = h;
        balance = bal;
    }

    /**
     * Uses the balance and INTEREST_RATE variables to calculate the monthly interest
     * @return product of balance and INTEREST_RATE/12
     */
    public double monthlyInterest(){
        return balance * (INTEREST_RATE/12);
    }


    /**
     * Returns the FEE constant variable
     * @return  FEE constant variable
     */
    public double monthlyFee(){
        return FEE;
    }

    /**
     * Returns type of bank account.
     * @return "Checking"
     */
    public String getType(){
        return "Checking";
    }

    /**
     * Enhanced equals method that checks various aspects of an account.
     * @param other
     * @return true if accounts are equal, false otherwise
     */
    public boolean isEqual(Account other){
        return (getType().equals(other.getType()) && holder.getFullName().equals(other.holder.getFullName()) && holder.getDateOfBirth().toString().equals(other.holder.getDateOfBirth().toString()));
    }

    /**
     * Getter method for the balance of the account.
     * @return
     */
    public double getBalance(){
        return balance;
    }

    /**
     * return type of campus, CC only.
     * @return campus
     */
    public String getCampus(){
        return null;
    }

    /**
     * Increments the withdrawal amount by 1, to be called every time a withdrawal is made.
     */
    public void addWith(){
        return;
    }

    /**
     * helper method to print portion of Money market string.
     * @return money market string
     */
    public String mmString(){
        return "";
    }


}

/**
 * This class represents a Savings account, which has a higher interest rate and monthly fee
 * than a checking account (however, the monthly fee is waived if balance >= $500). The interest
 * rate is also boosted by 0.25% if the holder of the account is loyal.
 *
 */
package com.example.sm_project_3;

/**
 * Class containing methods and operations for Savings accounts.
 */
public class Savings extends Account{
    /**
     * Boolean variable representing loyalty of account holder.
     */
    protected boolean isLoyal;
    /**
     * Default interest rate of a Savings account.
     */
    final double INTEREST_RATE = .04;
    /**
     * Interest rate of a loyal Savings account, increased by .25%.
     */
    final double LOYAL_INTEREST_RATE = .0425;
    /**
     * Monthly fee for a Savings account, waived if balance >= $500.
     */
    final double FEE = 25;

    /**
     * Constructor for a Savings account, takes in 3 parameters, a Profile, a double, and a boolean.
     * @param h     Profile holder, contains first and last name, and D.O.B. of account holder
     * @param bal   double balance, represents amount of money in account
     * @param l     boolean l, represents loyalty status of account holder
     */
    public Savings(Profile h, double bal, boolean l){
        holder = h;
        balance = bal;
        isLoyal = l;
    }

    /**
     * Constructor for Savings class, includes an int l that represents the loyalty of the customer.
     * @param h
     * @param bal
     * @param l 0 for not loyal, 1 for loyal
     */
    public Savings(Profile h, double bal, int l){
        holder = h;
        balance = bal;
        if (l == 0)
            isLoyal = false;
        else
            isLoyal = true;
    }

    /**
     * Returns the monthly fee of this Savings account, the fee is waived if balance >= $500
     * @return  0 if balance >=500, 25 otherwise
     */
    public double monthlyFee(){
        if (balance >= 500)
            return 0;
        else
            return FEE;
    }

    /**
     * Returns monthly interest of account, increased by 0.25% if holder is loyal.
     * @return balance * 4.25/12 if loyal, balance * 4/12 otherwise
     */
    public double monthlyInterest(){
        if (isLoyal)
            return balance * (LOYAL_INTEREST_RATE/12);
        else
            return balance * (INTEREST_RATE/12);
    }

    /**
     * Returns type of bank account.
     * @return "Savings"
     */
    public String getType(){
        return "Savings";
    }

    /**
     * Enhanced equals method that checks various aspects of the accounts.
     * @param other
     * @return true if accounts are equal, false otherwise.
     */
    public boolean isEqual(Account other){
        return (getType().equals(other.getType()) && holder.getFullName().equals(other.holder.getFullName()) && holder.getDateOfBirth().toString().equals(other.holder.getDateOfBirth().toString()));
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
     * helper method to print portion of Savings string.
     * @return money market string
     */
    @Override
    public String mmString(){
        if(isLoyal){
            return ("::is loyal");
        }
        else
            return "";
    }

}

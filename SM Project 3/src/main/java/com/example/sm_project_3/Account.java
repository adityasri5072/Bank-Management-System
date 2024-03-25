/**
 * An abstract class that defines the methods and variables for its child classes. This is the
 * general type of the other account types. Must be 16 or older to open an account of any kind.
 * 
 */

package com.example.sm_project_3;

/**
 * Abstract class containing methods for the different types of accounts.
 */
public abstract class Account {

    /**
     * Protected instance of the profile class, represents the holder of this account.
     * Contains first name, last name, and date of birth. Can be accessed by any subclasses.
     */
    protected Profile holder;

    /**
     * Protected instance variable balance, a double representing the amount of money
     * in the account.
     * Can be accessed by any subclasses of Account.
     */
    protected double balance;

    /**
     * Calculates the monthly interest based on the interest rate of each account type.
     * @return monthly interest in dollars
     */
    public abstract double monthlyInterest();

    /**
     * Returns the value of the monthly fee depending on the account type.
     * @return monthly fee in dollars
     */
    public abstract double monthlyFee();

    /**
     * Returns a string with the type of bank account.
     * @return Type of bank account
     */
    public abstract String getType();

    /**
     * Increments the withdrawal amount by 1, to be called every time a withdrawal is made.
     */
    public abstract void addWith();

    /**
     * helper method to print portion of Money market string.
     * @return money market string
     */
    public abstract String mmString();

    /**
     * return campus string for print, CC only.
     * @return campus string
     */
    public abstract String getCampus();

    public abstract boolean isEqual(Account other);

    /**
     * Returns the balance of the account.
     * @return balance of the account.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Sets the balance for the account.
     * @param d Amount to set as the new balance.
     */
    public void setBalance(double d) {
        this.balance = d;
    }



    public Object getHolder() {
        return holder;
    }

    // Assuming that the `Profile` class has an appropriate `equals` method. If not, that should be added too.
}

/**
 * This class holds the array of accounts for this project and contains the methods used to
 * operate on these classes. It has an initial capacity of 4, but is expanded as necessary.
 *
 * @author Michael Walters
 * @author Aditya Srinivas
 */
package com.example.sm_project_3;

import java.text.DecimalFormat;

/**
 * Database where various accounts will be stored.
 */
public class AccountDatabase {

    // Constants for initial capacity, growth factor, and not found indicator.
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH_FACTOR = 4;
    private static final int NOT_FOUND = -1;

    // Array to hold account objects and variable to track the number of accounts.
    private static Account[] accounts;
    private int numAccounts;

    /**
     * Constructor that initializes an empty account database with an initial capacity.
     */
    public AccountDatabase() {
        this.accounts = new Account[INITIAL_CAPACITY];
        this.numAccounts = 0;
    }

    /**
     * Private helper method to find an account.
     * @param accountToFind The account to find.
     * @return The index of the account, or NOT_FOUND if not found.
     */
    public int find(Account accountToFind, AccountDatabase accs) {


        for (int i = 0; i < accs.getNumAccounts(); i++) {
            if (accs.getAccounts()[i].isEqual(accountToFind)) {

                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Private helper method to expand the accounts array.
     */
    private void grow() {
        Account[] newAccounts = new Account[numAccounts + GROWTH_FACTOR];
        System.arraycopy(accounts, 0, newAccounts, 0, numAccounts);
        accounts = newAccounts;
    }

    /**
     * Method to check if an account exists in the database.
     * @param account The account to check.
     * @return true if the account exists, false otherwise.
     */
    public boolean contains(Account account) {
        return find(account, this) != NOT_FOUND;
    }

    /**
     * Method to open a new account and add it to the database.
     * @param account The account to add.
     * @return true if the account was successfully added, false otherwise.
     */
    public boolean open(Account account) {

        if (contains(account)) {
            return false;
        }
        if (numAccounts >= accounts.length) {
            grow();
        }
        accounts[numAccounts++] = account;
        return true;
    }

    /**
     * Method to close an account and remove it from the database.
     * @param account The account to close.
     * @return true if the account was successfully closed, false otherwise.
     */
    public boolean close(Account account) {
        int index = find(account, this);
        if (index == NOT_FOUND) {
            return false;
        }
        for (int i = index; i < numAccounts - 1; i++) {
            accounts[i] = accounts[i + 1];
        }
        accounts[--numAccounts] = null;
        return true;
    }

    /**
     * Method to withdraw from an account.
     * Note: The provided code seems to suggest the 'Account' object passed to the withdraw
     * function contains the withdrawal amount as its balance. This seems a bit non-intuitive.
     * You might want to reconsider this design.
     * @param account The account from which to withdraw.
     * @return true if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(Account account) {
        int index = find(account, this);
        if (index == NOT_FOUND) {
            return false;
        }
        double withdrawAmt = account.getBalance();
        if (accounts[index].getBalance() < withdrawAmt) {
            return false;
        }
        accounts[index].setBalance(accounts[index].getBalance() - withdrawAmt);

        return true;
    }

    /**
     * Method to deposit into an account.
     * Note: Similar note as for withdraw, the design seems a bit non-intuitive.
     * @param account The account into which to deposit.
     * @return 
     */
    public boolean deposit(Account account) {
        int index = find(account, this);
        if (index != NOT_FOUND) {
            accounts[index].setBalance(accounts[index].getBalance() + account.getBalance());
            return true;
        }
        return false;
    }

    /**
     * This method closes all accounts.
     */
    public void closeAllAccounts(){
        for (int i = 0; i < numAccounts; i++){
            accounts[i] = null;
        }
        numAccounts = 0;
    }


    /**
     * Getter method for array of accounts, helpful in certain methods in TransactionManagerController.
     * @return array of accounts
     */
    public Account[] getAccounts(){
        return accounts;
    }





        /**
     * Sorts accounts based on the account's balance in descending order.
     */
    private void sortAccountsByBalance() {
        for (int i = 0; i < numAccounts - 1; i++) {
            for (int j = 0; j < numAccounts - i - 1; j++) {
                if (accounts[j].getBalance() < accounts[j + 1].getBalance()) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                }
            }
        }
    }



    /**
     * Applies fees and interests to accounts.
     * This is a placeholder method and would need specific logic based on the type of accounts.
     */
    private double applyFeesAndInterests(Account account) {
         if (account instanceof Checking) {
            return account.getBalance() * 0.01;
        } else if (account instanceof Savings) {
            return account.getBalance() * 0.04;
        } else if (account instanceof MoneyMarket) {
            return account.getBalance() * 0.045;
        } else if (account instanceof CollegeChecking) {
            return account.getBalance() * 0.01;
        }
        return 0;  // Default: no interest
    }


    
    /**
     * Returns the total number of accounts in the database.
     * @return Number of accounts in the database.
     */
    public int getNumAccounts() {
        return numAccounts;
    }

    /**
     * Returns the account placed at the given index of accounts[]
     * @param i index of account to be returned
     * @return accoutn at index i
     */
    public Account getAccount(int i) {
        return accounts[i];
    }



    /**
     * Helper method to compute the monthly interest of an account based on the account type.
     * @param account account to compute interest for
     * @return a double representing the amount of interest due.
     */
    private double computeInterest(Account account) {
        // Depending on the account type, compute interest
        if (account instanceof Checking) {
            return account.getBalance() * 0.01;
        } else if (account instanceof Savings) {
            return account.getBalance() * 0.04;
        } else if (account instanceof MoneyMarket) {
            return account.getBalance() * 0.045;
        } else if (account instanceof CollegeChecking) {
            return account.getBalance() * 0.01;
        }
        return 0;  // Default: no interest
    }


}

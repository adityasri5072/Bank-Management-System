/**
 * This is the Controller class, where all the inputs are handled. This clas contains the methods
 * to open, close, withdraw, deposit, and print accounts, among other various helper methods.
 *
 */
package com.example.sm_project_3;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;

import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 * The Transaction manager controller.
 */
public class TransactionManagerController {


    /**
     * These variables represent information to be inputted by the user.
     */
    @FXML
    private TextField accountType, fullName, dob, bal, campusString;

    /**
     * TextArea variable representing the output box where information about accounts will be displayed.
     */
    @FXML
    private TextArea output;

    /**
     * CheckBox representing whether a Savings account is loyal.
     */
    @FXML
    private CheckBox loyalty;


    /**
     * Instance of AccountDatabase where accounts will be stored.
     */
    private AccountDatabase accs = new AccountDatabase();


    /**
     * Method to interpret commands based off user input.
     *
     * @param command the command
     */
    @FXML
    public void interpretCommand(String command){
        switch (command){
            case "O":
                handleOpen(accs);
                break;
            case "C":
                handleClose(accs);
                break;
            case "D":
                handleDeposit(accs);
                break;
            case "W":
                handleWithdraw(accs);
                break;
            case "P":
                output.appendText("*Accounts sorted by account type and profile\n");
                printSorted(accs);
                output.appendText("*End of list\n\n");
                break;
            case "PI":
                output.appendText("List of accounts with fee and monthly interest.\n");
                printIF(accs);
                output.appendText("*End of list\n\n");
                break;
            case "UB":
                output.appendText("List of accounts with fees and interest applied.\n");
                printUB(accs);
                output.appendText("*End of list\n\n");
                break;
            case"Q":
                output.clear();
                return;
            case"F":
                readFile(accs);
                break;
            case "CA":
                output.appendText("All Accounts Closed");
                accs.closeAllAccounts();;
                break;
            default:
        }
    }

    /**
     * Helper method to tell InterpretCommand to execute open method.
     */
    public void openButtonPressed(){
        interpretCommand("O");
    }

    /**
     * Helper method to tell InterpretCommand to execute close method.
     */
    public void closeButtonPressed(){
        interpretCommand("C");
    }

    /**
     * Helper method to tell InterpretCommand to execute withdraw method.
     */
    public void withdrawButtonPressed(){
        interpretCommand("W");
    }

    /**
     * Helper method to tell InterpretCommand to execute deposit method.
     */
    public void depositButtonPressed(){
        interpretCommand("D");
    }

    /**
     * Helper method to tell InterpretCommand to execute print method.
     */
    public void printButtonPressed(){
        interpretCommand("P");
    }

    /**
     * Helper method to tell InterpretCommand to execute print interests and fees method.
     */
    public void printIFButtonPressed(){
        interpretCommand("PI");
    }

    /**
     * Helper method to tell InterpretCommand to execute print updated accounts method.
     */
    public void ubButtonPressed(){
        interpretCommand("UB");
    }

    /**
     * Helper method to tell InterpretCommand to execute quit(clear) method.
     */
    public void quitButtonPressed(){
        interpretCommand("Q");
    }

    /**
     * Helper method to tell InterpretCommand to execute readFile() method.
     */
    public void fileButtonPressed(){
        interpretCommand("F");
    }

    /**
     * Helper method to tell InterpretCommand to close all accounts in database.
     */
    public void closeAllButtonPressed(){
        interpretCommand("CA");
    }


    /**
     * Method to handle opening of an account. Checks for various exceptions and improper inputs.
     *
     * @param accs the accs
     * @return true if successfully opened the account, false otherwise
     */
    @FXML
    public boolean handleOpen(AccountDatabase accs){
        try {
            try {
                String accountType = this.accountType.getText();
                String firstName = this.fullName.getText().substring(0, this.fullName.getText().indexOf(" "));
                String lastName = this.fullName.getText().substring(this.fullName.getText().indexOf(" ") + 1);
                String dob = this.dob.getText();
                if (!isValidDate(dob)) {
                    output.appendText("Invalid Date\n");
                    return false;
                }
                double bal = Double.parseDouble(this.bal.getText());
                if (bal < 0) {
                    output.appendText("Initial Balance cannot be negative");
                    return false;
                }

                Account a;
                if (accountType.equals("CC")) {
                    if (Integer.parseInt(campusString.getText()) > 2 || Integer.parseInt(campusString.getText()) < 0){
                        output.appendText("Invalid campus code");
                        return false;
                    }
                    a = new CollegeChecking(new Profile(firstName, lastName, new Date(dob)), bal, Integer.parseInt(campusString.getText()));

                } else if (accountType.equals("S")) {
                    a = new Savings(new Profile(firstName, lastName, new Date(dob)), bal, getLoyalty());
                } else if (accountType.equals("C")) {
                    a = new Checking(new Profile(firstName, lastName, new Date(dob)), bal);
                } else if (accountType.equals("MM")) {
                    a = new MoneyMarket(new Profile(firstName, lastName, new Date(dob)), bal);
                } else {
                    output.appendText("Invalid account type\n");
                    return false;
                }

                if (accs.open(a)) {
                    output.appendText("Account opened successfully\n");
                    return true;
                }
                else {
                    output.appendText("Account already in database\n");
                    return false;
                }


            } catch (NumberFormatException e) {
                output.appendText("Invalid argument type");
                return false;
            }
        }
        catch (NoSuchElementException e) {
            output.appendText("Not enough arguments");
            return false;
        }

    }

    /**
     * Method to handle closing of an account. Checks for various exceptions and improper inputs.
     * @param accs
     * @return true if successfully closed the account, false otherwise
     */
    @FXML
    private boolean handleClose(AccountDatabase accs){
        try {
            try {
                String accountType = this.accountType.getText();
                String firstName = this.fullName.getText().substring(0, this.fullName.getText().indexOf(" "));
                String lastName = this.fullName.getText().substring(this.fullName.getText().indexOf(" ") + 1);
                String dob = this.dob.getText();
                if (!isValidDate(dob)) {
                    output.appendText("Invalid Date");
                    return false;
                }
                //double bal = Double.parseDouble(this.bal.getText());
                Account a;
                if (accountType.equals("CC")) {
                    if (Integer.parseInt(campusString.getText()) > 2 || Integer.parseInt(campusString.getText()) < 0){
                        output.appendText("Invalid campus code");
                        return false;
                    }
                    a = new CollegeChecking(new Profile(firstName, lastName, new Date(dob)), 0, Integer.parseInt(campusString.getText()));

                } else if (accountType.equals("S")) {
                    a = new Savings(new Profile(firstName, lastName, new Date(dob)), 0, getLoyalty());
                } else if (accountType.equals("C")) {
                    a = new Checking(new Profile(firstName, lastName, new Date(dob)), 0);
                } else if (accountType.equals("MM")) {
                    a = new MoneyMarket(new Profile(firstName, lastName, new Date(dob)), 0);
                } else {
                    output.appendText("Invalid account type\n");
                    return false;
                }

                if (accs.close(a)) {
                    output.appendText("Account closed successfully\n");
                    return true;
                }
                else{
                    output.appendText("Account not found in database\n");
                    return false;
                }

            } catch (NumberFormatException e) {
                output.appendText("Invalid argument type\n");
                return false;
            }
        }
        catch (NoSuchElementException e) {
            output.appendText("Not enough arguments\n");
            return false;
        }

    }

    /**
     * Method to handle withdrawal from an account. Checks for various exceptions and improper inputs.
     * @param accs
     * @return true if successfully withdrew from the account, false otherwise
     */
    @FXML
    private boolean handleWithdraw(AccountDatabase accs){
        try {
            try {
                String accountType = this.accountType.getText();
                String firstName = this.fullName.getText().substring(0, this.fullName.getText().indexOf(" "));
                String lastName = this.fullName.getText().substring(this.fullName.getText().indexOf(" ") + 1);
                String dob = this.dob.getText();
                if (!isValidDate(dob)) {
                    output.appendText("Invalid Date\n");
                    return false;
                }
                double amount = Double.parseDouble(this.bal.getText());
                Account a;

                if (accountType.equals("CC")) {
                    if (Integer.parseInt(campusString.getText()) > 2 || Integer.parseInt(campusString.getText()) < 0){
                        output.appendText("Invalid campus code");
                        return false;
                    }
                    a = new CollegeChecking(new Profile(firstName, lastName, new Date(dob)), amount, Integer.parseInt(campusString.getText()));
                } else if (accountType.equals("S")) {
                    a = new Savings(new Profile(firstName, lastName, new Date(dob)), amount, getLoyalty());
                } else if (accountType.equals("C")) {
                    a = new Checking(new Profile(firstName, lastName, new Date(dob)), amount);
                } else if (accountType.equals("MM")) {
                    a = new MoneyMarket(new Profile(firstName, lastName, new Date(dob)), amount);
                } else {
                    output.appendText("Invalid account type\n");
                    return false;
                }

                if (!accs.contains(a)) {
                    output.appendText("Account not found in the database.\n");
                    return (false);
                }

                if (!accs.withdraw(a)){
                    output.appendText("Failed to Withdraw\n");
                    return false;
                }
                else{
                    output.appendText("withdrawal successful\n");
                    accs.getAccount(accs.find(a, accs)).addWith();
                    return true;
                }

            } catch (NumberFormatException e) {
                output.appendText("Invalid argument type\n");
                return false;
            }
        }catch (NoSuchElementException e) {
            output.appendText("Not enough arguments\n");
            return false;
        }
    }

    /**
     * Method to handle deposit into an account. Checks for various exceptions and improper inputs.
     * @param accs
     * @return true if successfully deposited into the account, false otherwise
     */
    @FXML
    private boolean handleDeposit(AccountDatabase accs){
        try {
            try {
                String accountType = this.accountType.getText();
                String firstName = this.fullName.getText().substring(0, this.fullName.getText().indexOf(" "));
                String lastName = this.fullName.getText().substring(this.fullName.getText().indexOf(" ") + 1);
                String dob = this.dob.getText();
                if (!isValidDate(dob)) {
                    output.appendText("Invalid Date\n");
                    return false;
                }
                double amount = Double.parseDouble(this.bal.getText());
                Account a;

                if (accountType.equals("CC")) {
                    if (Integer.parseInt(campusString.getText()) > 2 || Integer.parseInt(campusString.getText()) < 0){
                        output.appendText("Invalid campus code");
                        return false;
                    }
                    a = new CollegeChecking(new Profile(firstName, lastName, new Date(dob)), amount, Integer.parseInt(campusString.getText()));
                } else if (accountType.equals("S")) {
                    a = new Savings(new Profile(firstName, lastName, new Date(dob)), amount, getLoyalty());
                } else if (accountType.equals("C")) {
                    a = new Checking(new Profile(firstName, lastName, new Date(dob)), amount);
                } else if (accountType.equals("MM")) {
                    a = new MoneyMarket(new Profile(firstName, lastName, new Date(dob)), amount);
                } else {
                    output.appendText("Invalid account type\n");
                    return false;
                }

                if (!accs.contains(a)) {
                    output.appendText("Account not found in the database.\n");
                    return (false);
                }
                if (!accs.deposit(a)){
                    output.appendText("Failed to deposit\n");
                    return false;
                }
                else{
                    output.appendText("Deposit successful");
                    return true;
                }
            } catch (NumberFormatException e) {
                output.appendText("Invalid argument type\n");
                return false;
            }
        }catch (NoSuchElementException e) {
            output.appendText("Not enough arguments\n");
            return false;
        }
    }

    /**
     * Method to read list of accounts from a file. Checks for various exceptions and improper inputs. Currently,
     * this method only reads from its current absolute directory path (Users/michaelwalters/...)
     * @param accs
     * @return true if successfully read accounts from file, false otherwise
     */
    @FXML
    private boolean readFile(AccountDatabase accs) {
        try {
            try {
                try {
                    String line;
                    FileReader fileReader = new FileReader("./src/main/java/com/example/sm_project_3/bankAccounts.txt");
                    BufferedReader br = new BufferedReader(fileReader);

                    while ((line = br.readLine()) != null) {
                        String[] accountLine = line.split(",");
                        String accountType = accountLine[0];
                        String firstName = accountLine[1];

                        String lastName = accountLine[2];
                        String dob = accountLine[3];
                        double bal = Double.parseDouble(accountLine[4]);
                        if (bal < 0) {
                            output.appendText("Initial Balance cannot be negative\n");
                            return false;
                        }
                        String campusString = "";
                        boolean loyalty = false;
                        switch (accountType) {
                            case "CC":
                                campusString = accountLine[5];
                            case "S":
                                loyalty = (Integer.parseInt(accountLine[5]) == 1);
                        }
                        Account a;
                        if (accountType.equals("CC")) {
                            if (Integer.parseInt(campusString) > 2 || Integer.parseInt(campusString) < 0){
                                output.appendText("Invalid campus code");
                                break;
                            }
                            a = new CollegeChecking(new Profile(firstName, lastName, new Date(dob)), bal, Integer.parseInt(campusString));

                        } else if (accountType.equals("S")) {
                            a = new Savings(new Profile(firstName, lastName, new Date(dob)), bal, loyalty);
                        } else if (accountType.equals("C")) {
                            a = new Checking(new Profile(firstName, lastName, new Date(dob)), bal);
                        } else if (accountType.equals("MM")) {
                            a = new MoneyMarket(new Profile(firstName, lastName, new Date(dob)), bal);
                        } else {
                            output.appendText("Invalid account type\n");
                            return false;
                        }
                        if (!accs.open(a))
                            output.appendText("Duplicate account detected\n");

                    }
                }
                catch (ArrayIndexOutOfBoundsException arrout){
                    output.appendText("Accounts opened successfully\n");
                    return true;
                }
                output.appendText("Accounts opened successfully\n");
                return true;
            }
            catch (FileNotFoundException e) {
                output.appendText("File not found\n");
                return false;
            }
        }
        catch(IOException i){
            output.appendText("File not found, IO\n");
            return false;
        }
    }

    /**
     * This is a helper method to check if a given date is valid.
     * @param dateStr
     * @return true of date is valid, false otherwise
     */
    private boolean isValidDate(String dateStr) {
        // Assuming date format is MM/DD/YYYY
        String[] parts = dateStr.split("/");
        if (parts.length != 3)
            return false;

        int month, day, year;
        try {
            month = Integer.parseInt(parts[0]);
            day = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        // Basic validation for month and day
        if (month < 1 || month > 12)
            return false;
        if (day < 1 || day > 31)
            return false;
        // Add more comprehensive date validation if needed

        return true;
    }

    /**
     * This is a helper method to get the loyalty value from the CheckBox.
     * @return 1 if check box is selected, 0 otherwise
     */
    private int getLoyalty(){
        if (loyalty.isSelected())
            return 1;
        else
            return 0;
    }

    /**
     * This method prints all the current accounts in the database in order of Account type and profile. It also
     * checks for various exceptions and improper inputs.
     *
     * @param accs the accs
     */
    public void printSorted(AccountDatabase accs) {
        // Sorting logic will go here.
        if (accs.getNumAccounts() == 0){
            output.appendText("Account database is empty\n");
            return;
        }
        Account[] sortedAccounts = accs.getAccounts();

        int k, j;
        Account temp;
        boolean swapped;
        for (k = 0; k < accs.getNumAccounts() - 1; k++) {
            swapped = false;
            for (j = 0; j < accs.getNumAccounts() - k - 1; j++) {
                if (accs.getAccounts()[j].getType().compareTo(accs.getAccounts()[j + 1].getType()) > 0) {

                    // Swap arr[j] and arr[j+1]
                    temp = accs.getAccounts()[j];
                    accs.getAccounts()[j] = accs.getAccounts()[j + 1];
                    accs.getAccounts()[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }


        DecimalFormat df = new DecimalFormat("#,###.00");

        for (int i = 0; i < accs.getNumAccounts(); i++) {
            switch (accs.getAccounts()[i].getType()){
                case ("College Checking"):
                    output.appendText(accs.getAccounts()[i].getType() + "::" + accs.getAccounts()[i].holder.getFullName() + " " + accs.getAccounts()[i].holder.getDateOfBirth().toString() +
                            "::Balance $" + df.format(accs.getAccounts()[i].getBalance()) + "::" + accs.getAccounts()[i].getCampus() + "\n");

                    break;
                case ("Money Market"):
                    //accs.getAccounts()[i].addWith();
                    output.appendText(accs.getAccounts()[i].getType() + "::" + accs.getAccounts()[i].holder.getFullName() + " " + accs.getAccounts()[i].holder.getDateOfBirth().toString() +
                            "::Balance $" + df.format(accs.getAccounts()[i].getBalance()) + "::" + accs.getAccounts()[i].mmString() + "\n");
                    break;
                case ("Savings"):
                    output.appendText(accs.getAccounts()[i].getType() + "::" + accs.getAccounts()[i].holder.getFullName() + " " + accs.getAccounts()[i].holder.getDateOfBirth().toString() +
                            "::Balance $" + df.format(accs.getAccounts()[i].getBalance()) + accs.getAccounts()[i].mmString() + "\n");
                    break;
                default:
                    output.appendText(accs.getAccounts()[i].getType() + "::" + accs.getAccounts()[i].holder.getFullName() + " " + accs.getAccounts()[i].holder.getDateOfBirth().toString() +
                            "::Balance $" + df.format(accs.getAccounts()[i].getBalance()) + "\n");

            }
        }
    }

    /**
     * This method prints the accounts with the monthly interests and fees attached.
     *
     * @param accs the accs
     */
    public void printIF(AccountDatabase accs){
        if (accs.getNumAccounts() == 0){
            output.appendText("Account database is empty\n");
            return;
        }
        DecimalFormat df = new DecimalFormat("#,###.00");

        for (int i = 0; i < accs.getNumAccounts(); i++) {
            switch (accs.getAccounts()[i].getType()){
                case ("College Checking"):
                    output.appendText(accs.getAccounts()[i].getType() + "::" + accs.getAccounts()[i].holder.getFullName() + " " + accs.getAccounts()[i].holder.getDateOfBirth().toString() +
                            "::Balance $" + df.format(accs.getAccounts()[i].getBalance()) + "::" + accs.getAccounts()[i].getCampus() +
                            "::fee $" + df.format(accs.getAccounts()[i].monthlyFee()) +
                            "::monthly interest $" + df.format(accs.getAccounts()[i].monthlyInterest()) + "\n");
                    break;
                case ("Money Market"):
                    //accs.getAccounts()[i].addWith();
                    output.appendText(accs.getAccounts()[i].getType() + "::" + accs.getAccounts()[i].holder.getFullName() + " " + accs.getAccounts()[i].holder.getDateOfBirth().toString() +
                            "::Balance $" + df.format(accs.getAccounts()[i].getBalance()) + "::" + accs.getAccounts()[i].mmString() +
                            "::fee $" + df.format(accs.getAccounts()[i].monthlyFee()) +
                            "::monthly interest $" + df.format(accs.getAccounts()[i].monthlyInterest()) + "\n");
                    break;
                case ("Savings"):
                    output.appendText(accs.getAccounts()[i].getType() + "::" + accs.getAccounts()[i].holder.getFullName() + " " + accs.getAccounts()[i].holder.getDateOfBirth().toString() +
                            "::Balance $" + df.format(accs.getAccounts()[i].getBalance()) + accs.getAccounts()[i].mmString() +
                            "::fee $" + df.format(accs.getAccounts()[i].monthlyFee()) +
                            "::monthly interest $" + df.format(accs.getAccounts()[i].monthlyInterest()) + "\n");
                    break;
                default:
                    output.appendText(accs.getAccounts()[i].getType() + "::" + accs.getAccounts()[i].holder.getFullName() + " " + accs.getAccounts()[i].holder.getDateOfBirth().toString() +
                            "::Balance $" + df.format(accs.getAccounts()[i].getBalance()) + "::fee $" + df.format(accs.getAccounts()[i].monthlyFee()) +
                            "::monthly interest $" + df.format(accs.getAccounts()[i].monthlyInterest()) + "\n");
            }
        }
    }

    /**
     * This method applies the monthly interests and fees to each account in the database, then prints
     * the updated values.
     *
     * @param accs the accs
     */
    public void printUB(AccountDatabase accs){
        if (accs.getNumAccounts() == 0){
            output.appendText("Account database is empty\n");
            return;
        }
        for (int i = 0; i < accs.getNumAccounts(); i++) {
            accs.getAccounts()[i].setBalance(accs.getAccounts()[i].getBalance() - accs.getAccounts()[i].monthlyFee() + accs.getAccounts()[i].monthlyInterest());

        }
        printIF(accs);
    }


}
/**
 * The CollegeChecking class is a subclass of the Checking class, and it denotes a checking
 * account specifically for a college student. This type of account has no account fee.
 * Must be under the age of 24 to open this kind of account, a person cannot hold a college
 * checking and a regular checking at the same time.
 *
 */

package com.example.sm_project_3;

/**
 * Class containing methods for college checking accounts.
 */
public class CollegeChecking extends Checking{

    /**
     * Campus enum that will store the full name of the campus based on the campus.
     * code given.
     */
    private Campus campus;

    /**
     * Interest Rate (in decimal), which is 1% for checking accounts.
     */
    final double INTEREST_RATE = .01;

    /**
     * monthly fee for an account, which is 0 for CollegeChecking accounts.
     */
    final double FEE = 0;

    /**
     * Constructor for CollegeChecking accounts, creates instances of Profile and balance variables
     * as requested by the Account abstract class.
     * @param h     Profile variable containing first and last name and D.O.B.
     * @param bal   Double containing the balance, amt of money in account
     */
    public CollegeChecking(Profile h, double bal){
        super(h, bal);
    }

    /**
     * Constructor containing an integer cVal that represents the campus code, automatically sets
     * campus from campus code.
     * @param h
     * @param bal
     * @param cVal
     */
    public CollegeChecking(Profile h, double bal, int cVal){
        super(h, bal);
        setCampus(cVal);
    }

    /**
     * Overrides Checking's monthlyFee method because CollegeChecking accounts have no monthly fee.
     * @return FEE, which is 0 for College accounts
     */
    @Override
    public double monthlyFee(){
        return FEE;
    }

    /**
     * Returns type of bank account.
     * @return "College Checking"
     */
    @Override
    public String getType(){
        return "College Checking";
    }

    /* might be unnecessary
    public double monthlyInterest(){
        return balance * (INTEREST_RATE/12);
    }*/

    /**
     * Helper method to set the campus based on the given campus code.
     * @param i 0 for NB, 1 for NWK, 2 for CAM
     */
    public void setCampus(int i){
        switch(i){
            case 0:
                campus = Campus.NB;
                break;
            case 1:
                campus = Campus.NWK;
                break;
            case 2:
                campus = Campus.CAM;
                break;
            default:
        }

    }

    /**
     * Helper method that returns the campus in String form.
     * @return campus String
     */
    @Override
    public String getCampus(){
        return campus.toString();
    }

    /**
     * Enhanced equals method that compares various aspects of the accounts.
     * @param other
     * @return true if accounts are equal, false otherwise.
     */
    public boolean isEqual(Account other){
        return (getType().equals(other.getType()) && holder.getFullName().equals(other.holder.getFullName()) && holder.getDateOfBirth().toString().equals(other.holder.getDateOfBirth().toString()));
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

/**
 * The MoneyMarket class is a subclass of Savings. It is a version of a savings account with
 * a slightly higher interest rate and a minimum initial deposit of 2000. Also, this account
 * has the loyalty status by default, raising its initial 4.5% interest rate to 4.75%, but
 * if the balance drops below 2000, it loses this status. Also, the fee is waived if
 * balance >= 2000, and there is an extra $10 fee if number of withdrawals > 3
 *
 */
package com.example.sm_project_3;

/**
 * Class containing methods and operations for money market accounts.
 */
public class MoneyMarket extends Savings{
    /**
     * Number of withdrawals in a period.
     */
    private int withdrawal;
    /**
     * Regular interest rate for MoneyMarket account, 4.5%
     */
    final double INTEREST_RATE = .045;
    /**
     * Loyal interest rate for MoneyMarket account, 4.75%
     */
    final double LOYAL_INTEREST_RATE = .0475;
    /**
     * Fee for money market account, $25 but waived if bal >= 2000.
     */
    final double FEE = 25;

    /**
     * Constructor for money market account, contains Profile holder and double balance. Default
     * loyalty status for MoneyMarket accounts is true, so sends true for isLoyal in Savings
     * @param h     Profile holder, contains first and last name and D.O.B.
     * @param bal   double balance, amount of money in account
     */
    public MoneyMarket(Profile h, double bal){
        super(h, bal, true);
        withdrawal = 0;
    }

    /**
     * Returns monthly fee for this account, overrides Savings' monthly fee method because
     * MoneyMarket accounts have different conditions for fees.
     * @return 25 if balance less than 2000, plus a $10 charge if withdrawal > 3
     */
    @Override
    public double monthlyFee(){
        double fee = 0;
        if (balance < 2000){
            fee += FEE;
        }
        if (withdrawal > 3){
            fee += 10;
        }
        return fee;
    }

    /**
     * Returns monthly interest due to account.
     * @return balance * monthly interest rate, taking into account loyalty status
     */
    @Override
    public double monthlyInterest(){
        if (isLoyal)
            return balance * (LOYAL_INTEREST_RATE/12);
        else
            return balance * (INTEREST_RATE/12);
    }

    /**
     * Increments the withdrawal amount by 1, to be called every time a withdrawal is made.
     */
    @Override
    public void addWith(){
        withdrawal++;
        checkBal();
    }

    /**
     * return number of withdrawals
     * @return num withdrawals
     */
    public int getWithdrawal() { return withdrawal;}

    /**
     * Updates loyalty status depending on balance, should be called every time balance
     * is changed.
     */
    public void checkBal(){
        isLoyal = !(balance < 2000);
    }

    /**
     * Returns type of bank account.
     * @return "Money Market"
     */
    @Override
    public String getType(){
        return "Money Market";
    }

    //check if there needs to be a monthlyInterest method here, or if one in the superclass works

    /**
     * Enhanced equals method to compare various aspects of the accounts.
     * @param other
     * @return true if accounts are equal, false otherwise
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
     * helper method to print portion of Money market string.
     * @return money market string
     */
    @Override
    public String mmString(){
        if (isLoyal){
            return "is loyal:: withdrawal: " + withdrawal;
        }
        else
            return "not loyal:: withdrawal: " + withdrawal;
    }



}

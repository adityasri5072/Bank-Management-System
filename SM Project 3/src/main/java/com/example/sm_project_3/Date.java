/**
 * This is the Date class, which takes in the date and creates a calendar object that contains the month, day, and year
 *
 */

package com.example.sm_project_3;

import java.util.Calendar;

import java.util.Scanner;

import java.util.StringTokenizer;

/**
 * Class that handles the operation of Date objects, for date of births.
 */
public class Date implements Comparable<Date>
{
    /**
     * Integer representing the year.
     */
    private int year;
    /**
     * Integer representing the month (1 = Jan, 2 = Feb, etc.).
     */
    private int month;
    /**
     * Integer representing the day of the month
     */
    private int day;
    /**
     * Calendar instance using the Calendar library.
     */
    private Calendar cal;

    /**
     * Constructor for the date class, takes in 3 parameters and makes a calendar from them.
     * @param y     int representing the year
     * @param m     int representing the month
     * @param d     int representing the day
     */
    public Date(int y, int m, int d)
    {
        year = y;
        month = m;
        day = d;
        makeCal(y, m, d);
    }

    /**
     * Another constructor for the date class, this time taking a single string of the form
     * "MM/DD/YYYY". Separates the 3 parts of the string using the "/".
     * @param newDate   date String in form: "MM/DD/YYYY"
     */
    public Date(String newDate)
    {
        String[] dateParts = newDate.split("/");

        try {
            month = Integer.parseInt(dateParts[0]);
            day = Integer.parseInt(dateParts[1]);
            year = Integer.parseInt(dateParts[2]);
        } catch (NumberFormatException e) {
        }

    }

    public Date(Date date) {
    }

    private String[] split(String string) {
        return null;
    }

    /**
     * Getter method for the year.
     * @return year
     */
    private int getYear(){return year;}

    /**
     * Getter method for the month.
     * @return month in int form (1 = Jan, 2 = Feb, etc.)
     */
    private int getMonth(){return month;}

    /**
     * Getter method for the day.
     * @return day of the month
     */
    private int getDay(){return day;}

    /**
     * Uses the calendar library's format to store the given event date into a calendar
     * @param year      year
     * @param month     month in int form (1 = Jan, 2 = Feb, etc.)
     * @param date      day of the month
     */
    private void makeCal(int year, int month, int date)
    {
        switch(month)
        {
            case 1:
                cal.set(year, Calendar.JANUARY, date);
            case 2:
                cal.set(year, Calendar.FEBRUARY, date);
            case 3:
                cal.set(year, Calendar.MARCH, date);
            case 4:
                cal.set(year, Calendar.APRIL, date);
            case 5:
                cal.set(year, Calendar.MAY, date);
            case 6:
                cal.set(year, Calendar.JUNE, date);
            case 7:
                cal.set(year, Calendar.JULY, date);
            case 8:
                cal.set(year, Calendar.AUGUST, date);
            case 9:
                cal.set(year, Calendar.SEPTEMBER, date);
            case 10:
                cal.set(year, Calendar.OCTOBER, date);
            case 11:
                cal.set(year, Calendar.NOVEMBER, date);
            case 12:
                cal.set(year, Calendar.DECEMBER, date);
        }

    }

    /**
     * Checks if the given date is valid.
     * @return false if incorrect number of days for specific month (taking into account leap year)
     * OR month out of range (not between 1 and 12) OR negative year, true otherwise.
     */
    public boolean isValid()
    {
        boolean output = true;

        //checks for leap year
        boolean isLeapYear = false;
        if (year % 4 == 0)
        {
            if (year % 100 == 0)
            {
                if (year % 400 == 0)
                {
                    isLeapYear = true;
                }
            }
            else
            {
                isLeapYear = true;
            }
        }

        //this section checks if the date contains a valid number of days
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        { //if a month with 31 days
            output = (day > 0 && day < 32);
        }
        else if(month == 2 && isLeapYear) //if february and leap year
        {
            output = (day > 0 && day < 30); //should be between 1 and 29 days, inclusive
        }
        else if (month == 2 && !isLeapYear) //if february and not leap year
        {
            output = (day > 0 && day < 29); //should be between 1 and 28 days, inclusive
        }
        else //only the 30-day months are left
        {
            output = (day > 0 && day < 31);
        }

        if (!output)
            return output; //returns output if it is already false


        //this makes sure the given month is between 1 and 12
        if (!(month > 0 && month < 13))
        {
            output = false;
        }
        if (!(year > 0)){
            output = false;
        }

        return output;
    }

    /**
     * Overrides the compareTo method, checks if this date comes before or after the date
     * passed in the parameter.
     * @param newDate the date to be compared.
     * @return      -1 if this date comes before newDate date, 0 if dates are the same,
     * 1 if this date comes after newDate
     */
    @Override
    public int compareTo(Date newDate) //if newDate comes before this date, outputs 1
    {
        int output = 0;
        if (year < newDate.getYear())
        {
            output = -1;
        }
        else if (year > newDate.getYear())
        {
            output = 1;
        }
        else{
            if (month < newDate.getMonth())
            {
                output = -1;
            }
            else if (month > newDate.getMonth())
            {
                output = 1;
            }
            else{
                if (day < newDate.getDay())
                {
                    output = -1;
                }
                else if (day > newDate.getDay())
                {
                    output = 1;
                }
            }
        }
        return output;
    }

    /**
     * Overrides the default toString method, outputs the date with given format.
     * @return  date with format: MM/DD/YYYY
     */
    @Override
    public String toString()
    {
        return (month + "/" + day + "/" + year);
    }

    /**
     * Testbed main, used to test this individual class's functionality.
     * @param args  arguments given by user
     */
    public static void main(String[] args)
    {

    }


}


/**
 * Defines the profile of an account holder, contains variables for the first and last name, as
 * well as the holder's date of birth.
 *
 */

package com.example.sm_project_3;

/**
 * Class containing methods and operations for the profile of each account holder.
 */
public class Profile implements Comparable<Profile>{
    /**
     * String representing first name.
     */
    private String fname;
    /**
     * String representing last name.
     */
    private String lname;
    /**
     * Date instance representing date of birth, initialized in constructor
     */
    private Date dob;

    /**
     * Constructor for  Profile, takes in two strings representing first and last name, and a
     * String representing the date.
     * @param f first name
     * @param l last name
     * @param date D.O.B. in format MM/DD/YYYY
     */
    public Profile(String f, String l, Date date){
        fname = f;
        lname = l;
        dob = date;
    }




    /**
     * Overrides default toString method in order to print in the required format
     * @return "firstName lastName D.O.B."
     */
    @Override
    public String toString(){
        return fname + " " + lname + " " + dob.toString();
    }

    /**
     * Checks if this Profile contains the same values as Profile in the parameter.
     * @param other the Profile to be compared.
     * @return      0 if the profiles contain the same name (ignoring case) and date of birth,
     *              -1   otherwise
     */
    @Override
    public int compareTo(Profile other){

        if (fname.equalsIgnoreCase(other.fname) && lname.equalsIgnoreCase(other.lname) && (dob.compareTo(other.dob) == 0))
            return 0;
        switch (lname.compareTo(other.lname)) {
            case 1: //this comes before other
                return 1;
            case -1:
                return -1;
            case 0:
                switch(fname.compareTo(other.fname)){
                    case 1:
                        return 1;
                    case -1:
                        return -1;
                    case 0:
                        return 0;
                }
        }
        return 0; //should never reach here
    }

    public Object getFirstName() {
        return fname;
    }

    public Object getLastName() {
        return lname;
    }

    public String getFullName() { return fname + " " + lname;}

    public Date getDateOfBirth() {
        return dob;
    }

}

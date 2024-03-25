/**
 * This enum holds the possible values of Campus used in the College Checking class.
 *
 */

package com.example.sm_project_3;

public enum Campus {
    NB("NEW_BRUNSWICK"),
    NWK("NEWARK"),
    CAM("CAMDEN");

    /**
     * Description for enum.
     */
    private String campusName;

    /**
     * Constructor that takes in a String representing the full name of the Campus.
     * @param cN    full name of the campus
     */
    private Campus(String cN){
        campusName = cN;
    }

    /**
     * Constructor that takes in an int representing the campus code.
     * @param i     campus code as inputted by user, should be 0, 1, or 2
     */
    private Campus(int i){
        setValue(i);
    }

    /**
     * Sets the value of campusName depending on the campus code given by the constructor.
     * @param i     0 for New Brunswick, 1 for Newark, 2 for Camden
     */
    public void setValue(int i){
        switch(i){
            case 0:
                campusName = "New Brunswick";
                return;
            case 1:
                campusName = "Newark";
                return;
            case 2:
                campusName = "Camden";
                return;
            default:
        }
    }

    /**
     * Allows the full name of the campus to be printed from the enum class.
     * @return     String campusName, full name of campus
     */
    public String toString(){return campusName;}

}

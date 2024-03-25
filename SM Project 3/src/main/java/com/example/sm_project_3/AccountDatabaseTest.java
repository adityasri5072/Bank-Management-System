/**
 * This is the JUnit test class for the AccountDatabase class. This class will test the close() method
 * in AccountDatabase.
 *
 */

package com.example.sm_project_3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class AccountDatabaseTest {

    /**
     * Initializes and instance of Account Database to be used by the tester methods
     */
    AccountDatabase ad = new AccountDatabase();

    /**
     * Runs at the beginning of the JUnit test class, opens two accounts with the given information.
     */
    @BeforeEach
    void setUp(){
        ad.open(new Checking(new Profile("Mike", "Walters", new Date("07/23/2003")), 2000));

        ad.open(new Checking(new Profile("Justin", "Chey", new Date("06/01/2003")), 3000));

        //ad.open(new Checking(new Profile("Phil", "Valencia", new Date("08/18/2002")), 4000));
    }

    /**
     * Tests the close method in the AccountDatabase class. Checks to make sure the close method properly accounts for
     * differences in name, date of birth, and balance. Additionally, checks that an account is properly removed
     * after it is closed.
     */
    @Test
    void close() {
        assertFalse(ad.close(new Checking(new Profile("Mlke", "Walters", new Date("07/23/2003")), 2000)));
        assertFalse(ad.close(new Checking(new Profile("Mike", "Walters", new Date("07/23/2003")), 3000)));
        assertFalse(ad.close(new Checking(new Profile("Mike", "Walters", new Date("07/24/2003")), 2000)));
        assertTrue(ad.close(new Checking(new Profile("Mike", "Walters", new Date("07/23/2003")), 2000)));
        assertFalse(ad.close(new Checking(new Profile("Mike", "Walters", new Date("07/23/2003")), 2000)));

        assertFalse(ad.close(new Checking(new Profile("Jxstin", "Chey", new Date("06/01/2003")), 3000)));
        assertFalse(ad.close(new Checking(new Profile("Justin", "Chey", new Date("06/01/2003")), 4000)));
        assertFalse(ad.close(new Checking(new Profile("Justin", "Chey", new Date("06/02/2003")), 3000)));
        assertTrue(ad.close(new Checking(new Profile("Justin", "Chey", new Date("06/01/2003")), 3000)));
        assertFalse(ad.close(new Checking(new Profile("Justin", "Chey", new Date("06/01/2003")), 3000)));

    }
}
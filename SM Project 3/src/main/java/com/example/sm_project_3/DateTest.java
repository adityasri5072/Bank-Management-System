/**
 * This is the JUnit test class for the Date class. It will test the isValid() method using various test cases.
 *
 */

package com.example.sm_project_3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class DateTest {

    @Test
    void isValid() {
        Date date;
        date = new Date("11/01/2023"); //valid date
        assertTrue(date.isValid());

        date = new Date("13/01/2023"); //invalid date, month out of range
        assertFalse(date.isValid());

        date = new Date("11/32/2023"); //invalid date, day out of range
        assertFalse(date.isValid());

        date = new Date("11/-1/2023"); //invalid date, day out of range
        assertFalse(date.isValid());

        date = new Date("11/01/-1"); //invalid date, year out of range
        assertFalse(date.isValid());

        date = new Date("02/29/2023"); //invalid date, not a leap year
        assertFalse(date.isValid());

        date = new Date("02/29/2024"); //valid date, is a leap year
        assertTrue(date.isValid());

    }
}

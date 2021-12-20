// Regular tests for Epic feature 2

package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Epic Feature 2 Regular Tests
 * The methods will be provided correct input to see whether they would process it
 *
 * @author: Daniel Dovhun
 */

public class Epic2RegularTests {
    static Facade facade;
    static String[][] userDetails = {
            {"john.doe@gmail.com", "mary.jane@yahoo.com", "sem.mogilevich@mafiamail.com"}, // Username
            {"John Doe", "Mary Jane", "Semion Mogilevich"},                                // Full name
            {"UnknownPhantom897", "NonExistentPerson888", "AcceptablePassword894"},        // Password
            {"+46 72-373 89 30", "+46 72-373 46 52", "+46 70-785 79 45"},                  // Phone number
            {                                                                              // Address
                    "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden",
                    "Somethingplatsen 9 2253, 131 41 Nacka, Sweden",
                    "Vikingtorget 2 1101, 417 43 Göteborg, Sweden"
            },
            {"19890213-1032", "19930101-8956", "19460630-9765"}                            // Social security number
    };

    @BeforeAll
    public static void setup() {
        facade = new Facade();
    }

    @Test
    public void createUserAccountTest() {
        String expectedValue = "New account for john.doe@gmail.com was successfully created.";
        String actualValue = facade.createUserAccount(userDetails[0][0], userDetails[1][0], userDetails[2][0], userDetails[2][0],
                userDetails[3][0], userDetails[4][0], userDetails[5][0]);
        assertEquals(expectedValue, actualValue);

        expectedValue = "New account for mary.jane@yahoo.com was successfully created.";
        actualValue = facade.createUserAccount(userDetails[0][1], userDetails[1][1], userDetails[2][1], userDetails[2][1],
                userDetails[3][1], userDetails[4][1], userDetails[5][1]);
        assertEquals(expectedValue, actualValue);

        expectedValue = "New account for sem.mogilevich@mafiamail.com was successfully created.";
        actualValue = facade.createUserAccount(userDetails[0][2], userDetails[1][2], userDetails[2][2], userDetails[2][2],
                userDetails[3][2], userDetails[4][2], userDetails[5][2]);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void deleteUserAccountTest() {
        String expectedValue = "Account for john.doe@gmail.com has been successfully terminated.";
        String actualValue = facade.deleteUserAccount("", "john.doe@gmail.com", "John Doe", true);
        assertEquals(expectedValue, actualValue);

        expectedValue = "Account for mary.jane@yahoo.com has been successfully terminated.";
        actualValue = facade.deleteUserAccount("2", "mary.jane@yahoo.com", "Mary Jane", true);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void resetUserPasswordTest() {
        String expectedValue = "Password reset for ‘sem.mogilevich@mafiamail.com’ was successful.";
        String actualValue = facade.resetUserPassword("3", userDetails[0][2], "NewPassword123", "NewPassword123");
        assertEquals(expectedValue, actualValue);
    }
}

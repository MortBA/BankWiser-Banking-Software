/*
Alternative tests for Epic Feature 1.
There should be only one test for US 1.1
 */

package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EpicOneAlternativeTests {
    static Facade facade;
    static String[][] userDetails = {
            {"john.doe@gmail.com", "mary.jane@yahoo.com"}, // Username
            {"John Doe", "Mary Jane"},                     // Full name
            {"UnknownPhantom897", "NonExistentPerson888"}, // Password
            {"+46 72-373 89 30", "+46 72-373 46 52"},      // Phone number
            {                                              // Address
                    "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden",
                    "Somethingplatsen 9 2253, 131 41 Nacka, Sweden"
            },
            {"890213-1032", "9301018956"}                  // Social security number
    };

    @BeforeAll
    public static void setup() {
        facade.createUserAccount(userDetails[0][0], userDetails[1][0], userDetails[2][0], userDetails[2][0],
                userDetails[3][0], userDetails[4][0], userDetails[5][0]);
        facade.createUserAccount(userDetails[0][1], userDetails[1][1], userDetails[2][1], userDetails[2][1],
                userDetails[3][1], userDetails[4][1], userDetails[5][1]);
    }

    @Test
    public void userLoginTest() {
        String expectedValue = "User johndoe@gmail.com could not be logged in. Invalid username or password.";
        String actualValue = facade.userLogin("johndoe@gmail.com", userDetails[0][1]);
        assertEquals(expectedValue, actualValue);

        expectedValue = "User mary.jane@yahoo.com could not be logged in. Invalid username or password.";
        actualValue = facade.userLogin(userDetails[0][1], "WrongPassword123");
    }
}

package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Regular tests for Epic Feature 2.
 * The methods will be provided correct input to see whether they would process it
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic2RegularTests {
    static Facade facade;

    @BeforeAll
    public static void setup() {
        facade = new Facade();
    }

    @Test
    public void createUserAccountTest() {
        String expectedValue = "New account for john.doe@gmail.com was successfully created.";
        String actualValue = facade.createUserAccount("john.doe@gmail.com", "John Doe", "Password123", "Password123","+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-1032");
        assertEquals(expectedValue, actualValue);

        expectedValue = "New account for mary.jane@yahoo.com was successfully created.";
        actualValue = facade.createUserAccount("mary.jane@yahoo.com", "Mary Jane", "Password123", "Password123","+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-5698");
        assertEquals(expectedValue, actualValue);

        expectedValue = "New account for sem.mogilevich@mafiamail.com was successfully created.";
        actualValue = facade.createUserAccount("sem.mogilevich@mafiamail.com", "Semion Mogilevich", "AcceptablePassword894", "AcceptablePassword894", "+46 70-785 79 45", "Vikingtorget 2 1101, 417 43 Göteborg, Sweden", "19460630-9765");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void deleteUserAccountTest() {
        String expectedValue = "Account for john.doe@gmail.com has been successfully terminated.";
        String actualValue = facade.deleteUserAccount("john.doe@gmail.com");
        assertEquals(expectedValue, actualValue);

        expectedValue = "Account for mary.jane@yahoo.com has been successfully terminated.";
        actualValue = facade.deleteUserAccount("mary.jane@yahoo.com");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void resetUserPasswordTest() {
        String expectedValue = "Password reset for ‘sem.mogilevich@mafiamail.com’ was successful.";
        String actualValue = facade.resetUserPassword("", "AcceptablePassword894", "NewPassword123", "NewPassword123");
        assertEquals(expectedValue, actualValue);
    }
}

// Alternative tests for Epic Feature 2

package com.logic.bankwiser.facade;

/*
 * Epic Feature 2 Alternative Tests
 * The tested method will be provided incorrect input (e.g. an input field may have been left empty or the PIN code is too short)
 * to see if the error is noticed and an appropriate output is generated.
 *
 * @author: Daniel Dovhun
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Epic2AlternativeTests {
    static Facade facade;

    @BeforeAll
    public static void setup() {
        facade.createUserAccount("john.doe@gmail.com", "John Doe", "Password123", "Password123",
                "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-1032");
    }

    @Test
    public void createUserAccountTest() {
        String expectedValue = "A user with these details already exists.";
        String actualValue = facade.createUserAccount("john.doe@gmail.com", "John Doe", "Password123", "Password123",
                "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-1032");
        assertEquals(expectedValue, actualValue);

        expectedValue = "Email address is invalid";
        actualValue = facade.createUserAccount("mary.janeyahoo.com", "Mary Jane", "Password123", "Password123",
                "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-5698");
        assertEquals(expectedValue, actualValue);

        expectedValue = "The passwords don't match";
        actualValue = facade.createUserAccount("mary.janeyahoo.com", "Mary Jane", "ThePassword123", "Password123",
                "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-5698");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void resetUserPassword() {
        String expectedValue = "Approval signature is missing.";
        String actualValue = facade.resetUserPassword("1","john.doe@gmail.com", "Password456", "Password123");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void deleteUserAccountTest() {
        String expectedValue = "Email address is invalid";
        String actualValue = facade.createUserAccount("mary.janeyahoo.com", "Mary Jane", "ThePassword123", "Password123",
                "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-5698");
        assertEquals(expectedValue, actualValue);

        expectedValue = "User with this username wasn’t found.";
        actualValue = facade.deleteUserAccount("2","mary.jane@zmail.com", "Mary Jane", true);
        assertEquals(expectedValue, actualValue);

        expectedValue = "Approval signature is missing.";
        actualValue = facade.deleteUserAccount("","mary.jane@yahoo.com", "Mary Jane", true);
        assertEquals(expectedValue, actualValue);

        expectedValue = "Approval signature is missing.";
        actualValue = facade.deleteUserAccount("","john.doe@gmail.com", "John Doe",  false);
        assertEquals(expectedValue, actualValue);
    }
}

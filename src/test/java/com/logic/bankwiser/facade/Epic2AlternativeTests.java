package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Alternative tests for Epic Feature 2.
 * The tested method will be provided incorrect input (e.g. an input field may have been left empty or the PIN code is too short)
 * to see if the error is noticed and an appropriate output is generated.
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic2AlternativeTests {
    private Facade facade;

    @BeforeEach
    public void setup() {
        facade = new Facade(true);

        facade.createUserAccount("john.doe@gmail.com", "John Doe", "Password123", "Password123",
                "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-1032");
    }

    @Test
    public void createUserAccountTest() {
        String expectedValue = "A user with these details already exists.";
        String actualValue = facade.createUserAccount("john.doe@gmail.com", "John Doe", "Password123", "Password123",
                "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-1032");
        assertEquals(expectedValue, actualValue);

        expectedValue = "Email address is invalid.";
        actualValue = facade.createUserAccount("mary.janeyahoo.com", "Mary Jane", "Password123", "Password123",
                "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-5698");
        assertEquals(expectedValue, actualValue);

        expectedValue = "The passwords don't match.";
        actualValue = facade.createUserAccount("mary.jane@yahoo.com", "Mary Jane", "ThePassword123", "Password123",
                "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-5698");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void resetUserPassword() {
        String expectedValue = "A user with that email does not exist.";
        String actualValue = facade.resetUserPassword("leroy@gmail.com");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void deleteUserAccountTest() {
        String expectedValue = "A user with that email does not exist.";
        String actualValue = facade.deleteUserAccount("mary.janeyahoo.com");
        assertEquals(expectedValue, actualValue);

        expectedValue = "A user with that email does not exist.";
        actualValue = facade.deleteUserAccount("2");
        assertEquals(expectedValue, actualValue);
    }
}

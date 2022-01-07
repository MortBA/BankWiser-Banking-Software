package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Alternative tests for Epic Feature 1.
 * There should be only one test for US 1.1
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic1AlternativeTests {
    private Facade facade;

    @BeforeEach
    public void setup() {
        facade = new Facade(true);
        facade.createUserAccount("john.doe@gmail.com", "John Doe", "UnknownPhantom897", "UnknownPhantom897",
                "+46 72-373 89 30", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "890213-1032");
        facade.createUserAccount("mary.jane@yahoo.com", "Mary Jane", "NonExistentPerson888", "NonExistentPerson888",
                "+46 72-373 46 52", "Somethingplatsen 9 2253, 131 41 Nacka, Sweden", "9301018956");
    }

    @Test
    public void userLoginTest() {
        String expectedValue = "No account is registered with that email address.";
        String actualValue = facade.userLogin("johndoe@gmail.com", "UnknownPhantom897");
        assertEquals(expectedValue, actualValue);

        expectedValue = "Password is incorrect.";
        actualValue = facade.userLogin("mary.jane@yahoo.com", "WrongPassword123");
        assertEquals(expectedValue, actualValue);
    }
}

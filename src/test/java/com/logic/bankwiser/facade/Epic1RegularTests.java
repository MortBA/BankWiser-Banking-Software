package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Regular tests for Epic Feature 1.
 * There should be only one test for US 1.1
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic1RegularTests {
    private Facade facade; // Creates Facade object

    @BeforeEach
    public void setup() {
        facade = new Facade(true);

        facade.createUserAccount("john.doe@gmail.com", "John Doe", "UnknownPhantom897", "UnknownPhantom897", "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-1032");
        facade.createUserAccount("mary.jane@yahoo.com", "Mary Jane", "NonExistentPerson888", "NonExistentPerson888", "+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-5698");
    }

    @Test
    public void userLoginTest() {
        String expectedValue = "Successfully logged in.";
        String actualValue = facade.userLogin("john.doe@gmail.com", "UnknownPhantom897");
        assertEquals(expectedValue, actualValue);

        expectedValue = "Successfully logged in.";
        actualValue = facade.userLogin("mary.jane@yahoo.com", "NonExistentPerson888");
        assertEquals(expectedValue, actualValue);
    }
}

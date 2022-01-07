package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Alternative tests for Epic Feature 4.
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic4AlternativeTests {
    private Facade facade;

    @BeforeEach
    public void setup() {
        facade = new Facade(true);
        facade.createUserAccount("john@gmail.com", "John Smith", "password", "password", "+46707012345", "Street 1", "200001010001");
    }

    @Test
    public void failDeleteUserAccountTest() {
        String expectedValue = "A user with that email does not exist.";
        String actualValue = facade.deleteUserAccount("person@gmail.com");
        assertEquals(expectedValue, actualValue);
    }
}

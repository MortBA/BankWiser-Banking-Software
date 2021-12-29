package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Alternative tests for Epic Feature 4
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic4AlternativeTests {
    static Facade facade;

    @BeforeAll
    public static void setup() {
        facade = new Facade();

        facade.createUserAccount("bank.clerk@bankwiser.com", "Bank Clerk", "rr2e2w28ew53d!", "rr2e2w28ew53d", "+46 72-373 11 29", "Streetgatan 1 2265, 45612 Somethingborg, Sweden", "19991024-7884");
    }

    @Test
    public static void pendingRequestsTest() {
        String expectedValue = "No pending requests.";
        String actualValue = facade.pendingRequests().toString();

        assertEquals(actualValue, expectedValue);
    }

    @Test
    public static void resetClerkPasswordTest() {
        String expectedValue = "Invalid account number";
        String actualValue = facade.resetClerkPassword("20001201", "bank.clerk@bankwiser.com", "18e5s5e4d5s!!!", "18e5s5e4d5s!!!");
        assertEquals(expectedValue, actualValue);

        expectedValue = "Invalid email address.";
        actualValue = facade.resetClerkPassword("19991024-7884", "bankclerk@bankwiser.com", "18e5s5e4d5s!!!", "18e5s5e4d5s!!!");
        assertEquals(expectedValue, actualValue);

        expectedValue = "The passwords do not match.";
        actualValue = facade.resetClerkPassword("19991024-7884", "bank.clerk@bankwiser.com", "18e5s5e4d5s!?!", "18e5s5e4d5s!!!");
        assertEquals(expectedValue, actualValue);
    }
}

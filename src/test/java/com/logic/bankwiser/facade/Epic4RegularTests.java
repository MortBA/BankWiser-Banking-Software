package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Regular tests for Epic Feature 4.
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic4RegularTests {
    static Facade facade;

    @BeforeAll
    public static void setup() {
        facade.createUserAccount("bank.clerk@bankwiser.com", "Bank Clerk", "rr2e2w28ew53d!", "rr2e2w28ew53d", "+46 72-373 11 29", "Streetgatan 1 2265, 45612 Somethingborg, Sweden", "19991024-7884");
        facade.loanApplication("john.doe@fictmail.com", 200000);
        facade.loanApplication("john.doe@fictmail.com", 3000000);
        facade.loanApplication("mary.jane@fictmail.com", 5000000);
    }

    @Test
    public void resetClerkPasswordTest() {
        String expectedValue = "Password reset for ‘bank.clerk@bankwiser.com’ was successful.";
        String actualValue = facade.resetClerkPassword("19991024-7884", "bank.clerk@bankwiser.com", "NewPassword123", "NewPassword123");
        assertEquals(expectedValue, actualValue);
    }
}

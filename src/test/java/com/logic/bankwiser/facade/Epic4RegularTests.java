package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Regular tests for Epic Feature 4.
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic4RegularTests {
    private Facade facade;

    @BeforeEach
    public void setup() {
        facade = new Facade(true);
        facade.createUserAccount("john@gmail.com", "John Smith", "password", "password", "+46707012345", "Street 1", "200001010001");
        facade.createUserAccount("peter@gmail.com", "Peter Smith", "password", "password", "+46707023456", "Street 2", "200001010002");
    }

    @Test
    public void deleteUserAccountTest() {
        String expectedValue = "User account deletion request has been sent.";
        String actualValue = facade.deleteUserAccount("john@gmail.com");
        assertEquals(expectedValue, actualValue);

        expectedValue = "User account deletion request has been sent.";
        actualValue = facade.deleteUserAccount("peter@gmail.com");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void requestMapAccessed() {
        HashMap<Integer, UserAccount> expectedValue = new HashMap<>();
        expectedValue.put(0, facade.storage.getUserFromMap("john@gmail.com"));
        facade.deleteUserAccount("john@gmail.com");
        HashMap<Integer, UserAccount> actualValue = facade.storage.getRequestMap();
        assertEquals(expectedValue, actualValue);
    }
}

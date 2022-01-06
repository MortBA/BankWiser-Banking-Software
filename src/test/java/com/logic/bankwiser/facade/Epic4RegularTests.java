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

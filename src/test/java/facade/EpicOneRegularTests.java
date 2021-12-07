/*
Regular tests for Epic Feature 1.
There should be only one test for US 1.1
 */

package facade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class EpicOneRegularTests {
    static Facade facade;
    static String[][] users = {
            {"john.doe@email.com", "mary.jane@email.com"},
            {"John Doe", "Mary Jane"},
            {"Password123", "NotHarleyQueen123"},
            {"+46 72-373 33 56", "+46 70-323 98 89"},
            {"Gatangatan 9 1126, 422 42 Hisings Backa, Sweden", "Platsentorget 9 1123, 417 57 Lindholmen, Sweden"},
            {"841222-7896", "001105-7896"}
    };

    @BeforeAll
    public static void setup() {
        facade.createUserAccount(users[0][0], users[0][1], users[0][2], users[0][2], users[0][3], users[0][4], users[0][5]);
        facade.createUserAccount(users[1][0], users[1][1], users[1][2], users[1][2], users[1][3], users[1][4], users[1][5]);
    }

    @Test
    public void userLoginTest() {
        String expectedValue = "User john.doe@email.com was logged in.";
        String actualValue = facade.userLogin(users[0][0], users[0][2]);
        assertEquals(expectedValue, actualValue);

        expectedValue = "User mary.jane@email.com was logged in.";
        actualValue = facade.userLogin(users[1][0], users[1][2]);
        assertEquals(expectedValue, actualValue);
    }
}

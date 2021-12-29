// Alternative tests for Epic Feature 3

package com.logic.bankwiser.facade;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Epic3AlternativeTests {
    static Facade facade;
    static String name = "test-account-name";

    @BeforeAll
    public static void setup() {
        facade = new Facade();

        facade.createBankAccount("john.doe@fictmail.com", name);
    }

    @Test
    public void createBankAccountTest() {
        String expectedValue = "Cannot create a new account named test-account-name: the account of that name already exists.";
        String actualValue = facade.createBankAccount("john.doe@fictmail.com", name);
        assertEquals(expectedValue, actualValue);

        expectedValue = "Cannot create a new account named test-account name: the name shouldn't include spaces.";
        actualValue = facade.createBankAccount("john.doe@fictmail.com", "test-account name");
        assertEquals(expectedValue, actualValue);

        expectedValue = "Cannot create a new account: The account name name is only 4 long.";
        actualValue = facade.createBankAccount("john.doe@fictmail.com", "name");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void deleteBankAccountTest() {
        String expectedValue = "Cannot delete account fdsfdsaqwertytrewq: Account not found.";
        String actualValue = facade.deleteBankAccount("fdsfdsaqwertytrewq");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void viewUserAccountsTest() {
        facade.createUserAccount("not.mei@fictmail.com", "Notredamus Meius", "rr2e2w28ew53d!", "rr2e2w28ew53d", "+46 72-373 65 29", "Streetgatan 1 2265, 45612 Somethingborg, Sweden", "19991024-7894");

        String expectedValue = "Cannot retrieve account.";
        String actualValue = facade.deleteBankAccount("not.mei@fictmail.com");
        assertEquals(expectedValue, actualValue);
    }
}

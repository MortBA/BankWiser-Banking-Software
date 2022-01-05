package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Regular Tests for Epic Feature 3
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic3RegularTests {
    private Facade facade;
    UserAccount johnAccount;
    UserAccount maryAccount;
    String[] accountNames = {"account-name1", "account-name2", "account-name3", "account-name4", "account-name5"};

    @BeforeEach
    public void setup() {
        facade = new Facade();

        facade.createUserAccount("john.doe@fictmail.com", "John Doe", "NotPassword123", "NotPassword123", "+46 72-373 46 79", "Streetgatan 1 1234, 45612 Somethingborg, Sweden", "19421231-7894");
        facade.createUserAccount("mary.jane@fictmail.com", "Mary Jane", "NonexistentPwd123", "NonexistentPwd123", "+46 72-373 40 96", "Streetgatan 1 1234, 45612 Somethingborg, Sweden", "19430228-0012");

        johnAccount = facade.storage.getUserFromMap("john.doe@fictmail.com");
        maryAccount = facade.storage.getUserFromMap("mary.jane@fictmail.com");
    }

    @Test
    public void createBankAccountTest() {
        String expectedValue = "New banking account account-name1 had been created";
        String actualValue = facade.createBankAccount(johnAccount.getUserID(), accountNames[0]);
        assertEquals(expectedValue, actualValue);

        expectedValue = "New banking account account-name2 had been created";
        actualValue = facade.createBankAccount(maryAccount.getUserID(), accountNames[1]);
        assertEquals(expectedValue, actualValue);

        expectedValue = "New banking account account-name3 had been created";
        actualValue = facade.createBankAccount(johnAccount.getUserID(), accountNames[2]);
        assertEquals(expectedValue, actualValue);

        expectedValue = "New banking account account-name4 had been created";
        actualValue = facade.createBankAccount(maryAccount.getUserID(), accountNames[3]);
        assertEquals(expectedValue, actualValue);

        expectedValue = "New banking account account-name5 had been created";
        actualValue = facade.createBankAccount(maryAccount.getUserID(), accountNames[4]);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void deleteBankAccountTest() {
        String expectedValue = "Deleted account account-name1.";
        String actualValue = facade.deleteBankAccount(accountNames[0]);
        assertEquals(expectedValue, actualValue);

        expectedValue = "Deleted account account-name2";
        actualValue = facade.deleteBankAccount(accountNames[1]);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void viewUserAccountsTest() {
        facade.depositMoney("account-name3", 10000);
        facade.depositMoney("account-name4", 2000);
        facade.depositMoney("account-name5", 5000);

        String expectedValue = "[account-name3 = 10000]";
        String actualValue = johnAccount.toString();
        assertEquals(expectedValue, actualValue);

        expectedValue = "[account-name4 = 2000, account-name5 = 5000]";
        actualValue = maryAccount.toString();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void viewAccountDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        facade.createBankAccount(johnAccount.getUserID(), "account-name6");
        facade.transferMoney("bank-account3", "bank-account6", "", 1000);

        String expectedValue = "[" + dateFormat.format(date) + ", 1000, 1, [" + dateFormat.format(date) + ", bank-account3, 1000, 1000]";
        String actualValue = "";

        assertEquals(expectedValue, actualValue);
    }
}

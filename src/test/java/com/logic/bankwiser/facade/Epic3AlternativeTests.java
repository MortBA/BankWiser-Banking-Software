package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Alternative tests for Epic Feature 3
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic3AlternativeTests {
    private Facade facade;
    UserAccount johnAccount;
    UserAccount peterAccount;
    BankAccount johnBankAccount;

    @BeforeEach
    public void setup() {
        facade = new Facade(true);

        facade.createUserAccount("john@gmail.com", "John Smith", "password", "password", "+46707012345", "Street 1", "200001010001");
        facade.createUserAccount("peter@gmail.com", "Peter Smith", "password", "password", "+46707023456", "Street 2", "200001010002");

        johnAccount = facade.storage.getUserFromMap("john@gmail.com");
        peterAccount = facade.storage.getUserFromMap("peter@gmail.com");

        facade.createBankAccount(johnAccount.getUserID(), "Savings");
        facade.createBankAccount(peterAccount.getUserID(), "Checking");

        johnBankAccount = facade.storage.getBankAccount(johnAccount.getBankAccountList().get(0));
    }

    @Test
    public void createBankAccountTest() {
        facade.activeUser = johnAccount;
        String expectedValue = "Cannot create a new account named Savings: an account of that name already exists.";
        String actualValue = facade.createBankAccount(johnAccount.getUserID(), "Savings");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void deleteBankAccountTest() {
        facade.activeUser = johnAccount;
        String expectedValue = "That bank account does not exist.";
        String actualValue = facade.deleteBankAccount("fdsfdsaqwertytrewq");
        assertEquals(expectedValue, actualValue);
    }
}

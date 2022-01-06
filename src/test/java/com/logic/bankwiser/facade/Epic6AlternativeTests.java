package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Alternative tests for Epic Feature 6.
 *
 * @author Mathias Hallander
 */
public class Epic6AlternativeTests {
    private Facade facade; // Creates Facade object
    UserAccount johnAccount;
    UserAccount peterAccount;
    BankAccount johnBankAccount;
    BankAccount peterBankAccount;

    @BeforeEach
    public void setup() {
        facade = new Facade(true);

        facade.createUserAccount("john@gmail.com", "John Smith", "password", "password", "+46707012345", "Street 1", "200001010001");
        facade.createUserAccount("peter@gmail.com", "Peter Smith", "password", "password", "+46707023456", "Street 2", "200001010002");

        johnAccount = facade.storage.getUserFromMap("john@gmail.com");
        peterAccount = facade.storage.getUserFromMap("peter@gmail.com");

        facade.createBankAccount(johnAccount.getUserID(), "Personal");
        facade.createBankAccount(peterAccount.getUserID(), "Checking");

        johnBankAccount = facade.storage.getBankAccount(johnAccount.getBankAccountList().get(0));
        peterBankAccount = facade.storage.getBankAccount(peterAccount.getBankAccountList().get(0));
    }

    @Test
    public void shouldPrintErrorForInvalidLoan() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String expectedValue = "Your loan request was denied. Your income cannot support repaying a loan.";
        String actualValue = facade.homeLoanApplication(20000, 20000, 100000, 12, 40, "Street 1", "Villa", 1);
        assertEquals(expectedValue, actualValue);

        expectedValue = "Your loan request was denied. You must borrow at least 99996.0 for this duration.";
        actualValue = facade.homeLoanApplication(20000, 5000, 10000, 12, 40, "Street 1", "Villa", 1);
        assertEquals(expectedValue, actualValue);
    }
}
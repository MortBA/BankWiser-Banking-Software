package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Regular tests for Epic Feature 7.
 *
 * @author Mathias Hallander
 */
public class Epic7RegularTests {
    private Facade facade; // Creates Facade object
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

        facade.createBankAccount(johnAccount.getUserID(), "testing1");
        facade.createBankAccount(peterAccount.getUserID(), "testing2");

        johnBankAccount = facade.storage.getBankAccount(johnAccount.getBankAccountList().get(0));
    }

    /**
     * Expected value 1: "Your application for a debit card has been accepted. We’ll let you know when it will be shipped soon."
     * Expected value 2: "Your application for a credit card has been submitted. We’ll let you know whether it has been accepted or rejected after evaluation."
     */
    @Test
    public void shouldCreateCard() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String expectedValue = "Your application for a debit card has been accepted. We’ll let you know when it will be shipped soon.";
        String actualValue = facade.createDebitCard(1596, 1596);
        assertEquals(expectedValue, actualValue);

        expectedValue = "Your application for a credit card has been submitted. We’ll let you know whether it has been accepted or rejected after evaluation.";
        actualValue = facade.createCreditCard(1596, 1596, 10000, 5000);
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Expected value 1: "Your card has been successfully blocked."
     * Expected value 2: "Your card has been successfully unblocked."
     * Expected value 4: "You successfully turned on online transactions."
     * Expected value 5: "You successfully turned off online transactions."
     * Expected value 6: "You successfully changed your spending limit from {originalLimit} to {newLimit}."
     */
    @Test
    public void shouldModifyCard() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String cardNumber = "4405111122223333";
        facade.createDebitCard(cardNumber, 1596, 1596);

        String expectedValue = "Your card has been successfully blocked.";
        String actualValue = facade.toggleFreezeCard(cardNumber); // Should be changed later
        assertEquals(expectedValue, actualValue);

        expectedValue = "Your card has been successfully unblocked.";
        actualValue = facade.toggleFreezeCard(cardNumber);
        assertEquals(expectedValue, actualValue);

        expectedValue = "You successfully turned off online transactions.";
        actualValue = facade.toggleOnlineTransactions(cardNumber);
        assertEquals(expectedValue, actualValue);

        expectedValue = "You successfully turned on online transactions.";
        actualValue = facade.toggleOnlineTransactions(cardNumber);
        assertEquals(expectedValue, actualValue);

        expectedValue = "You successfully changed your spending limit to 30000.0.";
        actualValue = facade.changeSpendingLimit(cardNumber, 30000);
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Expected value: "Your card has been successfully terminated."
     */
    @Test
    public void shouldDeleteCard() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String cardNumber = "4405111122223333";
        facade.createDebitCard(cardNumber, 1596, 1596);

        String expectedValue = "Your card has been successfully terminated.";
        String actualValue = facade.deleteCard(cardNumber, "too young", 1596);
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Expected value: "Successfully changed PIN code."
     */
    @Test
    public void shouldModifyPIN() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String cardNumber = "4405111122223333";
        facade.createDebitCard(cardNumber, 1596, 1596);
        String expectedValue = "Successfully changed PIN code.";
        String actualValue = facade.changePin(cardNumber, 1596, 5246, 5246);
        assertEquals(expectedValue, actualValue);
    }

}
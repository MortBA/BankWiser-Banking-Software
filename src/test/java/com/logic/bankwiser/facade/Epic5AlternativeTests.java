package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Alternative tests for Epic Feature 5.
 *
 * @author Mathias Hallander
 */
public class Epic5AlternativeTests {
    private Facade facade; // Creates Facade object
    UserAccount john;
    UserAccount peter;
    BankAccount johnBankAccount;
    BankAccount peterBankAccount;

    @BeforeEach
    public void setup() {
        facade = new Facade();

        facade.createUserAccount("john@gmail.com", "John Smith", "password", "password", "+46707012345", "Street 1", "200001010001");
        facade.createUserAccount("peter@gmail.com", "Peter Smith", "password", "password", "+46707023456", "Street 2", "200001010002");

        john = facade.storage.getUserFromMap(facade.storage.getUserUUID("john@gmail.com"));
        peter = facade.storage.getUserFromMap(facade.storage.getUserUUID("peter@gmail.com"));

        facade.createBankAccount(john.getUserID(), "testing1");
        facade.createBankAccount(peter.getUserID(), "testing2");

        johnBankAccount = facade.storage.getBankAccount(john.getBankAccountList().get(0));
        peterBankAccount = facade.storage.getBankAccount(peter.getBankAccountList().get(0));
    }

    /**
     * Expected value 1: “Cannot transfer: {sender} was not found.“
     * Expected value 2: “Cannot transfer: {receiver} was not found.”
     * Expected value 3: “Cannot transfer: Only enter positive numbers.”
     * Expected value 4: “The sender field shouldn’t be left blank.”
     * Expected value 5: "The receiver field shouldn't be left blank."
     * Expected value 6: "The amount field shouldn't be left blank."
     */
    @Test
    public void shouldPrintErrorTransactionFailure() {

        assertEquals(facade.transferMoney("1584", peterBankAccount.getBankAccountID(), "", 100), "Cannot transfer: {sender} was not found.");
        assertEquals(facade.transferMoney(johnBankAccount.getBankAccountID(), "1543", "", 100), "Cannot transfer: {receiver} was not found.");
        assertEquals(facade.transferMoney(johnBankAccount.getBankAccountID(), peterBankAccount.getBankAccountID(), "", -100), "Cannot transfer: Only enter positive numbers.");
        assertEquals(facade.transferMoney("", peterBankAccount.getBankAccountID(), "", 100), "The sender field shouldn’t be left blank.");
        assertEquals(facade.transferMoney(johnBankAccount.getBankAccountID(), "", "", 100), "The receiver field shouldn’t be left blank.");
        assertEquals(facade.transferMoney(johnBankAccount.getBankAccountID(), peterBankAccount.getBankAccountID(), "", 0), "The amount field shouldn’t be left blank.");

    }

    /**
     * Expected value: “No previous transaction history.”
     */
    @Test
    public void shouldPrintErrorWhenNoTransactionHistory() {
        assertEquals(facade.viewTransactionHistory(johnBankAccount.getBankAccountID()), "No previous transaction history.");
    }
}

package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.utils.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void createBankAccountTest() {
        String expectedValue = "New banking account Savings has been created.";
        String actualValue = facade.createBankAccount(johnAccount.getUserID(), "Savings");
        assertEquals(expectedValue, actualValue);

        expectedValue = "New banking account Money has been created.";
        actualValue = facade.createBankAccount(peterAccount.getUserID(), "Money");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void renameBankAccountTest() {
        facade.activeUser = johnAccount;
        String expectedValue = "Your bank account has been renamed to Finances.";
        String actualValue = facade.renameBankAccount(johnBankAccount.getBankAccountID(), "Finances");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void deleteBankAccountTest() {
        facade.activeUser = johnAccount;
        String expectedValue = "Deleted bank account Personal.";
        String actualValue = facade.deleteBankAccount(johnBankAccount.getBankAccountID());
        assertEquals(expectedValue, actualValue);

        facade.activeUser = peterAccount;
        expectedValue = "Deleted bank account Checking.";
        actualValue = facade.deleteBankAccount(peterBankAccount.getBankAccountID());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void viewUserAccountsTest() {
        String expectedValue = "userID: " + johnAccount.getUserID() + Input.EOL +
                                "fullName: John Smith" + Input.EOL +
                                "phoneNumber: +46707012345" + Input.EOL +
                                "address: Street 1" + Input.EOL +
                                "SOCIAL_SECURITY_NUMBER: 200001010001" + Input.EOL +
                                "emailID: john@gmail.com" + Input.EOL +
                                "password: password" + Input.EOL +
                                "bankAccountList: [" + johnBankAccount.getBankAccountID() + "]";
        String actualValue = johnAccount.toString();
        assertEquals(expectedValue, actualValue);

        expectedValue = "userID: " + peterAccount.getUserID() + Input.EOL +
                        "fullName: Peter Smith" + Input.EOL +
                        "phoneNumber: +46707023456" + Input.EOL +
                        "address: Street 2" + Input.EOL +
                        "SOCIAL_SECURITY_NUMBER: 200001010002" + Input.EOL +
                        "emailID: peter@gmail.com" + Input.EOL +
                        "password: password" + Input.EOL +
                        "bankAccountList: [" + peterBankAccount.getBankAccountID() + "]";
        actualValue = peterAccount.toString();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void viewBankAccountDetails() {
        String expectedValue = "bankAccountID: " + johnBankAccount.getBankAccountID() + Input.EOL +
                                "Name: Personal" + Input.EOL +
                                "Balance: 0.00" + Input.EOL +
                                "Transactions: []" + Input.EOL +
                                "Loans: []" + Input.EOL +
                                "Cards: []";
        String actualValue = johnBankAccount.toString();
        assertEquals(expectedValue, actualValue);

        expectedValue = "bankAccountID: " + peterBankAccount.getBankAccountID() + Input.EOL +
                "Name: Checking" + Input.EOL +
                "Balance: 0.00" + Input.EOL +
                "Transactions: []" + Input.EOL +
                "Loans: []" + Input.EOL +
                "Cards: []";
        actualValue = peterBankAccount.toString();
        assertEquals(expectedValue, actualValue);
    }
}

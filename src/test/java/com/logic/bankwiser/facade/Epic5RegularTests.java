package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.utils.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Regular tests for Epic Feature 5.
 *
 * @author Mathias Hallander
 */
public class Epic5RegularTests {
    private Facade facade; // Creates Facade object
    UserAccount john;
    UserAccount peter;
    BankAccount johnBankAccount;
    BankAccount peterBankAccount;

    @BeforeEach
    public void setup() {
        facade = new Facade(true);

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
     * Expected Value: "Successfully sent {moneyAmount} SEK from {sender} to {receiver}. Note: {note}."
     */
    @Test
    public void shouldTransferMoney() {
        String expectedValue = "Successfully sent 100.0 SEK from " + johnBankAccount.getBankAccountID() + " to " + peterBankAccount.getBankAccountID() + ". Note: transfer.";
        String actualValue = facade.transferMoney(johnBankAccount.getBankAccountID(), peterBankAccount.getBankAccountID(), "transfer", 100);
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Expected value: date + ", " + bankAccountID + ", -100, -100.00" + Input.EOL +
     * date + ", " + bankAccountID + ", -100, -200.00" + Input.EOL +
     * date + ", " + bankAccountID + ", -100, -300.00" + Input.EOL;
     */
    @Test
    public void shouldRetrieveTransactionHistory() {
        String bankAccountID = peterBankAccount.getBankAccountID();
        String date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();

        facade.transferMoney(johnBankAccount.getBankAccountID(), peterBankAccount.getBankAccountID(), "transfer", 100);
        facade.transferMoney(johnBankAccount.getBankAccountID(), peterBankAccount.getBankAccountID(), "transfer", 100);
        facade.transferMoney(johnBankAccount.getBankAccountID(), peterBankAccount.getBankAccountID(), "transfer", 100);

        String transactions = date + ", " + bankAccountID + ", -100.0, -100.00" + Input.EOL +
                date + ", " + bankAccountID + ", -100.0, -200.00" + Input.EOL +
                date + ", " + bankAccountID + ", -100.0, -300.00" + Input.EOL;

        assertEquals(transactions, facade.viewTransactionHistory(johnBankAccount.getBankAccountID()));

    }
}

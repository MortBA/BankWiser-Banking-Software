package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Alternative tests for Epic Feature 7.
 *
 * @author Mathias Hallander
 */
public class Epic7AlternativeTests {
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
     * Expected value 1: "Invalid PIN: A number cannot be repeated more than once"
     * Expected value 2: "Invalid PIN: Your PIN cannot consist of numbers in consecutive order"
     * Expected value 4: "Invalid PIN: PIN must be four digits"
     * Expected value 5: "Invalid PIN: PIN must be four digits"
     */
    @Test
    public void shouldPrintErrorMessageWhenCardInvalid() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String cardNumber = "4405111122223333.";

        String actualValue = facade.createDebitCard(cardNumber, 1116);
        String expectedValue = "Invalid PIN: A number cannot be repeated more than once.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.createDebitCard(cardNumber, 1234);
        expectedValue = "Invalid PIN: Your PIN cannot consist of numbers in consecutive order.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.createDebitCard(cardNumber, 153);
        expectedValue = "Invalid PIN: PIN must be four digits.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.createDebitCard(cardNumber, 84675);
        expectedValue = "Invalid PIN: PIN must be four digits.";
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Expected value 1: "Cannot toggle freezing of card: Incorrect card details."
     * Expected value 2: "Cannot toggle online transactions: Incorrect card details."
     */
    @Test
    public void shouldPrintErrorMessageWhenCardModifiedInvalid() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String cardNumber = "4405111122223333.";
        facade.createDebitCard(cardNumber, 1596);

        String actualValue = facade.toggleFreezeCard("1234");
        String expectedValue = "Cannot toggle freezing of card: Incorrect card details.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.toggleOnlineTransactions("1234");
        expectedValue = "Cannot toggle online transactions: Incorrect card details.";
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Expected value 1: "Card number you entered was not found in the list of your cards."
     * Expected value 2: "Incorrect PIN code."
     * Expected value 3: "You have to provide at least one reason why you wish to terminate your card."
     */
    @Test
    public void shouldPrintErrorMessageWhenCardDeleteInvalid() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String cardNumber = "4405111122223333.";
        facade.createDebitCard(cardNumber, 1596);

        String actualValue = facade.deleteCard("186516515", "Too expensive",1596);
        String expectedValue = "Card number you entered was not found in the list of your cards.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.deleteCard(cardNumber, "Too expensive",8914);
        expectedValue = "Incorrect PIN code.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.deleteCard(cardNumber, "",1596);
        expectedValue = "You have to provide at least one reason why you wish to terminate your card.";
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Expected value 1:  "There is no card with the number {cardNumber} linked to you."
     * Expected value 2:  "Incorrect PIN code." * For the old PIN code
     * Expected value 5:  "Cannot change PIN code: New PIN code doesn’t match the one in the confirmation field."
     * Expected value 6:  "Invalid PIN: A number cannot be followed by the same one"
     * Expected value 7:  "Invalid PIN: Your PIN cannot consist of numbers in consecutive order"
     * Expected value 10: "Invalid PIN: PIN must be four digits"
     */
    @Test
    public void shouldPrintErrorMessageWhenChangingCardPINInvalid() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String cardNumber = "4405111122223333.";
        facade.createDebitCard(cardNumber, 1596);

        String actualValue = facade.changePin("1234", 1596, 1869, 1869);
        String expectedValue = "Card number you entered was not found in the list of your cards.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.changePin(cardNumber, 8794, 1869, 1869);
        expectedValue = "Incorrect PIN code.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.changePin(cardNumber, 1596, 1863, 1894);
        expectedValue = "Cannot change PIN code: New PIN code doesn’t match the one in the confirmation field.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.changePin(cardNumber, 1596, 186, 186);
        expectedValue = "Invalid PIN: PIN must be four digits.";
        assertEquals(expectedValue, actualValue);

        actualValue = facade.changePin(cardNumber, 1596, 18609, 18609);
        expectedValue = "Invalid PIN: PIN must be four digits.";
        assertEquals(expectedValue, actualValue);

    }
}

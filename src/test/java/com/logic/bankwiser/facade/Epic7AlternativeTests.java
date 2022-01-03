package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;


/**
 * Alternative tests for Epic Feature 7.
 *
 * @author Mathias Hallander
 */
public class Epic7AlternativeTests {
    private Facade facade; // Creates Facade object

    @BeforeEach
    public void setup() {
        facade = new Facade();

        facade.createUserAccount("john@gmail.com", "John Smith", "password", "password", "+46707012345", "Street 1", "200001010001");
        facade.createUserAccount("peter@gmail.com", "Peter Smith", "password", "password", "+46707023456", "Street 2", "200001010002");

        UUID johnID = facade.storage.getUserUUID("john@gmail.com");
        UUID peterID = facade.storage.getUserUUID("peter@gmail.com");

        facade.createBankAccount(johnID, "testing1");
        facade.createBankAccount(peterID, "testing2");
    }

    /**
     * Expected value 1: “Invalid PIN: A number cannot be followed by the same one”
     * Expected value 2: “Invalid PIN: Your PIN cannot consist of numbers in consecutive order”
     * Expected value 3: “Invalid PIN: Only enter numbers.”
     * Expected value 4: “Invalid PIN: The PIN is too short (PIN should be 4 characters long.”
     * Expected value 5: “Invalid PIN: The PIN is too long (PIN should be only 4 characters long).”
     */
    @Test
    public void shouldPrintErrorMessageWhenCardInvalid() {

    }

    /**
     * Expected value 1: “Cannot unfreeze card: Incorrect card details.”
     * Expected value 3: “Invalid input: Please only enter numbers.”
     * Expected value 4: “Invalid input: The new spending limit should not be negative.”
     */
    @Test
    public void shouldPrintErrorMessageWhenCardModifiedInvalid() {

    }

    /**
     * Expected value 1: “Card number you entered was not found in the list of your cards.”
     * Expected value 2: “Incorrect PIN code.”
     * Expected value 3: “You have to provide at least one reason why you wish to terminate your card.”
     * Expected value 4: “{fieldName} field shouldn’t be left blank.”
     */
    @Test
    public void shouldPrintErrorMessageWhenCardDeleteInvalid() {

    }

    /**
     * Expected value 1:  “There is no card with the number {cardNumber} linked to you.”
     * Expected value 2:  “Incorrect PIN code.” * For the old PIN code
     * Expected value 3:  “Invalid PIN code: Only enter numbers.”
     * Expected value 4:  “Invalid PIN code: Only enter positive integers.“
     * Expected value 5:  “Cannot change PIN code: New PIN code doesn’t match the one in the confirmation field.”
     * Expected value 6:  “Invalid PIN: A number cannot be followed by the same one”
     * Expected value 7:  “Invalid PIN: Your PIN cannot consist of numbers in consecutive order”
     * Expected value 8:  “Invalid PIN: Only enter numbers.”
     * Expected value 9:  “Invalid PIN: The PIN is too short (PIN should be 4 characters long.”
     * Expected value 10: “Invalid PIN: The PIN is too long (PIN should be only 4 characters long).”
     */
    @Test
    public void shouldPrintErrorMessageWhenCardPINInvalid() {

    }
}

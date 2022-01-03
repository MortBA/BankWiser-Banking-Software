package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;


/**
 * Regular tests for Epic Feature 7.
 *
 * @author Mathias Hallander
 */
public class Epic7RegularTests {
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
     * Expected value 1: “Your application for a debit card has been accepted. We’ll let you know when it will be shipped soon.”
     * Expected value 2: “Your application for a credit card has been submitted. We’ll let you know whether it has been accepted or rejected after evaluation.”
     */
    @Test
    public void shouldCreateCard() {

    }

    /**
     * Expected value 1: “Your card has been successfully blocked.”
     * Expected value 2: “Your card has been successfully unblocked.”
     * Expected value 4: “You successfully turned on online transactions.”
     * Expected value 5: “You successfully turned off online transactions.”
     * Expected value 6: “You successfully changed your spending limit from {originalLimit} to {newLimit}.”
     */
    @Test
    public void shouldModifyCard() {

    }

    /**
     * Expected value: “Your card has been successfully terminated.”
     */
    @Test
    public void shouldDeleteCard() {

    }

    /**
     * Expected value: “Successfully changed PIN code”
     */
    @Test
    public void shouldModifyPIN() {

    }

}
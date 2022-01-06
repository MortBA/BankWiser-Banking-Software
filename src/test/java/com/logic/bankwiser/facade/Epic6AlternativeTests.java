package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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

    /**
     * Expected value: "User’s {username} loan for {amount} SEK was denied."
     */
    @Test
    public void shouldPrintErrorForInvalidLoan() {

    }

    /**
     * Expected value 1: "Invalid input: Please don’t enter negative numbers."
     * In case the user tries to enter negative income or negative expenses
     * Expected value 2: "Invalid input: A non-numeric character found."
     * In case the customer makes a typo and accidentally includes a letter or something
     * Expected value 3: "Please don’t leave {fieldName} empty."
     */
    @Test
    public void shouldPrintErrorForInvalidPersonalLoan() {

    }

    /**
     * Expected value 1: "Invalid input: Please amount must not be negative."
     * Expected value 2: "Invalid input: Please only enter numbers."
     * Expected value 3: "Invalid input: Property must have at least 1 floor."
     * Expected value 4: "{fieldName} cannot be left blank."
     */
    @Test
    public void shouldPrintErrorForInvalidHomeLoan() {

    }

    /**
     * Expected value 1: "Invalid input: Please amount must not be negative."
     * Expected value 2: "Invalid input: Please only enter numbers."
     * Expected value 3: "{fieldName} cannot be left blank."
     */
    @Test
    public void shouldPrintErrorForInvalidVehicleLoan() {

    }

    /**
     * Expected value 1: "Invalid input: A non-numeric character was found."
     * Expected value 2: "Invalid input: A negative value was found."
     * Expected value 3: "{fieldName} field cannot be left blank."
     */
    @Test
    public void shouldPrintErrorForInvalidLoanSize() {

    }

    /**
     * Expected value 1: "Loan requested by {username} was rejected due to {reason}
     * Expected value 2: "Cannot accept the loan because the last document was submitted {days} past deadline
     */
    @Test
    public void shouldPrintErrorFor() {

    }
}
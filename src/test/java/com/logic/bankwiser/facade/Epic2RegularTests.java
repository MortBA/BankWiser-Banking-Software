package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Regular tests for Epic Feature 2.
 * The methods will be provided correct input to see whether they would process it
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic2RegularTests {
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

        facade.createBankAccount(johnAccount.getUserID(), "testing1");
        facade.createBankAccount(peterAccount.getUserID(), "testing2");

        johnBankAccount = facade.storage.getBankAccount(johnAccount.getBankAccountList().get(0));
    }

    @Test
    public void createUserAccountTest() {
        String expectedValue = "New account for john.doe@gmail.com was successfully created.";
        String actualValue = facade.createUserAccount("john.doe@gmail.com", "John Doe", "Password123", "Password123","+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-1032");
        assertEquals(expectedValue, actualValue);

        expectedValue = "New account for mary.jane@yahoo.com was successfully created.";
        actualValue = facade.createUserAccount("mary.jane@yahoo.com", "Mary Jane", "Password123", "Password123","+46 72-373 89 56", "Gatangatan 8 1152, 422 42 Hisings Backa, Sweden", "19890213-5698");
        assertEquals(expectedValue, actualValue);

        expectedValue = "New account for sem.mogilevich@mafiamail.com was successfully created.";
        actualValue = facade.createUserAccount("sem.mogilevich@mafiamail.com", "Semion Mogilevich", "AcceptablePassword894", "AcceptablePassword894", "+46 70-785 79 45", "Vikingtorget 2 1101, 417 43 Göteborg, Sweden", "19460630-9765");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void resetUserPasswordTest() {
        String expectedValue = "An email has been sent to john@gmail.com with a link that will allow you to reset your password.";
        String actualValue = facade.resetUserPassword("john@gmail.com");
        assertEquals(expectedValue, actualValue);
    }
}

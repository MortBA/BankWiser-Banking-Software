package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.utils.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Regular tests for Epic Feature 6.
 *
 * @author Mathias Hallander
 */
public class Epic6RegularTests {
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
     * Expected value:
     * Your loan request has been accepted.
     * Applicant: {fullName}
     * Income: {income} SEK/mo
     * Expenses: {expenses} SEK/mo
     * Duration of the loan: {duration} months
     */
    @Test
    public void shouldCreatePersonalLoan() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String expectedValue = "Your loan request has been accepted." + Input.EOL +
                "Applicant: John Smith" + Input.EOL +
                "Loan amount: 50000.0 SEK" + Input.EOL +
                "Income: 20000.0 SEK/mo" + Input.EOL +
                "Expenses: 10000.0 SEK/mo" + Input.EOL +
                "Duration of the loan: 12 months";
        String actualValue = facade.personalLoanApplication(20000, 10000, 50000, 12, "Vacation");
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Expected value:
     * "Home loan application submitted:
     *  Applicant: {fullName}
     *  Income: {income} SEK/mo
     *  Expenses: {expenses} SEK/mo
     *  Liabilities " dependencies: {liabilities} SEK
     *  Home address: {houseAddress}
     *  Type of home: {type}
     *  Property size: {size} m^2
     *  Amount of stories: {stories}
     *  Duration of the loan: {duration} months"
     */
    @Test
    public void shouldCreateHomeLoan() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String expectedValue = "Your loan request has been accepted." + Input.EOL +
                                "Applicant: John Smith" + Input.EOL +
                                "Loan amount: 100000.0" + Input.EOL +
                                "Income: 20000.0 SEK/mo" + Input.EOL +
                                "Expenses: 10000.0 SEK/mo" + Input.EOL +
                                "Home address: Street 1" + Input.EOL +
                                "Type of home: Villa" + Input.EOL +
                                "Property size: 40.0 m^2" + Input.EOL +
                                "Amount of stories: 40.0" + Input.EOL +
                                "Duration of the loan: 12 months";
        String actualValue = facade.homeLoanApplication(20000, 10000, 100000, 12, 40, "Street 1", "Villa", 1);
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Expected value:
     * "Car loan application submitted:
     *  Applicant: {fullName}
     *  Income: {income} SEK/mo
     *  Expenses: {expenses} SEK/mo
     *  Liabilities " dependencies: {liabilities} SEK
     *  Model: {carModel}
     *  Manufacturer: {carManufacturer}
     *  Millage: {millage} km
     *  Year manufactured: {manufacturedYear}
     */
    @Test
    public void shouldCreateVehicleLoan() {
        facade.activeUser = johnAccount;
        facade.activeBankAccount = johnBankAccount;
        String expectedValue = "Your loan request has been accepted." + Input.EOL +
                                "Applicant: John Smith" + Input.EOL +
                                "Loan amount: 50000.0" + Input.EOL +
                                "Income: 20000.0 SEK/mo" + Input.EOL +
                                "Expenses: 10000.0 SEK/mo" + Input.EOL +
                                "Manufacturer & Model: Car" + Input.EOL +
                                "Millage: 10000.0 km" + Input.EOL +
                                "Year manufactured: 2010" + Input.EOL +
                                "Duration of the loan: 12 months";
        String actualValue = facade.vehicleLoanApplication(20000, 10000, 50000, 12, 10000, "Car", "Diesel", 2010);
        assertEquals(expectedValue, actualValue);
    }
}

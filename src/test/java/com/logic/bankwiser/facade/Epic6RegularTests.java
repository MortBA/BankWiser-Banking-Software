package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Regular tests for Epic Feature 6.
 *
 * @author Mathias Hallander
 */
public class Epic6RegularTests {
    private Facade facade; // Creates Facade object

    @BeforeEach
    public void setup() {
        facade = new Facade();

    }

    /**
     * Expected value: "User’s {username} loan for {amount} SEK was approved."
     */
    @Test
    public void shouldPrintSuccessOnApprovedLoan() {

    }

    /**
     * Expected value:
     * "Applicant: {fullName}
     *  Income: {income} SEK/mo
     *  Expenses: {expenses} SEK/mo
     *  Liabilities " dependencies: {liabilities} SEK
     *  Duration of the loan: {duration} months"
     */
    @Test
    public void shouldCreatePersonalLoan() {

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

    }

    /**
     * Expected value:
     * {monthlyIncome} * {loanLength} * 4.5
     */
    @Test
    public void shouldCalculateCorrectLoanSize() {

    }

    /**
     * Expected value: {interestRate}
     */
    @Test
    public void shouldCalculateCorrectInterestRate() {

    }

    /**
     * Expected value 1: "Loan requested by {username} for {amount} was accepted with interest of {interest}
     * Expected value 2: "Loan requested by {username} was rejected due to {reason}
     */
    @Test
    public void shouldApproveLoan() {

    }
}

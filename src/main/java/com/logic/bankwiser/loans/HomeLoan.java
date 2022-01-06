package com.logic.bankwiser.loans;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.utils.Input;

/**
 * A loan intended for the purchase of a home.
 *
 * @author Burak Askan
 * @author Dragos Florinel Isar
 * @author Mathias Hallander
 */
public class HomeLoan extends Loan {

    private final double HOME_LOAN_INTEREST_RATE = 0.02;
    private final String PROPERTY_ADDRESS;
    private final String PROPERTY_TYPE;
    private final double PROPERTY_SIZE;
    private final int PROPERTY_FLOORS;

    public HomeLoan(BankAccount bankAccount, String loanID, double loanAmount, int loanDuration, String propertyAddress, String propertyType,
                    double propertySize, int propertyFloor) {
        super(bankAccount, loanID, loanAmount, loanDuration);
        this.PROPERTY_ADDRESS = propertyAddress;
        this.PROPERTY_TYPE = propertyType;
        this.PROPERTY_SIZE = propertySize;
        this.PROPERTY_FLOORS = propertyFloor;
        setInterestRate(HOME_LOAN_INTEREST_RATE);
    }

    public double getHomeLoanInterestRate() {
        return HOME_LOAN_INTEREST_RATE;
    }

    public String getPropertyAddress() {
        return PROPERTY_ADDRESS;
    }

    public String getPropertyType() {
        return PROPERTY_TYPE;
    }

    public double getPropertySize() {
        return PROPERTY_SIZE;
    }

    public int getPropertyFloors() {
        return PROPERTY_FLOORS;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString() + Input.EOL);
        sb.append("Property address: " + PROPERTY_ADDRESS + Input.EOL +
                "Property type: " + PROPERTY_TYPE + Input.EOL +
                "Property size: " + PROPERTY_SIZE + Input.EOL +
                "Property floor: " + PROPERTY_FLOORS);
        return sb.toString();
    }
}
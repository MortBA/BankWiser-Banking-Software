package com.logic.bankwiser.loans;

import com.logic.bankwiser.bank_accounts.BankAccount;

/**
 * A loan intended for personal use which does not involve a vehicle or home.
 *
 * @author Burak Askan
 * @author Dragos Florinel Isar
 * @author Mathias Hallander
 */
public class VehicleLoan extends Loan {

    private final String TYPE_OF_FUEL;
    private final double MILEAGE;
    private final int MANUFACTURING_YEAR;
    private final double VEHICLE_LOAN_INTEREST_RATE = 0.055;

    public VehicleLoan(BankAccount bankAccount, String loanID, double loanAmount, int loanDuration, String typeOfFuel, double mileage, int manufactureYear) {
        super(bankAccount, loanID, loanAmount, loanDuration);
        setInterestRate(VEHICLE_LOAN_INTEREST_RATE);
        this.TYPE_OF_FUEL = typeOfFuel;
        this.MILEAGE = mileage;
        this.MANUFACTURING_YEAR = manufactureYear;
    }

    public double getVehicleLoanInterestRate() {
        return this.VEHICLE_LOAN_INTEREST_RATE;
    }

    public String getTypeOfFuel() {
        return TYPE_OF_FUEL;
    }

    public double getMileage() {
        return MILEAGE;
    }

    public int getManufacturingYear() {
        return MANUFACTURING_YEAR;
    }

}

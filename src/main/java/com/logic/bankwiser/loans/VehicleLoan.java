package com.logic.bankwiser.loans;

import com.logic.bankwiser.bank_accounts.BankAccount;

public class VehicleLoan extends Loan {

    private VehicleInformation vehicleInformation;
    public VehicleLoan(BankAccount bankAccount, String loanID, double loanAmount, double interestRate, int loanDuration, String status, VehicleInformation vehicleInformation) {
        super(bankAccount, loanID, loanAmount, interestRate, loanDuration, status);
        this.vehicleInformation = vehicleInformation;
    }

    public VehicleInformation getVehicleInfo() {
        return vehicleInformation;
    }

}

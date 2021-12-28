package com.logic.bankwiser.loans;

import java.util.Date;

public class VehicleLoan extends Loan {



    private VehicleInformation vehicleInformation;
    public VehicleLoan(int loanID, double loanAmount, double interestRate, int loanDuration, String status, VehicleInformation vehicleInformation) {
        super(loanID, loanAmount, interestRate, loanDuration, status);
        this.vehicleInformation = vehicleInformation;
    }

    public VehicleInformation getVehicleInfo() {
        return vehicleInformation;
    }


}

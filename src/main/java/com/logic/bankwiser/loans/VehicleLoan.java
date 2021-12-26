package com.logic.bankwiser.loans;

import java.util.Date;

public class VehicleLoan extends Loan {

    private String vehicleInfo;
    public VehicleLoan(int loanID, int loanAmount, double interestRate, Date maturityDate, String status, String vehicleInfo) {
        super(loanID, loanAmount, interestRate, maturityDate, status);
        this.vehicleInfo = vehicleInfo;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

}

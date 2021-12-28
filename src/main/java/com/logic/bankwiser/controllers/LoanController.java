package com.logic.bankwiser.controllers;

import com.logic.bankwiser.loans.*;
import com.logic.bankwiser.storage.Storage;

import java.util.Random;

public class LoanController {

    private final Storage storage;

    public LoanController(Storage storage) {
        this.storage = storage;
    }

    public void createHomeLoan(int loanAmount, double interestRate, int loanDuration, String status, HomeInformation homeInformation) {
        int loanID = generateLoanID();
        storage.addLoan(new HomeLoan(loanID, loanAmount, interestRate, loanDuration, status, homeInformation));
    }

    public void createPersonalLoan(int loanAmount, double interestRate, int loanDuration, String status, String personalReasons) {
        int loanID = generateLoanID();
        storage.addLoan(new PersonalLoan(loanID, loanAmount, interestRate,loanDuration, status, personalReasons));
    }

    public void createVehicleLoan(int loanAmount, double interestRate, int loanDuration, String status, VehicleInformation vehicleInformation) {
        int loanID = generateLoanID();
        storage.addLoan(new VehicleLoan(loanID, loanAmount, interestRate, loanDuration, status, vehicleInformation));
    }


    public int generateLoanID(){
        int min = 100000;
        int max = 999999;
        Random rand = new Random();
        int loanID = rand.nextInt((max-min)+1) + min;
        return loanID;
    }

}


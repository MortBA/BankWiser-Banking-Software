package com.logic.bankwiser.controllers;

import com.logic.bankwiser.storage.Storage;

import com.logic.bankwiser.loans.HomeLoan;
import com.logic.bankwiser.loans.PersonalLoan;
import com.logic.bankwiser.loans.VehicleLoan;
import java.util.Date;
import java.util.Random;

public class LoanController {

    private final Storage storage;

    public LoanController(Storage storage) {
        this.storage = storage;
    }

    public void createHomeLoan(int loanAmount, double interestRate, Date maturityDate, String status, String propertyInfo) {
        int caseID = generateCaseID();
        storage.addLoan(new HomeLoan(caseID, loanAmount, interestRate, maturityDate, status, propertyInfo));
    }

    public void createPersonalLoan(int loanAmount, double interestRate, Date maturityDate, String status, String personalReasons) {
        int caseID = generateCaseID();
        storage.addLoan(new PersonalLoan(caseID, loanAmount, interestRate, maturityDate, status, personalReasons));
    }

    public void createVehicleLoan(int loanAmount, double interestRate, Date maturityDate, String status, String vehicleLoan) {
        int caseID = generateCaseID();
        storage.addLoan(new VehicleLoan(caseID, loanAmount, interestRate, maturityDate, status, vehicleLoan));
    }


    public int generateCaseID(){
        int min = 100000;
        int max = 999999;
        Random rand = new Random();
        int caseID = rand.nextInt((max-min)+1) + min;
        return caseID;
    }

}


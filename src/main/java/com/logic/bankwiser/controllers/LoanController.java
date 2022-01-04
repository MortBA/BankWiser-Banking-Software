package com.logic.bankwiser.controllers;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.loans.*;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.utils.MathUtils;

public class LoanController {

    private final Storage storage;

    public LoanController(Storage storage) {
        this.storage = storage;
    }

    public void createHomeLoan(BankAccount bankAccount, int loanAmount, double interestRate, int loanDuration, String status, HomeInformation homeInformation) {

        bankAccount.addLoan(new HomeLoan(bankAccount, generateLoanID(bankAccount), loanAmount, interestRate, loanDuration, status, homeInformation));
    }

    public void createPersonalLoan(BankAccount bankAccount, int loanAmount, double interestRate, int loanDuration, String status, String personalReasons) {

        bankAccount.addLoan(new PersonalLoan(bankAccount, generateLoanID(bankAccount), loanAmount, interestRate,loanDuration, status, personalReasons));
    }

    public void createVehicleLoan(BankAccount bankAccount, int loanAmount, double interestRate, int loanDuration, String status, VehicleInformation vehicleInformation) {

        bankAccount.addLoan(new VehicleLoan(bankAccount, generateLoanID(bankAccount), loanAmount, interestRate, loanDuration, status, vehicleInformation));
    }

    /**
     * Acquires BankAccount from method to guarantee unique id generation
     *
     * @return guaranteed unique id
     */
    public String generateLoanID(BankAccount bankAccount) {
        StringBuilder stringBuilder = new StringBuilder();

        bankAccount.getLoanMap().values().forEach((Loan loan) -> stringBuilder.append(loan.getLoanID()).append(','));

        return MathUtils.generateUniqueID(stringBuilder.toString());
    }

}


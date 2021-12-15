package com.logic.bankwiser.loans;

import java.util.Date;

public class PersonalLoan extends Loan {

    private String personalReasons;
    public PersonalLoan(int caseID, int loanAmount, double interestRate, Date maturityDate, String status, String personalReasons) {
        super(caseID, loanAmount, interestRate, maturityDate, status);
        this.personalReasons = personalReasons;
    }

    public String getPersonalReasons() {
        return personalReasons;
    }
}

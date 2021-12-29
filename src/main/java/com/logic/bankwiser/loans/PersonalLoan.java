package com.logic.bankwiser.loans;

import java.util.Date;

public class PersonalLoan extends Loan {

    private String personalReasons;
    public PersonalLoan(int loanID, int loanAmount, double interestRate, Date maturityDate, String status, String personalReasons) {
        super(loanID, loanAmount, interestRate, maturityDate, status);
        this.personalReasons = personalReasons;
    }

    public String getPersonalReasons() {
        return personalReasons;
    }
}

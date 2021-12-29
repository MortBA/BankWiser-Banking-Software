package com.logic.bankwiser.loans;

import java.util.Date;

public class PersonalLoan extends Loan {

    private String personalReasons;
    public PersonalLoan(int loanID, double loanAmount, double interestRate, int loanDuration, String status, String personalReasons) {
        super(loanID, loanAmount, interestRate, loanDuration, status);
        this.personalReasons = personalReasons;
    }

    public String getPersonalReasons() {
        return personalReasons;
    }


}

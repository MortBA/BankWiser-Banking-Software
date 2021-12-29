package com.logic.bankwiser.loans;

import java.util.Date;

public class HomeLoan extends Loan {

    private final String PROPERTY_INFO;
    public HomeLoan(int loanID, int loanAmount, double interestRate, Date maturityDate, String status, String propertyInfo) {
        super(loanID, loanAmount, interestRate, maturityDate, status);
        this.PROPERTY_INFO = propertyInfo;
    }

    public String getPropertyInfo() {
        return PROPERTY_INFO;
    }
}

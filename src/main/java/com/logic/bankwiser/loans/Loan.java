package com.logic.bankwiser.loans;

import java.util.Date;
public abstract class Loan {

    private final int LOAN_ID;
    private final int LOAN_AMOUNT; //Specified by user
    private final double INTEREST_RATE;
    private final Date MATURITY_DATE;
    private String status;


    public Loan(int loanID, int loanAmount, double interestRate, Date maturityDate, String status) {
        this.LOAN_ID = loanID;
        this.LOAN_AMOUNT = loanAmount;
        this.INTEREST_RATE = interestRate;
        this.MATURITY_DATE = maturityDate;
        this.status = "Pending";
    }

    public int getLoanID() {
        return LOAN_ID;
    }

    public int getLoanAmount() {
        return LOAN_AMOUNT;
    }

    public double getInterestRate() {
        return INTEREST_RATE;
    }

    public Date getMaturityDate() {
        return MATURITY_DATE;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

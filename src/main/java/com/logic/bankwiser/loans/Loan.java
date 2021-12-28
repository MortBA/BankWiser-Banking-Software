package com.logic.bankwiser.loans;

public abstract class Loan {

    private final int LOAN_ID;
    private final double LOAN_AMOUNT;
    private final double INTEREST_RATE;
    private final int LOAN_DURATION;
    private String status;


    public Loan(int loanID, double loanAmount, double interestRate, int loanDuration, String status) {
        this.LOAN_ID = loanID;
        this.LOAN_AMOUNT = loanAmount;
        this.INTEREST_RATE = interestRate;
        this.LOAN_DURATION = loanDuration;
        this.status = "Pending approval by user";
    }

    public int getLoanID() {
        return LOAN_ID;
    }

    public double getLoanAmount() {
        return LOAN_AMOUNT;
    }

    public double getInterestRate() {
        return INTEREST_RATE;
    }

    public int loanDuration() {
        return LOAN_DURATION;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

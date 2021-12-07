package com.logic.bankwiser.loans;

import java.util.Date;

public abstract class Loan {

    private final int CASE_ID;
    private final int LOAN_AMOUNT; //Specified by user
    private final double INTEREST_RATE;
    private final Date MATURITY_DATE;
    private String status;


    public Loan(int caseID, int loanAmount, double interestRate, Date maturityDate, String status) {
        //int min = 100000;
        //int max = 999999;
        //Random rand = new Random();
        //int caseID = rand.nextInt((max-min)+1) + min;

        this.CASE_ID = caseID;
        this.LOAN_AMOUNT = loanAmount;
        this.INTEREST_RATE = interestRate;
        this.MATURITY_DATE = maturityDate;
        this.status = status;
    }

    public int getCaseID() {
        return CASE_ID;
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

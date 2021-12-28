package com.logic.bankwiser.loans;


public class HomeLoan extends Loan {

    private HomeInformation homeInformation;
    public HomeLoan(int loanID, double loanAmount, double interestRate, int loanDuration, String status, HomeInformation homeInformation) {
        super(loanID, loanAmount, interestRate, loanDuration, status);
        this.homeInformation = homeInformation;
    }

    public HomeInformation getHomeInformation() {
        return homeInformation;
    }

}

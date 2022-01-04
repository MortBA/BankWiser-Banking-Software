package com.logic.bankwiser.loans;


import com.logic.bankwiser.bank_accounts.BankAccount;

public class HomeLoan extends Loan {

    private HomeInformation homeInformation;
    public HomeLoan(BankAccount bankAccount, String loanID, double loanAmount, double interestRate, int loanDuration, String status, HomeInformation homeInformation) {
        super(bankAccount, loanID, loanAmount, interestRate, loanDuration, status);
        this.homeInformation = homeInformation;
    }

    public HomeInformation getHomeInformation() {
        return homeInformation;
    }
}

package com.logic.bankwiser.loans;

import com.logic.bankwiser.bank_accounts.BankAccount;

public class PersonalLoan extends Loan {

    private String personalReasons;
    public PersonalLoan(BankAccount bankAccount, String loanID, double loanAmount, double interestRate, int loanDuration, String status, String personalReasons) {
        super(bankAccount, loanID, loanAmount, interestRate, loanDuration, status);
        this.personalReasons = personalReasons;
    }

    public String getPersonalReasons() {
        return personalReasons;
    }
}

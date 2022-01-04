package com.logic.bankwiser.loans;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.utils.Input;

import java.util.Objects;

/**
 * Loan abstract class
 *
 * @author Dragos Florinel Isar
 * @author Mathias Hallander
 */
public abstract class Loan {

    private final String LOAN_ID;
    private final String BANK_ACCOUNT_ID;
    private final double LOAN_AMOUNT; //Specified by user
    private final double INTEREST_RATE;
    private final int LOAN_DURATION;
    private String status;

    public Loan(BankAccount bankAccount, String loanID, double loanAmount, double interestRate, int loanDuration, String status) {
        String LOAN_IDENTIFIER = "L";
        this.LOAN_ID = bankAccount.getBankAccountID() + LOAN_IDENTIFIER + loanID;
        this.BANK_ACCOUNT_ID = bankAccount.getBankAccountID();
        this.LOAN_AMOUNT = loanAmount;
        this.INTEREST_RATE = interestRate;
        this.LOAN_DURATION = loanDuration;
        this.status = "Pending approval by user";
    }

    public String getLoanID() {
        return LOAN_ID;
    }

    public String getBankAccountID() {
        return BANK_ACCOUNT_ID;
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

    @Override
    public String toString() {
        return  "Loan id: " + this.LOAN_ID + Input.EOL +
                "Bank account id: " + this.BANK_ACCOUNT_ID + Input.EOL +
                "Loan amount: " + this.LOAN_AMOUNT + Input.EOL +
                "Loan interest: " + this.INTEREST_RATE + Input.EOL +
                "Loan duration: " + this.LOAN_DURATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return LOAN_ID.equals(loan.LOAN_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(LOAN_ID);
    }
}

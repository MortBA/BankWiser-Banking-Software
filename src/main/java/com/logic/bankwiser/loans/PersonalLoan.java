package com.logic.bankwiser.loans;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.utils.Input;

/**
 * A loan intended for personal use which does not involve a vehicle or home.
 *
 * @author Burak Askan
 * @author Dragos Florinel Isar
 * @author Mathias Hallander
 */
public class PersonalLoan extends Loan {

    private final String PERSONAL_REASONS;
    private final double PERSONAL_LOAN_INTEREST_RATE = 0.07;


    /**
     * Constructor for Loans of type PersonalLoan, that uses different parameters that receive input from user.
     * Uses the current bankAccount as a parameter in order to link the created loan to it.
     *
     * @param bankAccount
     * @param loanID
     * @param loanAmount
     * @param loanDuration
     * @param personalReasons
     */

    public PersonalLoan(BankAccount bankAccount, String loanID, double loanAmount, int loanDuration, String personalReasons) {
        super(bankAccount, loanID, loanAmount, loanDuration);
        this.PERSONAL_REASONS = personalReasons;
        super.setInterestRate(PERSONAL_LOAN_INTEREST_RATE);
    }

    public double getPersonalLoanInterestRate() {
        return PERSONAL_LOAN_INTEREST_RATE;
    }

    public String getPersonalReasons() {
        return PERSONAL_REASONS;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString() + Input.EOL);
        sb.append("Note from applicant: " + PERSONAL_REASONS);
        return sb.toString();
    }
}

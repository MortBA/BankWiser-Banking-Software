package com.logic.bankwiser.cards;
import com.logic.bankwiser.bank_accounts.BankAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * CreditCard class
 * Acts as the sub-class for the debitCard and object creditCard created here.
 *
 * @author Burak Askan
 *
 */
public class CreditCard extends DebitCard {

    private final BigDecimal MAX_CREDIT;
    private final double INTEREST;
    String monthlyPaymentDate;

    /**
     * @param pin           pin is set as given by user
     * @param maxCredit     max credit in which the credit-card has is calculated in controller and set in constructor
     */
    public CreditCard(BankAccount bankAccount, int pin, BigDecimal maxCredit) {

        super(bankAccount, pin);
        this.MAX_CREDIT = maxCredit;
        this.INTEREST = 0.3;
        this.monthlyPaymentDate = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).toString();
    }

    public BigDecimal getMaxCredit() {
        return this.MAX_CREDIT;
    }

    public LocalDateTime getMonthlyPaymentDate() {
        return LocalDateTime.parse(this.monthlyPaymentDate);
    }

    public double getInterest() {
        return this.INTEREST;
    }

    public void setMonthlyPaymentDate(LocalDateTime monthlyPaymentDate) {
        this.monthlyPaymentDate = monthlyPaymentDate.truncatedTo(ChronoUnit.DAYS).toString();
    }
}

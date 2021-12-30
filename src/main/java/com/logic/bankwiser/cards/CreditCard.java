package com.logic.bankwiser.cards;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * CreditCard class
 * Acts as the sub-class for the debitCard and object creditCard created here.
 *
 * @author Burak Askan
 *
 */
public class CreditCard extends DebitCard {

    final private BigDecimal MAX_CREDIT;
    final private double INTEREST;
    private LocalDate monthlyPaymentDate;

    public CreditCard(int pin, BigDecimal maxCredit){

        super(pin);
        this.MAX_CREDIT = maxCredit;
        this.INTEREST = 0.3;
        this.monthlyPaymentDate = LocalDate.now();
    }

    public BigDecimal getMaxCredit() {
        return this.MAX_CREDIT;
    }

    public LocalDate getMonthlyPaymentDate() {
        return this.monthlyPaymentDate;
    }

    public double getInterest() {
        return this.INTEREST;
    }

    public void setMonthlyPaymentDate(LocalDate monthlyPaymentDate) {
        this.monthlyPaymentDate = monthlyPaymentDate;
    }

}

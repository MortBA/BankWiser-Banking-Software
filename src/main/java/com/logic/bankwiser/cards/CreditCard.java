package com.logic.bankwiser.cards;
import java.math.BigDecimal;

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

    public CreditCard(int pin, boolean status, String region,
                      boolean onlineStatus, int expenditureMax, BigDecimal maxCredit){

        super(pin, status, region, onlineStatus, expenditureMax);
        this.MAX_CREDIT = maxCredit;
        this.INTEREST = 0.3;
    }

    public BigDecimal getMaxCredit() {
        return this.MAX_CREDIT;
    }

    public double getInterest() {
        return this.INTEREST;
    }

}

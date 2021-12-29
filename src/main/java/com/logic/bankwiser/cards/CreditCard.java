package com.logic.bankwiser.cards;
import java.time.LocalDate;
import java.util.Date;
import java.math.BigDecimal;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.bank_accounts.BankAccount;
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

    //TODO: Add the loans calculator to set maxCredit and interest.
    public CreditCard(LocalDate expirationDate, int pin, boolean status, String region,
                      boolean onlineStatus, int spenditureMax, BigDecimal maxCredit, double interest){

        super(expirationDate, pin, status, region, onlineStatus, spenditureMax);
        this.MAX_CREDIT = maxCredit;
        this.INTEREST = interest;
    }

    public BigDecimal getMaxCredit() {
        return this.MAX_CREDIT;
    }

    public double getInterest() {
        return this.INTEREST;
    }

}

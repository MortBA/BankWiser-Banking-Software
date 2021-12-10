package com.logic.bankwiser.cards;
import java.util.Date;
import java.math.BigDecimal;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.bank_accounts.BankAccount;

public class CreditCard extends DebitCard {

    final private BigDecimal MAX_CREDIT;
    final private double INTEREST;
    private BigDecimal spenditure;

    public CreditCard(int linkedAccount, String expirationDate, int pin, boolean status, String region, boolean onlineStatus, BigDecimal maxCredit, double interest){

        super(linkedAccount, expirationDate, pin, status, region, onlineStatus);
        this.MAX_CREDIT = maxCredit;
        this.INTEREST = interest;
      //  BigDecimal spenditure = 0; ERROR?

    }

    public BigDecimal getMaxCredit() {
        return this.MAX_CREDIT;
    }

    public double getInterest() {
        return this.INTEREST;
    }

}

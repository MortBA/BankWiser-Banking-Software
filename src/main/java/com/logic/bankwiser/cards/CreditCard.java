package com.logic.bankwiser.cards;

public class CreditCard extends Card {

    final private int MAX_CREDIT;
    final private int INTEREST;

    public CreditCard(int loanAmount,String expirationDate, int pin, String status, int maxCredit, int interest){

        super(loanAmount, expirationDate, pin, status);
        this.MAX_CREDIT=maxCredit;
        this.INTEREST=interest;

    }

    public int getMaxCredit() {
        return this.MAX_CREDIT;
    }

    public int getInterest() {
        return this.INTEREST;
    }

}

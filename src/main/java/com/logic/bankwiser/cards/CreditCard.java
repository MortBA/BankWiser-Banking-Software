package com.logic.bankwiser.cards;

public class CreditCard extends Card {

    final private int MAX_CREDIT;
    final private double INTEREST;
    private double repayment;

    public CreditCard(int linkedAccount, String expirationDate, int pin, String status, int maxCredit, double interest){

        super(linkedAccount, expirationDate, pin, status);
        this.MAX_CREDIT=maxCredit;
        this.INTEREST=interest;
        double repayment;

    }

    public int getMaxCredit() {
        return this.MAX_CREDIT;
    }

    public double getInterest() {
        return this.INTEREST;
    }

}

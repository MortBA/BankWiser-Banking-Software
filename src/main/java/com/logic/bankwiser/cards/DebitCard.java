package com.logic.bankwiser.cards;

import com.logic.bankwiser.bank_accounts.BankAccount;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Random;

/**
 * DebitCard class
 * Acts as the main parent-class for the creditCard and object debitCard created here.
 *
 * @author Burak Askan
 * @author Mathias Hallander
 */
public class DebitCard {

    private final String CARD_NUMBER;
    private final String BANK_ACCOUNT_ID;
    private final String CCV;
    private final String CREATION_DATE;
    private final String EXPIRATION_DATE;
    private int pin;
    private boolean frozenStatus;
    private String region;
    private boolean onlineStatus;
    private double expenditureMax;
    private String yearlyPaymentDate;


    /**
     * Logic contained sets values to other attributes not set by the user
     *
     * @param pin     pin is set by the user after it has been checked in controller
     */
    public DebitCard(BankAccount bankAccount, int pin) { //BankAccount

        int cardNumberMin = 0;
        int cardNumberMax = 9999999;

        Random cardNumberRand = new Random();
        String cardNumber = "" + cardNumberRand.nextInt((cardNumberMax-cardNumberMin) + 1) + cardNumberMin;
        cardNumber = cardNumber + "" + cardNumberRand.nextInt((cardNumberMax-cardNumberMin) + 1) + cardNumberMin;

        int CCVMin = 0;
        int CCVMax = 999;
        Random CCVRand = new Random();
        String CCV = "" + CCVRand.nextInt((CCVMax - CCVMin) + 1) + CCVMin;

        if (CCV.length() == 2) {
            CCV = "0" + CCV;
        } else if (CCV.length() == 1) {
            CCV = "00" + CCV;
        }

        StringBuilder zero = new StringBuilder();
        for (int i = 2; i < 16; i++) {
            if (cardNumber.length() == i) {
                for (int y = 2; i < 16; i++) {
                    zero.append(0);
                }
            }
        }
        cardNumber = zero + cardNumber;

        this.CARD_NUMBER = cardNumber;
        this.BANK_ACCOUNT_ID = bankAccount.getBankAccountID();
        this.CCV = CCV;
        this.CREATION_DATE = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        this.EXPIRATION_DATE = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).plusYears(3).toString();
        this.pin = pin;
        this.frozenStatus = false;
        this.region = "Sweden";
        this.onlineStatus = true;
        this.expenditureMax = 10000;
        this.yearlyPaymentDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
    }


    public String getCardNumber() {
        return this.CARD_NUMBER;
    }

    public String getCCV() {
        return this.CCV;
    }

    public LocalDateTime getExpirationDate() {
        return LocalDateTime.parse(this.EXPIRATION_DATE);
    }

    public LocalDateTime getCreationDate() {
        return LocalDateTime.parse(this.CREATION_DATE);
    }

    public int getPin() {
        return this.pin;
    }

    public boolean getFrozenStatus() {
        return this.frozenStatus;
    }

    public String getRegion() {
        return this.region;
    }

    public boolean getOnlineStatus() {
        return this.onlineStatus;
    }

    public double getExpenditureMax() {
        return this.expenditureMax;
    }

    public LocalDateTime getYearlyPaymentDate() {
        return LocalDateTime.parse(this.yearlyPaymentDate);
    }

    public String getBankAccountID() {
        return this.BANK_ACCOUNT_ID;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setFrozenStatus(boolean frozenStatus) {
        this.frozenStatus = frozenStatus;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public void setExpenditureMax(double expenditureMax) {
        this.expenditureMax = expenditureMax;
    }
    
    public void setYearlyPaymentDate(LocalDateTime yearlyPaymentDate) {
        this.yearlyPaymentDate = yearlyPaymentDate.truncatedTo(ChronoUnit.SECONDS).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebitCard debitCard = (DebitCard) o;
        return CARD_NUMBER.equals(debitCard.CARD_NUMBER);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CARD_NUMBER);
    }
}

package com.logic.bankwiser.cards;
import java.util.Random;

public abstract class Card {

    private final int LINKED_ACCOUNT;
    private final int CARD_NUMBER;
    private final int CCV;
    private final String EXPIRATION_DATE;
    private int pin;
    private String status;


    public Card(int linkedAccount, String expirationDate, int pin, String status){

        int cardNumberMin = 100000;
        int cardNumberMax = 999999;
        Random cardNumberRand = new Random();
        int cardNumber = cardNumberRand.nextInt((cardNumberMax-cardNumberMin)+1) + cardNumberMin;

        int CCVMin = 100000;
        int CCVMax = 999999;
        Random CCVRand = new Random();
        int CCV = CCVRand.nextInt((CCVMax-CCVMin)+1) + CCVMin;

        this.LINKED_ACCOUNT=linkedAccount;
        this.CARD_NUMBER=cardNumber;
        this.CCV=CCV;
        this.EXPIRATION_DATE = expirationDate;
        this.pin = pin;
        this.status = status;
    }

    public int getLinkedAccount() {return LINKED_ACCOUNT;}

    public int getCardNumber() {
        return CARD_NUMBER;
    }

    public int CCV() {
        return CCV;
    }

    public String getExpirationDate() {
        return EXPIRATION_DATE;
    }

    public String getStatus() {
        return status;
    }

    public void setPin(int pin) {
        this.pin=pin;
    }

    public void setStatus(String status) {
        this.status=status;
    }

}

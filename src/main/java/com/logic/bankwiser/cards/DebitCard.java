package com.logic.bankwiser.cards;
import java.util.Random;
import java.time.LocalDate;

/**
 * DebitCard class
 * Acts as the main parent-class for the creditCard and object debitCard created here.
 *
 * @author Burak Askan
 *
 */
public class DebitCard {

    private final String CARD_NUMBER;
    private final String CCV;
    private final LocalDate CREATION_DATE;
    private final LocalDate EXPIRATION_DATE;
    private int pin;
    private boolean status;
    private String region;
    private boolean onlineStatus;
    private double expenditureMax;


    /**
     *
     *cardNumber = assigned by system with use of random generated numbers with total 16 digits.
     * CCV = assigned by system with use of random generated numbers with total 3 digits.
     * currentDate = assigned by system by assigning current time.
     * expirationDate = assigned by system by taking current time + 3 years.
     */
    public DebitCard(int pin, boolean status, String region, boolean onlineStatus, double expenditureMax){ //BankAccount

        int cardNumberMin = 0;
        int cardNumberMax = 9999999;

        Random cardNumberRand = new Random();
        String cardNumber = "" + cardNumberRand.nextInt((cardNumberMax-cardNumberMin)+1) + cardNumberMin;
        cardNumber = cardNumber+""+ cardNumberRand.nextInt((cardNumberMax-cardNumberMin)+1) + cardNumberMin;

        int CCVMin = 0;
        int CCVMax = 999;
        Random CCVRand = new Random();
        String CCV = ""+CCVRand.nextInt((CCVMax-CCVMin)+1) + CCVMin;

        if (CCV.length()==2){
            CCV="0"+CCV;
        }else if (CCV.length()==1){
            CCV="00"+CCV;
        }

        String zero = "";
        for(int i = 2; i<16; i++){
            if (cardNumber.length() == i){
                for(int y = 2; i<16; i++){
                    zero = zero + ""+0;
                }
            }
        }
        cardNumber = zero+cardNumber;

        this.CREATION_DATE = LocalDate.now();

        this.EXPIRATION_DATE = LocalDate.now().plusYears(3);

        this.CARD_NUMBER = cardNumber;
        this.CCV = CCV;
        this.pin = pin;
        this.status = status;
        this.region = region;
        this.onlineStatus = onlineStatus;
        this.expenditureMax = expenditureMax;
    }


    public String getCardNumber() {
        return CARD_NUMBER;
    }

    public String getCCV() {
        return CCV;
    }

    public LocalDate getExpirationDate() {
        return EXPIRATION_DATE;
    }

    public LocalDate getCreationDate() {
        return CREATION_DATE;
    }

    public int getPin() {
        return pin;
    }

    public boolean getStatus() {
        return status;
    }

    public String getRegion() {
        return region;
    }

    public boolean getOnlineStatus() {
        return onlineStatus;
    }

    public double getExpenditureMax() {
        return expenditureMax;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public void setExpenditureMax(double expenditureMax){
        this.expenditureMax = expenditureMax;
    }
}

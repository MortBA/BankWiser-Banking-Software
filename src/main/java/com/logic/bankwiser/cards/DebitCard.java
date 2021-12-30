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
    private boolean frozenStatus;
    private String region;
    private boolean onlineStatus;
    private double expenditureMax;
    private LocalDate yearlyPaymentDate;


    /**
     *
     *cardNumber = assigned by system with use of random generated numbers with total 16 digits.
     * CCV = assigned by system with use of random generated numbers with total 3 digits.
     * currentDate = assigned by system by assigning current time.
     * expirationDate = assigned by system by taking current time + 3 years.
     */
    public DebitCard(int pin){ //BankAccount

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
        this.frozenStatus = false;
        this.region = "Sweden";
        this.onlineStatus = true;
        this.expenditureMax = 10000;
        this.yearlyPaymentDate = LocalDate.now();
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

    public boolean getFrozenStatus() {
        return frozenStatus;
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

    public LocalDate getYearlyPaymentDate() {return yearlyPaymentDate;}

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

    public void setExpenditureMax(double expenditureMax){
        this.expenditureMax = expenditureMax;
    }

    public void setYearlyPaymentDate(LocalDate yearlyPaymentDate) {this.yearlyPaymentDate = yearlyPaymentDate;}
}

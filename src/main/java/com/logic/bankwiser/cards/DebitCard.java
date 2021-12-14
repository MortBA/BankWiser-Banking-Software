package com.logic.bankwiser.cards;
import java.util.Random;
import java.util.Date;
import java.math.BigDecimal;
import javafx.util.Pair;
//Big Decimal

public class DebitCard {

    private final int LINKED_ACCOUNT;
    private final String CARD_NUMBER;
    private final String CCV;
    private final String EXPIRATION_DATE;
    private int pin;
    private boolean status;
    private String region;
    private boolean onlineStatus;


    public DebitCard(int linkedAccount, String expirationDate, int pin, boolean status, String region, boolean onlineStatus){ //BankAccount

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
        for(int i = 2; i<16;i++){
            if (cardNumber.length()==i){
                for(int y = 2; i<16; i++){
                    zero = zero+""+0;
                }
            }
        }
        cardNumber=zero+cardNumber;


        this.LINKED_ACCOUNT=linkedAccount;
        this.CARD_NUMBER=cardNumber;
        this.CCV=CCV;
        this.EXPIRATION_DATE = expirationDate;
        this.pin = pin;
        this.status = status;
        this.region = region;
        this.onlineStatus = onlineStatus;
    }

    public int getLinkedAccount() {return LINKED_ACCOUNT;}

    public String getCardNumber() {
        return CARD_NUMBER;
    }

    public String CCV() {
        return CCV;
    }

    public String getExpirationDate() {
        return EXPIRATION_DATE;
    }

    public boolean getStatus() {
        return status;
    }

    public String getRegion(){return region;}

    public boolean getOnlineStatus(){return onlineStatus;}

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRegion(String region){this.region = region;}

    public void setOnlineStatus(boolean onlineStatus){this.onlineStatus = onlineStatus;}

}

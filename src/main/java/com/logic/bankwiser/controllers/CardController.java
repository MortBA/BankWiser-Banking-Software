package com.logic.bankwiser.controllers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;
import com.logic.bankwiser.cards.CreditCard;
import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.storage.Storage;
import javafx.util.Pair;

public class CardController {

    private final Storage STORAGE;

    public CardController(Storage storage) {
        this.STORAGE = storage;
    }

    public void addCard(int linkedAccount, String expirationDate, int pin, boolean status, String region, boolean onlineStatus, BigDecimal maxCredit, double interest) {
        STORAGE.addCard(new CreditCard(linkedAccount, expirationDate, pin, status, region, onlineStatus, maxCredit, interest));
    }

    public void addCard(int linkedAccount, String expirationDate, int pin, boolean status, String region, boolean onlineStatus) {
        STORAGE.addCard(new DebitCard(linkedAccount, expirationDate, pin, status, region, onlineStatus));
    }

    public Pair<Boolean, String> checkPassword(int pin){
        String pinString = "" + pin;
        String failCause = "";
        boolean acceptablePassword = true;

        int pinOne = Integer.parseInt(String.valueOf(pinString.charAt(1)));
        int pinTwo = Integer.parseInt(String.valueOf(pinString.charAt(2)));
        int pinThree = Integer.parseInt(String.valueOf(pinString.charAt(3)));
        int pinFour = Integer.parseInt(String.valueOf(pinString.charAt(4)));

        for(int i=0; i<4; i++){
            for(int y=0; y<4; i++){
                if(Integer.parseInt(String.valueOf(pinString.charAt(i))) == Integer.parseInt(String.valueOf(pinString.charAt(y)))){
                    acceptablePassword = false;
                    failCause = "Invalid PIN: A number cannot be followed by the same one";
                }
            }
        }

        if(pinOne-pinTwo == -1||pinOne-pinTwo == 1){
            acceptablePassword = false;
            failCause="Invalid PIN: Your PIN cannot consist of numbers in consecutive order";
        }
        if(pinThree-pinFour == -1||pinThree-pinFour == 1){
            acceptablePassword = false;
            failCause="Invalid PIN: Your PIN cannot consist of numbers in consecutive order";
        }
        return new Pair<>(acceptablePassword, failCause);
    }

    public void modifyStatus(String cardNumber, boolean statusNew) {
        STORAGE.getCard(cardNumber).setStatus(statusNew);
    }

    public void modifyPin(String cardNumber, int pinNew){
        STORAGE.getCard(cardNumber).setPin(pinNew);
    }

    public void modifyRegion(String cardNumber, String region){STORAGE.getCard(cardNumber).setRegion(region);}

    public void modifyOnlineStatus(String cardNumber, boolean onlineStatus){STORAGE.getCard(cardNumber).setOnlineStatus(onlineStatus);}

    public void remainderDays(String cardNumber){ //Calculates remaining days until expiration

        Date dateDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = date.format(dateDate);
        int day = Integer.parseInt(currentDate.substring(8,10));
        int month = Integer.parseInt(currentDate.substring(5,7));
        int year = Integer.parseInt(currentDate.substring(0,4));

        String expirationDate = STORAGE.getCard(cardNumber).getExpirationDate();
        int expirationDay = Integer.parseInt(expirationDate.substring(8,10));
        int expirationMonth = Integer.parseInt(expirationDate.substring(5,7));
        int expirationYear = Integer.parseInt(expirationDate.substring(0,4));

        int remainderYear = (expirationYear-year)*12*30;

        int remainderDay = ((remainderYear+(expirationMonth*30)+expirationDay)-((month*30)+day));

    }

    public void deleteCard(String cardNumber) {
        for(int i = 0; i < STORAGE.getCardList().size(); i++) {
            if(cardNumber == STORAGE.getCardList().get(i).getCardNumber()) {
                STORAGE.getCardList().remove(i);
            }
        }
    }


}

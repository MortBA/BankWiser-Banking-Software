package com.logic.bankwiser.controllers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;
import com.logic.bankwiser.cards.CreditCard;
import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.storage.Storage;
import javafx.util.Pair;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CardController {

    private final Storage STORAGE;

    public CardController(Storage storage) {
        this.STORAGE = storage;
    }

    public String addCard(int linkedAccount, String expirationDate, int pin, boolean status, String region, boolean onlineStatus, int expenditureMax, BigDecimal maxCredit, double interest) {
        STORAGE.addCard(new CreditCard(linkedAccount, expirationDate, pin, status, region, onlineStatus, expenditureMax, maxCredit, interest));
        return "Your application for a debit card has been accepted. We’ll let you know when it will be shipped soon.";
    }

    // Modified method to use LocalDate rather than String -KC
    public String addCard(int linkedAccount, LocalDate expirationDate, int pin, boolean status, String region, boolean onlineStatus, int expenditureMax) {
        STORAGE.addCard(new DebitCard(linkedAccount, expirationDate, pin, status, region, onlineStatus, expenditureMax));
        return "Your application for a credit card had been submitted. We’ll let you know whether it had been accepted or rejected after evaluation.";
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
        if(pinThree-pinTwo == -1||pinThree-pinTwo == 1){
            acceptablePassword = false;
            failCause="Invalid PIN: Your PIN cannot consist of numbers in consecutive order";
        }
        if(pinThree-pinFour == -1||pinThree-pinFour == 1){
            acceptablePassword = false;
            failCause="Invalid PIN: Your PIN cannot consist of numbers in consecutive order";
        }
        return new Pair<>(acceptablePassword, failCause);
    }

    public String modifyStatus(String cardNumber, boolean statusNew) {
        STORAGE.getCard(cardNumber).setStatus(statusNew);
        if (statusNew){
            return "Your card has been successfully blocked.";
        }else{
            return "Your card has been successfully unblocked.";
        }
    }

    public String modifyPin(String cardNumber, int pinNew){
        STORAGE.getCard(cardNumber).setPin(pinNew);
        return "Your pin has been successfully changed.";
    }

    public void modifyRegion(String cardNumber, String region) {
        STORAGE.getCard(cardNumber).setRegion(region);
    }

    public String modifyOnlineStatus(String cardNumber, boolean onlineStatus) {
        STORAGE.getCard(cardNumber).setOnlineStatus(onlineStatus);
        if (onlineStatus){
            return "You successfully turned on online transactions.";
        }else{
            return "You successfully turned off online transactions.";
        }
    }

    public String modifyExpenditureMax(String cardNumber, int expenditureMax) {
        int oldLimit = STORAGE.getCard(cardNumber).getExpenditureMax();
        STORAGE.getCard(cardNumber).setExpenditureMax(expenditureMax);
        int newLimit = STORAGE.getCard(cardNumber).getExpenditureMax();
        return "You successfully changed your spending limit from "+ oldLimit +" to "+newLimit+".";
    }

    public void remainderDays(String cardNumber){ //Calculates remaining days until expiration

//        Date dateDate = new Date();
//        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//        String currentDate = date.format(dateDate);
//        int day = Integer.parseInt(currentDate.substring(8,10));
//        int month = Integer.parseInt(currentDate.substring(5,7));
//        int year = Integer.parseInt(currentDate.substring(0,4));
//
//        String expirationDate = STORAGE.getCard(cardNumber).getExpirationDate();
//        int expirationDay = Integer.parseInt(expirationDate.substring(8,10));
//        int expirationMonth = Integer.parseInt(expirationDate.substring(5,7));
//        int expirationYear = Integer.parseInt(expirationDate.substring(0,4));
//
//        int remainderYear = (expirationYear-year)*12*30;
//
//        int remainderDay = ((remainderYear+(expirationMonth*30)+expirationDay)-((month*30)+day));

          /*
            After some research, I see that Date is no longer really used in Java.
            Rather, LocalDate is the "new" way to go about doing things.
            The implementation below does not mean this method is finished; but rather,
            The three lines below replace the 15 lines above the comment.
            Hopefully this is a good starting point! -KC
           */
          LocalDate expirationDate = STORAGE.getCard(cardNumber).getExpirationDate();
          LocalDate dateToday = LocalDate.now();
          long remainderDays = ChronoUnit.DAYS.between(dateToday, expirationDate);
    }

    public String deleteCard(String cardNumber, int pin) {

        boolean cardExist = false;
        String returnMessage = "";
        for(int i = 0; i < STORAGE.getCardList().size(); i++) {
            if(cardNumber == STORAGE.getCardList().get(i).getCardNumber()) {
                STORAGE.getCardList().remove(i);
                cardExist = true;
                returnMessage = "Your card has been successfully terminated.";
            }
        }
        if (cardExist==false){
            returnMessage = "Card number you entered was not found in the list of your cards.";
        }
        return returnMessage;
    }


}

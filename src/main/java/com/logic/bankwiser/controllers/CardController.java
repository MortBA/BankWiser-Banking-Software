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
        Pair<Boolean, String> keyAcceptance = createPasswordCheck(pin);
        if(keyAcceptance.getKey()){
            STORAGE.addCard(new CreditCard(linkedAccount, expirationDate, pin, status, region, onlineStatus, expenditureMax, maxCredit, interest));
            return "Your application for a debit card has been accepted. We’ll let you know when it will be shipped soon.";
        }else{
            return keyAcceptance.getValue();
        }
    }

    // Modified method to use LocalDate rather than String -KC
    public String addCard(int linkedAccount, LocalDate expirationDate, int pin, boolean status, String region, boolean onlineStatus, int expenditureMax) {
        STORAGE.addCard(new DebitCard(linkedAccount, expirationDate, pin, status, region, onlineStatus, expenditureMax));
        return "Your application for a credit card had been submitted. We’ll let you know whether it had been accepted or rejected after evaluation.";
    }

    public Pair<Boolean, String> createPasswordCheck(int pin) {
        String pinString = "" + pin;
        String failCause = "";
        boolean acceptablePassword = true;

        int pinOne = Integer.parseInt(String.valueOf(pinString.charAt(1)));
        int pinTwo = Integer.parseInt(String.valueOf(pinString.charAt(2)));
        int pinThree = Integer.parseInt(String.valueOf(pinString.charAt(3)));
        int pinFour = Integer.parseInt(String.valueOf(pinString.charAt(4)));

        if(String.valueOf(pin).length()!=4){
            failCause = "Invalid Pin: PIN must be four digits";
        }

        if(pin<0){
            failCause = "Invalid PIN: PIN code cannot be negative numbers";
        }

        int duplicateNumberCounter = 0;
        for(int i=0; i<4; i++){
            for(int y=0; y<4; y++){
                if(Integer.parseInt(String.valueOf(pinString.charAt(i))) == Integer.parseInt(String.valueOf(pinString.charAt(y)))){
                    duplicateNumberCounter++;
                    if(duplicateNumberCounter==2){
                        acceptablePassword = false;
                        failCause = "Invalid PIN: A number cannot be repeated more than once";
                    }
                }
            }
        }

        if(pinOne-pinTwo == -1||pinOne-pinTwo == 1) {
            acceptablePassword = false;
            failCause="Invalid PIN: Your PIN cannot consist of numbers in consecutive order";
        }
        if(pinThree-pinTwo == -1||pinThree-pinTwo == 1) {
            acceptablePassword = false;
            failCause="Invalid PIN: Your PIN cannot consist of numbers in consecutive order";
        }
        if(pinThree-pinFour == -1||pinThree-pinFour == 1) {
            acceptablePassword = false;
            failCause="Invalid PIN: Your PIN cannot consist of numbers in consecutive order";
        }
        return new Pair<>(acceptablePassword, failCause);
    }

    public void cardPayments() {
        Date dateDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = date.format(dateDate);
        int day = Integer.parseInt(currentDate.substring(8,10));
        int month = Integer.parseInt(currentDate.substring(5,7));
        int year = Integer.parseInt(currentDate.substring(0,4));

        for(int i = 0; i < STORAGE.getCardList().size(); i++){
            String creationDate = STORAGE.getCardList().get(i).getCreationDate();
            int dayCreation = Integer.parseInt(currentDate.substring(8,10));
            int monthCreation = Integer.parseInt(currentDate.substring(5,7));
            int yearCreation = Integer.parseInt(currentDate.substring(0,4));

            if(month == monthCreation && day == dayCreation){
                int tempBankBalance = 0;
                tempBankBalance -= 5999;
            }

            if(day == dayCreation && (year != yearCreation || month != monthCreation)){
                int tempValue = 0;
                double tempBankBalance = 0;
                if(tempBankBalance < 0){
                    CreditCard currentCard = (CreditCard) STORAGE.getCardList().get(i);
                    tempBankBalance *= currentCard.getInterest();
                }
            }


        }
    }

    public String modifyStatus(String cardNumber, boolean statusNew) {
        for (int i = 0; i<STORAGE.getCardList().size(); i++){
            if(STORAGE.getCardList().get(i).getCardNumber()==cardNumber){
                STORAGE.getCard(cardNumber).setStatus(statusNew);
                if (statusNew){
                    return "Your card has been successfully blocked.";
                }else{
                    return "Your card has been successfully unblocked.";
                }
            }
        }
        return "Invalid input: Given card number does not exist!";
    }

    public String modifyPin(String cardNumber, int pinNew) {
        for (int i = 0; i<STORAGE.getCardList().size(); i++){
            if(STORAGE.getCardList().get(i).getCardNumber()==cardNumber){
                STORAGE.getCard(cardNumber).setPin(pinNew);
                return "Your pin has been successfully changed.";
            }
        }
        return "Invalid input: Given card number does not exist!";
    }

    public String modifyRegion(String cardNumber, String region) {
        for (int i = 0; i<STORAGE.getCardList().size(); i++){
            if(STORAGE.getCardList().get(i).getCardNumber()==cardNumber){
                STORAGE.getCard(cardNumber).setRegion(region);
                return "Your region has been successfully changed.";
            }
        }
        return "Invalid input: Given card number does not exist!";
    }

    public String modifyOnlineStatus(String cardNumber, boolean onlineStatus) {
        for (int i = 0; i<STORAGE.getCardList().size(); i++){
            if(STORAGE.getCardList().get(i).getCardNumber()==cardNumber){
                STORAGE.getCard(cardNumber).setOnlineStatus(onlineStatus);
                if (onlineStatus){
                    return "You successfully turned on online transactions.";
                }else{
                    return "You successfully turned off online transactions.";
                }
            }
        }
        return "Invalid input: Given card number does not exist!";
    }

    public String modifyExpenditureMax(String cardNumber, int expenditureMax) {
        if (expenditureMax<0){
            return "Invalid input: The new spending limit should not be negative.";
        }
        for (int i = 0; i<STORAGE.getCardList().size(); i++){
            if(STORAGE.getCardList().get(i).getCardNumber()==cardNumber){
                int oldLimit = STORAGE.getCard(cardNumber).getExpenditureMax();
                STORAGE.getCard(cardNumber).setExpenditureMax(expenditureMax);
                int newLimit = STORAGE.getCard(cardNumber).getExpenditureMax();
                return "You successfully changed your spending limit from "+ oldLimit +" to "+newLimit+".";
            }
        }
        return "Invalid input: Given card number does not exist!";
    }

    public String remainderDays(String cardNumber) { //Calculates remaining days until expiration
        String address = "";
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

        if(remainderDay<0){
            return "The card "+cardNumber+" expired and was terminated.";
        }else {
            return "Your card"+ cardNumber+" will expire in "+remainderDay+" days and will be terminated then. We have sent you a new one to "+address+".";
        }
    }

    public String deleteCard(String cardNumber, int pin, String terminationReasoning) {
        boolean cardExist = false;
        String returnMessage = "";
        if(terminationReasoning.equals("")){
            returnMessage = "You have to provide at least one reason why you wish to terminate your card.";
        }
        if(pin!=STORAGE.getCard(cardNumber).getPin()){
            returnMessage = "Incorrect PIN code.";
        }
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

    public String resetPin(String cardNumber, int oldPin, int newPin, int newPinConfirmation){
        if(STORAGE.getCard(cardNumber).getPin()==oldPin){
            if(newPin==newPinConfirmation){
                Pair<Boolean, String> keyAcceptance = createPasswordCheck(newPin);
                if(keyAcceptance.getKey()){
                    STORAGE.getCard(cardNumber).setPin(newPin);
                    return "Card’s PIN code has been changed successfully.";
                }else {
                    return keyAcceptance.getValue();
                }
            }else {
                return "Cannot change PIN code: New PIN code doesn’t match the one in the confirmation field.";
            }
        }else{
            return "Incorrect PIN code.";
        }
    }

    public boolean checkPin(String cardNumber, int pin){
        boolean pinCheck = false;
        if(STORAGE.getCard(cardNumber).getPin() == pin){
                pinCheck = true;
            }
        return pinCheck;
    }


}

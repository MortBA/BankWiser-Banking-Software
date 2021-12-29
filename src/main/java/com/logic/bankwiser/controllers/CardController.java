package com.logic.bankwiser.controllers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;

import com.google.gson.Gson;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.cards.Card;
import com.logic.bankwiser.cards.CreditCard;
import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.controllers.TransactionController;
import javafx.util.Pair;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class CardController {

    private final Storage STORAGE;
    private final TransactionController TRANSACTION_CONTROLLER;

    public CardController(Storage storage, TransactionController transactionController) {
        this.STORAGE = storage;
        this.TRANSACTION_CONTROLLER = transactionController;

    }

    /**
     * CardController class
     * Acts as the controller which contains all logic regarding bank cards which is passed to Facade.
     *
     * @author Burak Askan
     *
     */

    /**
     * adding a credit card into list in storage
     * @param pin = pin password for cards set by the user.
     * @param status = status in regards to being frozen or unfrozen.
     * @param region = to which region is the bank card locked to.
     * @param onlineStatus = if the card has permission to make online purchases.
     * @param monthlyIncome = income of the user to assess size of credit.
     * @param monthlyExpenses = expenses of the user to assess size of credit.
     * @return affirmative or negative string.
     */
    public String addCard(int pin, boolean status, String region, boolean onlineStatus, int expenditureMax, double monthlyIncome, double monthlyExpenses) {
        BigDecimal maxCredit;
        Pair<Boolean, BigDecimal> creditAssessment = calculateCredit(monthlyIncome, monthlyExpenses);
        if(creditAssessment.getKey()){
            maxCredit = creditAssessment.getValue();
        }else{
            return "Your application for a debit card has been denied.";
        }

        Pair<Boolean, String> keyAcceptance = createPasswordCheck(pin);
        if(keyAcceptance.getKey()){
            STORAGE.addCard(new CreditCard(pin, status, region, onlineStatus, expenditureMax, maxCredit));
            return "Your application for a debit card has been accepted. We’ll let you know when it will be shipped soon.";
        }else{
            return keyAcceptance.getValue();
        }
    }

    /**
     * Assess eligibility of the user and calculation of max credit (creditCards).
     * @param monthlyIncome
     * @param monthlyExpenses
     * @return
     */
    public Pair<Boolean, BigDecimal> calculateCredit(double monthlyIncome, double monthlyExpenses){
        boolean eligible = false;
        BigDecimal maxCredit = new BigDecimal(0);
        double incomeRemainder = monthlyIncome-monthlyExpenses;
        if (incomeRemainder>2000){
            eligible = true;
            maxCredit = new BigDecimal(incomeRemainder *0.5);
        }
        return new Pair<>(eligible, maxCredit);
    }

    /**
     * adding a debit card into list in storage
     * @param pin
     * @param status
     * @param region
     * @param onlineStatus
     * @param expenditureMax
     * @return
     */
    // Modified method to use LocalDate rather than String -KC
    public String addCard(int pin, boolean status, String region, boolean onlineStatus, int expenditureMax) {
        Pair<Boolean, String> keyAcceptance = createPasswordCheck(pin);
        if(keyAcceptance.getKey()){
            STORAGE.addCard(new DebitCard(pin, status, region, onlineStatus, expenditureMax));
            return "Your application for a credit card had been submitted. We’ll let you know whether it had been accepted or rejected after evaluation.";
        }else{
            return keyAcceptance.getValue();
        }
    }

    /**
     * Checks if given pin is legal
     * @param pin = pin selected by the user
     * @return affirmative or negative string and boolean
     *
     */
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



    //FOR BOTH PAYMENTS METHODS:
    //TODO: Make sure to add time, monthly and yearly, for the payments

    /**
     * Monthly credit card payments
     * Checks if payment due on any credit card. If there is then it does the payments.
     */
    public void creditCardPayment() {

        LocalDate dateToday = LocalDate.now();

        for(BankAccount bankAccount : STORAGE.getBankAccountMap().values()){
            if(bankAccount.getBalance().intValue() < 0){
                for(DebitCard card : bankAccount.getCardList()){

                    LocalDate creationDate = card.getCreationDate();

                    if(dateToday.getDayOfMonth() == creationDate.getDayOfMonth() && (dateToday.getYear() != creationDate.getYear() || dateToday.getMonth() != creationDate.getMonth())){
                        CreditCard creditCard = (CreditCard) card;

                        BigDecimal moneyTransfered = bankAccount.getBalance().multiply(BigDecimal.valueOf(creditCard.getInterest() * -1));
                        String paymentNote = "Credit payment on your credit-card number: " + card.getCardNumber();
                        TRANSACTION_CONTROLLER.transferMoney(bankAccount.getBankAccountID(), "0", moneyTransfered, paymentNote, LocalDate.now());
                    }
                }

            }
        }
    }

    /**
     * Annual card payments
     * Checks if annual payments are due on any cards. If there is then it does the payments.
     */
    public void annualCardPayment() {
        LocalDate dateToday = LocalDate.now();

        for(BankAccount bankAccount : STORAGE.getBankAccountMap().values()){

            for(DebitCard card : bankAccount.getCardList()){

                LocalDate creationDate = card.getCreationDate();
                long remainderDays = ChronoUnit.DAYS.between(creationDate, dateToday);
                if(remainderDays % 365 == 0){
                    CreditCard creditCard = (CreditCard) card;
                    String paymentNote = "Payment on your card number: " + card.getCardNumber();
                    BigDecimal moneyTransferred = new BigDecimal(999);
                    TRANSACTION_CONTROLLER.transferMoney(bankAccount.getBankAccountID(), "0", moneyTransferred, paymentNote, LocalDate.now());
                }
            }


        }
    }

    /**
     *
     * @param cardNumber = cardNumber of the card that will be modified
     * @param statusNew = the new status, of frozen or unfrozen, of card according to user choice
     * @return affirmative or negative string.
     */
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

    /**
     *
     * @param cardNumber = cardNumber of the card that will be modified
     * @param region = the new region that the user want the card to be locked to
     * @return affirmative or negative string.
     */
    public String modifyRegion(String cardNumber, String region) {
        for (int i = 0; i<STORAGE.getCardList().size(); i++){
            if(STORAGE.getCardList().get(i).getCardNumber()==cardNumber){
                STORAGE.getCard(cardNumber).setRegion(region);
                return "Your region has been successfully changed.";
            }
        }
        return "Invalid input: Given card number does not exist!";
    }

    /**
     *
     * @param cardNumber = cardNumber of the card that will be modified
     * @param onlineStatus = the new online access status the user wants
     * @return affirmative or negative string.
     */
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

    /**
     *
     * @param cardNumber = cardNumber of the card that will be modified
     * @param expenditureMax = the new maximum set on expenditure on the card
     * @return affirmative or negative string.
     */
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

    /**
     *
     * @param cardNumber = cardNumber of the card that will be reminded
     * @param userID = the user that will receive the reminder
     * @return string containing reminder of expiration coming close or card has already expired
     */
    public String remainderDays(String cardNumber, UUID userID) { //Calculates remaining days until expiration
        String address = "";
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

//        if(remainderDay<0){
//            return "The card "+cardNumber+" expired and was terminated.";
//        }else {
//            return "Your card"+ cardNumber+" will expire in "+remainderDay+" days and will be terminated then. We have sent you a new one to "+address+".";
//        }
        LocalDate expirationDate = STORAGE.getCard(cardNumber).getExpirationDate();
        LocalDate dateToday = LocalDate.now();
        long remainderDays = ChronoUnit.DAYS.between(dateToday, expirationDate);

        if(remainderDays<0){
            return "The card "+cardNumber+" expired and was terminated.";
        }else {
            return "Your card"+ cardNumber+" will expire in "+remainderDays+" days and will be terminated then. We have sent you a new one to "+STORAGE.getUserFromMap(userID).getAddress()+".";
        }
    }

    /**
     *
     * @param cardNumber = the number of the card that will get deleted
     * @param pin = the correct pin of the card
     * @param terminationReasoning = string with reasoning of termination
     * @return affirmative or negative string.
     */
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

    /**
     *
     * @param cardNumber = the card that will have the pin modified
     * @param oldPin = current pin of the card
     * @param newPin = the new pin the user want to change the old pin into
     * @param newPinConfirmation = same pin as newpin for confirmation
     * @return affirmative or negative string.
     */
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

    /**
     *
     * @param cardNumber = Number of the card that is getting pin checked
     * @param pin = the pin that user entered with that pin
     * @return boolean if the pin entered was correct ot not
     */

    public boolean checkPin(String cardNumber, int pin){
        boolean pinCheck = false;
        if(STORAGE.getCard(cardNumber).getPin() == pin){
                pinCheck = true;
            }
        return pinCheck;
    }


}

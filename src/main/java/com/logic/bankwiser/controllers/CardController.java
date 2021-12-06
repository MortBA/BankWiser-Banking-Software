package com.logic.bankwiser.controllers;

import com.logic.bankwiser.cards.CreditCard;
import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.storage.Storage;

public class CardController {

    private final Storage STORAGE;

    public CardController(Storage storage) {
        this.STORAGE = storage;
    }

    public void addCard(int linkedAccount,String expirationDate, int pin, String status, int maxCredit, double interest) {
        STORAGE.addCard(new CreditCard(linkedAccount, expirationDate, pin, status, maxCredit, interest));
    }

    public void addCard(int linkedAccount ,String expirationDate, int pin, String status) {
        STORAGE.addCard(new DebitCard(linkedAccount, expirationDate, pin, status));
    }

    public void modifyStatus(int cardNumber, String statusNew) {
        STORAGE.getCard(cardNumber).setStatus(statusNew);
    }

    public void modifyPin(int cardNumber, int pinNew){
        STORAGE.getCard(cardNumber).setPin(pinNew);
    }





}

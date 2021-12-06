package com.logic.bankwiser.controllers;

import com.logic.bankwiser.cards.CreditCard;
import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.storage.Storage;

public class CardController {

    private final Storage STORAGE;

    public CardController(Storage storage) {
        this.STORAGE = storage;
    }

    public void addCard(int loanAmount,String expirationDate, int pin, String status, int maxCredit, int interest) {
        STORAGE.addCard(new CreditCard(loanAmount, expirationDate, pin, status, maxCredit, interest));
    }

    public void addCard(int loanAmount,String expirationDate, int pin, String status, int linkedAccount) {
        STORAGE.addCard(new DebitCard(loanAmount, expirationDate, pin, status, linkedAccount));
    }
}

package com.logic.bankwiser.controllers;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.storage.Storage;

import java.util.Random;
import java.util.UUID;

public class BankAccountController {

    private final Storage storage;

    public BankAccountController(Storage storage) {
        this.storage = storage;
    }

    public String createBankAccount(UUID UserID, String bankAccountName) {
        try {
            int bankAccountID = generateBankAccountID();
            BankAccount bankAccount = new BankAccount(bankAccountID, bankAccountName);
            storage.addBankAccount(bankAccountID, bankAccount);
            storage.getUserFromMap(UserID).addBankAccount(bankAccount);
            return "New banking account " + bankAccountName + " has been created.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // TODO Needs to be modified to include checking for account is user's -K
    public void renameBankAccount(int bankAccountID, String bankAccountName) {
        if (bankAccountName.length() <= 30){
            for (BankAccount bankAccount : storage.getBankAccountMap().values()){
                if (bankAccountID == bankAccount.getBankAccountID()){
                    // TODO Will try to reduce chaining. Discussion of specialised
                    //  get methods (from maps) required with backend team. -K
                    storage.getBankAccountMap().get(bankAccountID).setBankAccountName(bankAccountName);
                }
            }
        }
    }

    //TODO Implement after UserAccount link is functional -K
    public void deleteBankAccount() {
        /*
        The user has permission to delete any bank account they own, provided
        it is not their only bank account left, and the bank account is empty.

        Should the user attempt to remove an account which does not fulfil this
        criteria, the system will inform the user that the action was not performed.
         */
    }

    // TODO Consider having generation of UIDs moved to a util class. -K
    public int generateBankAccountID() {
        final int MIN_BANK_ACCOUNT_ID = 10000000;
        final int MAX_BANK_ACCOUNT_ID = 99999999;
        Random rand = new Random();
        return (rand.nextInt((MAX_BANK_ACCOUNT_ID - MIN_BANK_ACCOUNT_ID) + 1) + MIN_BANK_ACCOUNT_ID);
    }
}

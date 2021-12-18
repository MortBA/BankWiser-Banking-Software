package com.logic.bankwiser.controllers;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.transactions.Transaction;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionController {

    private final Storage storage;

    public TransactionController(Storage storage) {
        this.storage = storage;
    }

    public String transferMoney(int senderBankAccountID, int receiverBankAccountID, BigDecimal moneyTransferred, String note, Date transactionDate) {
        StringBuilder sb = new StringBuilder();
        BankAccount senderBankAccount = storage.getBankAccount(senderBankAccountID);
        BankAccount receiverBankAccount = storage.getBankAccount(receiverBankAccountID);

        senderBankAccount.processPaymentRequest(false, moneyTransferred);
        receiverBankAccount.processPaymentRequest(true, moneyTransferred);
        senderBankAccount.addTransaction(new Transaction(senderBankAccountID, receiverBankAccountID, moneyTransferred.negate(), note, transactionDate));
        receiverBankAccount.addTransaction(new Transaction(senderBankAccountID, receiverBankAccountID, moneyTransferred, note, transactionDate));

        sb.append("Successfully sent ").append(moneyTransferred).append(" SEK from ").append(senderBankAccountID);
        sb.append(" to ").append(receiverBankAccountID).append(".");
        sb.append("Note: ").append(note);
        return sb.toString();
    }



}

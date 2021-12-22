package com.logic.bankwiser.controllers;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.transactions.Transaction;
import com.logic.bankwiser.utils.Input;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Collins
 */

public class TransactionController {

    private final Storage storage;

    public TransactionController(Storage storage) {
        this.storage = storage;
    }

    public String transferMoney(int senderBankAccountID, int receiverBankAccountID, BigDecimal moneyTransferred, String note, LocalDate transactionDate) {
        StringBuilder sb = new StringBuilder();
        BankAccount senderBankAccount = storage.getBankAccount(senderBankAccountID);
        BankAccount receiverBankAccount = storage.getBankAccount(receiverBankAccountID);

        senderBankAccount.processPaymentRequest(false, moneyTransferred);
        BigDecimal senderNewBalance = senderBankAccount.getBalance();
        receiverBankAccount.processPaymentRequest(true, moneyTransferred);
        BigDecimal receiverNewBalance = receiverBankAccount.getBalance();
        senderBankAccount.addTransaction(new Transaction(receiverBankAccountID, moneyTransferred.negate(), note, transactionDate, senderNewBalance));
        receiverBankAccount.addTransaction(new Transaction(senderBankAccountID, moneyTransferred, note, transactionDate, receiverNewBalance));

        sb.append("Successfully sent ").append(moneyTransferred).append(" SEK from ").append(senderBankAccountID);
        sb.append(" to ").append(receiverBankAccountID).append(".");
        sb.append("Note: ").append(note);
        return sb.toString();
    }

    public String viewTransactionHistory(int bankAccountID) {
        StringBuilder sb = new StringBuilder();
        List<Transaction> transactionList = storage.getBankAccount(bankAccountID).getTransactionList();

        for (Transaction transaction : transactionList) {
            sb.append(transaction.toString()).append(Input.EOL);
        }

        return sb.toString();
    }

}

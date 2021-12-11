package com.logic.bankwiser.bank_accounts;

import com.logic.bankwiser.cards.Card;
import com.logic.bankwiser.transactions.Transaction;
import com.logic.bankwiser.loans.Loan;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class BankAccount {

    private final int BANK_ACCOUNT_ID;
    private String bankAccountName;
    private BigDecimal balance;
    private List<Transaction> transactionList;
    private List<Loan> loanList;
    private List<Card> cardList;

    public BankAccount(int bankAccountID, String bankAccountName) throws Exception {
        if (bankAccountName.length() <= 30) {
            this.BANK_ACCOUNT_ID = bankAccountID;
            this.bankAccountName = bankAccountName;
            this.balance = new BigDecimal("0.00");
            this.transactionList = new ArrayList<>();
            this.loanList = new ArrayList<>();
        } else {
            throw new Exception("Cannot create a new account: The account name" +
                    bankAccountName + " is only " + bankAccountName.length() + " long.");
        }
    }

    public int getBankAccountID() {
        return BANK_ACCOUNT_ID;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public int getNumOfTransactions(){
        return transactionList.size();
    }
}

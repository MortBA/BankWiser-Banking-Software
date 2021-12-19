package com.logic.bankwiser.transactions;

import com.logic.bankwiser.bank_accounts.BankAccount;

import java.math.BigDecimal;
import java.util.Date;

public final class Transaction {

    private final int BANK_ACCOUNT_ID;
    private final BigDecimal MONEY_TRANSFERRED;
    private final String NOTE;
    private final Date TRANSACTION_DATE;
    private final BigDecimal BALANCE_AFTER_TRANSACTION;

    public Transaction (int bankAccountID, BigDecimal moneyTransferred,
                 String note, Date transactionDate, BigDecimal balanceAfterTransaction) {
        this.BANK_ACCOUNT_ID = bankAccountID;
        this.MONEY_TRANSFERRED = moneyTransferred;
        this.NOTE = note;
        this.TRANSACTION_DATE = transactionDate;
        this.BALANCE_AFTER_TRANSACTION = balanceAfterTransaction;
    }

    public int getBankAccountID() {
        return BANK_ACCOUNT_ID;
    }

    public BigDecimal getMoneyTransferred() {
        return MONEY_TRANSFERRED;
    }

    public String getNote() {
        return NOTE;
    }

    public Date getTransactionDate() {
        return TRANSACTION_DATE;
    }

    public BigDecimal getBalanceAfterTransaction() {
        return BALANCE_AFTER_TRANSACTION;
    }

    @Override
    public String toString(){
        return TRANSACTION_DATE + ", " + BANK_ACCOUNT_ID + ", " + MONEY_TRANSFERRED + ", " + BALANCE_AFTER_TRANSACTION;
    }
}

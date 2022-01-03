package com.logic.bankwiser.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 *
 * @author Kevin Collins
 * @author Mathias Hallander
 */

public final class Transaction {

    private final String TRANSACTION_ID;
    private final String BANK_ACCOUNT_ID;
    private final BigDecimal MONEY_TRANSFERRED;
    private final String NOTE;
    private final LocalDate TRANSACTION_DATE;
    private final BigDecimal BALANCE_AFTER_TRANSACTION;
    private final String TRANSACTION_IDENTIFIER = "T";

    public Transaction (String transactionID, String bankAccountID, BigDecimal moneyTransferred,
                 String note, LocalDate transactionDate, BigDecimal balanceAfterTransaction) {
        this.TRANSACTION_ID = bankAccountID + TRANSACTION_IDENTIFIER + transactionID;
        this.BANK_ACCOUNT_ID = bankAccountID;
        this.MONEY_TRANSFERRED = moneyTransferred;
        this.NOTE = note;
        this.TRANSACTION_DATE = transactionDate;
        this.BALANCE_AFTER_TRANSACTION = balanceAfterTransaction;
    }

    public String getTransactionID() {
        return TRANSACTION_ID;
    }

    public String getBankAccountID() {
        return BANK_ACCOUNT_ID;
    }

    public BigDecimal getMoneyTransferred() {
        return MONEY_TRANSFERRED;
    }

    public String getNote() {
        return NOTE;
    }

    public LocalDate getTransactionDate() {
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

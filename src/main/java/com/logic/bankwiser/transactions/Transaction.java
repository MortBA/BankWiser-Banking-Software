package com.logic.bankwiser.transactions;

import java.math.BigDecimal;
import java.util.Date;

public final class Transaction {

    private final int SENDER_BANK_ACCOUNT_ID;
    private final int RECEIVER_BANK_ACCOUNT_ID;
    private final BigDecimal MONEY_TRANSFERRED;
    private final String NOTE;
    private final Date TRANSACTION_DATE;

    public Transaction (int senderBankAccountID, int receiverBankAccountID, BigDecimal moneyTransferred,
                 String note, Date transactionDate) {
        this.SENDER_BANK_ACCOUNT_ID = senderBankAccountID;
        this.RECEIVER_BANK_ACCOUNT_ID = receiverBankAccountID;
        this.MONEY_TRANSFERRED = moneyTransferred;
        this.NOTE = note;
        this.TRANSACTION_DATE = transactionDate;
    }

    public int getSenderBankAccountID() {
        return SENDER_BANK_ACCOUNT_ID;
    }

    public int getReceiverBankAccountID() {
        return RECEIVER_BANK_ACCOUNT_ID;
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
}

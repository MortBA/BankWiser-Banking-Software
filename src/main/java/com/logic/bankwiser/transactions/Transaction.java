package com.logic.bankwiser.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Object representation of a transaction event.
 * This object stores all information relevant to its creation.
 * As it is a representation of an event it stores its own identifier, time  date, and relevant bank account id.
 *
 * @author Kevin Collins
 * @author Mathias Hallander
 */
public final class Transaction {

    private final String TRANSACTION_ID;
    private final String BANK_ACCOUNT_ID;
    private final BigDecimal MONEY_TRANSFERRED;
    private final String NOTE;
    private final String TRANSACTION_DATE;
    private final BigDecimal BALANCE_AFTER_TRANSACTION;


    /**
     * Constructor for Transaction objects.
     *
     * @param transactionID
     * @param bankAccountID
     * @param moneyTransferred
     * @param note
     * @param transactionDate
     * @param balanceAfterTransaction
     */

    /**
     * Constructor for the Transaction class.
     * @param transactionID
     * @param bankAccountID
     * @param moneyTransferred
     * @param note
     * @param transactionDate
     * @param balanceAfterTransaction
     */
    public Transaction(String transactionID, String bankAccountID, BigDecimal moneyTransferred,
                       String note, LocalDateTime transactionDate, BigDecimal balanceAfterTransaction) {
        String TRANSACTION_IDENTIFIER = "T";
        this.TRANSACTION_ID = bankAccountID + TRANSACTION_IDENTIFIER + transactionID;
        this.BANK_ACCOUNT_ID = bankAccountID;
        this.MONEY_TRANSFERRED = moneyTransferred;
        this.NOTE = note;
        this.TRANSACTION_DATE = transactionDate.toString();
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

    public LocalDateTime getTransactionDate() {
        return LocalDateTime.parse(TRANSACTION_DATE);
    }

    public BigDecimal getBalanceAfterTransaction() {
        return BALANCE_AFTER_TRANSACTION;
    }

    @Override
    public String toString() {
        return TRANSACTION_DATE + ", " + BANK_ACCOUNT_ID + ", " + MONEY_TRANSFERRED + ", " + BALANCE_AFTER_TRANSACTION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return TRANSACTION_ID.equals(that.TRANSACTION_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TRANSACTION_ID);
    }
}

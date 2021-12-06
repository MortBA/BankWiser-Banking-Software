package bank_accounts;

import transactions.Transaction;
import loans.Loan;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.math.BigDecimal;

public class BankAccount {

    private final int BANK_ACCOUNT_ID;
    private String bankAccountName;
    private BigDecimal balance;
    private List<Transaction> transactionList;
    private List<Loan> loanList;

    public BankAccount(int bankAccountID, String bankAccountName) {
        this.BANK_ACCOUNT_ID = bankAccountID;
        this.bankAccountName = bankAccountName;
        this.balance = new BigDecimal(0.00);
        this.transactionList = new ArrayList<>();
        this.loanList = new ArrayList<>();
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

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public int getNumOfTransactions(){
        return transactionList.size();
    }
}

package com.logic.bankwiser.storage;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.cards.Card;
import com.logic.bankwiser.loans.Loan;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;

public class Storage {

    private final List<Integer> caseIDList;
    private final List<Loan> loanList;
    private final List<Card> cardList;
    private final LinkedHashMap<Integer, BankAccount> bankAccountMap;

    public Storage() {
        caseIDList = new ArrayList<>();
        loanList = new ArrayList<>();
        cardList = new ArrayList<>();
        bankAccountMap = new LinkedHashMap<>();
    }

    public LinkedHashMap<Integer, BankAccount> getBankAccountMap() {
        return bankAccountMap;
    }

    public void addCaseID(int caseID) {
        caseIDList.add(caseID);
    }

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }

    public void addCard(Card card){
        cardList.add(card);
    }

    public void addBankAccount(int bankAccountID, BankAccount bankAccount) {
        bankAccountMap.put(bankAccountID, bankAccount);
    }

}

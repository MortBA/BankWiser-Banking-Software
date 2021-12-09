package com.logic.bankwiser.storage;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.cards.Card;
import com.logic.bankwiser.loans.Loan;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.UUID;

public class Storage {

    private final List<Integer> caseIDList;
    private final List<Loan> loanList;
    private final List<Card> cardList;
    private final LinkedHashMap<Integer, BankAccount> bankAccountMap;
    private final LinkedHashMap<UUID, UserAccount> userAccountMap;

    public Storage() {
        caseIDList = new ArrayList<>();
        loanList = new ArrayList<>();
        cardList = new ArrayList<>();
        bankAccountMap = new LinkedHashMap<>();
        userAccountMap = new LinkedHashMap<>();
    }

    public List<Card> getCardList() {
        return cardList;
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

    public void addUserAccount(UUID userAccountID, UserAccount userAccount) {
        userAccountMap.put(userAccountID, userAccount);
    }

    //TEMPORARY HELPER METHODS
    public Card getCard(int cardNumber){
        int cardIndex=0;
        for(int i = 0; i<cardList.size();i++){
            if(cardNumber==cardList.get(i).getCardNumber()){
                cardIndex=i;
            }
        }
        return cardList.get(cardIndex);
    }


}

package com.logic.bankwiser.storage;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.loans.Loan;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.UUID;

public class Storage {

    private final List<Integer> caseIDList;
    private final LinkedHashMap<String, Loan> loanMap;
    private final List<DebitCard> cardList;
    private final LinkedHashMap<String, BankAccount> bankAccountMap;
    private final LinkedHashMap<String, UUID> userEmailMap;
    private final LinkedHashMap<UUID, UserAccount> userAccountMap;

    public Storage() {
        caseIDList = new ArrayList<>();
        loanMap = new LinkedHashMap<>();
        cardList = new ArrayList<>();
        bankAccountMap = new LinkedHashMap<>();
        userEmailMap = new LinkedHashMap<>();
        userAccountMap = new LinkedHashMap<>();
    }

    public List<DebitCard> getCardList() {
        return cardList;
    }

    public LinkedHashMap<String, BankAccount> getBankAccountMap() {
        return bankAccountMap;
    }

    public UserAccount getUserFromMap(UUID userID) {
        return userAccountMap.get(userID);
    }

    public BankAccount getBankAccount(String bankAccountID) {
        return bankAccountMap.get(bankAccountID);
    }

    public void addCaseID(int caseID) {
        caseIDList.add(caseID);
    }

    public void addLoan(Loan loan) {
        //loanMap.put(loan.getLoanID(), loan);
    }

    public void addCard(DebitCard card){
        cardList.add(card);
    }

    public void addBankAccount(String bankAccountID, BankAccount bankAccount) {
        bankAccountMap.put(bankAccountID, bankAccount);
    }

    public void addUserAccount(UUID userAccountID, UserAccount userAccount) {
        userAccountMap.put(userAccountID, userAccount);
        userEmailMap.put(userAccount.getEmailID(), userAccountID);
    }

    public UUID getUserUUID(String email) {
        return userEmailMap.get(email);
    }

    //TEMPORARY HELPER METHODS
    public DebitCard getCard(String cardNumber){
        int cardIndex=0;
        for(int i = 0; i<cardList.size();i++){
            if(cardNumber==cardList.get(i).getCardNumber()){
                cardIndex=i;
            }
        }
        return cardList.get(cardIndex);
    }


}

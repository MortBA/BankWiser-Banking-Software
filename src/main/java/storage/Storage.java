package storage;

import cards.Cards;
import loans.Loan;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final List<Integer> caseIDList;
    private final List<Loan> loanList;
    private final List<Cards> cardList;

    public Storage() {
        caseIDList = new ArrayList<>();
        loanList = new ArrayList<>();
        cardList = new ArrayList<>();
    }

    public void addCaseID(int caseID) {
        caseIDList.add(caseID);
    }

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }

    public void addCard(Cards card){ cardList.add(card);}
}

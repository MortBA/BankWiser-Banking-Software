package storage;

import loans.Loan;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final List<Integer> caseIDList;
    private final List<Loan> loanList;

    public Storage() {
        caseIDList = new ArrayList<>();
        loanList = new ArrayList<>();
    }

    public void addCaseID(int caseID) {
        caseIDList.add(caseID);
    }

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }
}

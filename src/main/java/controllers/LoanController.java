package controllers;

import storage.Storage;
import java.util.Date;

public class LoanController {

    private final Storage storage;

    public LoanController(Storage storage) {
        this.storage = storage;
    }

//    public void createLoan(int loanAmount, double interestRate, Date maturityDate, String status){
//        storage.addCaseID();
//        storage.addLoan(new Loan(loanAmount, interestRate, maturityDate, status));
//    }

}

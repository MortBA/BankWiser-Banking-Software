package com.logic.bankwiser.storage;

import com.google.gson.Gson;
import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.cards.CreditCard;
import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.loans.HomeLoan;
import com.logic.bankwiser.loans.Loan;
import com.logic.bankwiser.loans.PersonalLoan;
import com.logic.bankwiser.loans.VehicleLoan;
import com.logic.bankwiser.transactions.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * This Storage class acts as the sole responsible class for the interaction between this program
 * and its storage files. Using the GSON library, we are able to convert Java objects to and from JSON,
 * and with Java Files we can read and write that information to normal text files.
 * The system uses a series of "stores" and "retrievers" to facilitate the storage of all relevant information.
 * All storage methods utilize HashSets in order to guarantee that no duplicates exist in storage, as well
 * as facilitating the ability for individual objects to be overwritten if a change has been made.
 * <p>
 * Due to the limitations imposed by exclusively using JSON and text files, and the lack of a database,
 * there are more IO calls than would normally occur in a program like this. Although the function of overwriting
 * individual objects works exactly as written, it still requires that the entire file is both read and rewritten
 * in its entirety. This will have performance implications down the line, unless a database solution is implemented.
 * <p>
 * There are a couple sections in this class which could have used Generics or similar to reduce repetition,
 * however, as this project requires that anyone in the project should be able to answer any question about it,
 * it is not feasible to teach generics for this purpose.
 *
 * @author Burak Askan
 * @author Kevin Collins
 * @author Mathias Hallander
 */

//TODO add individual storage sections to controllers
public class Storage {

    protected final HashMap<UUID, UserAccount> userAccountMap;
    protected final HashMap<String, UUID> userEmailMap;
    protected final HashMap<String, BankAccount> bankAccountMap;
    private final HashMap<Integer, UserAccount> requestMap;
    private final List<String> errorReportList;
    protected final Gson gson;
    private boolean tests = false;

    /**
     * Constructor for the Storage branches
     */
    public Storage() {
        userAccountMap = new HashMap<>();
        userEmailMap = new HashMap<>();
        bankAccountMap = new HashMap<>();
        requestMap = new HashMap<>();
        errorReportList = new ArrayList<>();
        gson = new Gson();

        try {
            retrieveUsers();
            retrieveBankAccounts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Storage(boolean test) {
        userAccountMap = new HashMap<>();
        userEmailMap = new HashMap<>();
        bankAccountMap = new HashMap<>();
        requestMap = new HashMap<>();
        errorReportList = new ArrayList<>();
        gson = new Gson();
        tests = test;
    }

    public HashMap<String, BankAccount> getBankAccountMap() {
        return bankAccountMap;
    }

    public HashMap<UUID, UserAccount> getUserAccountMap() {
        return userAccountMap;
    }

    public HashMap<String, UUID> getUserEmailMap() {
        return userEmailMap;
    }

    public UserAccount getUserFromMap(UUID userID) {
        return userAccountMap.get(userID);
    }

    public UserAccount getUserFromMap(String username) {
        return userAccountMap.get(userEmailMap.get(username));
    }

    public BankAccount getBankAccount(String bankAccountID) {
        return bankAccountMap.get(bankAccountID);
    }

    public HashMap<Integer, UserAccount> getRequestMap() {
        return requestMap;
    }

    public void addBankAccount(String bankAccountID, BankAccount bankAccount) {
        bankAccountMap.put(bankAccountID, bankAccount);
        try {
            if (!tests) {
                storeBankAccounts(bankAccount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUserAccount(UUID userAccountID, UserAccount userAccount) {
        userAccountMap.put(userAccountID, userAccount);
        userEmailMap.put(userAccount.getEmailID(), userAccountID);
        try {
            if (!tests) {
                storeUsers(userAccount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDeleteUserRequest(UserAccount userAccount) {
        requestMap.put(requestMap.size(), userAccount);
    }

    public void addErrorReport(String errorReport) {
        errorReportList.add(errorReport);
    }

    public UUID getUserUUID(String email) {
        return userEmailMap.get(email);
    }

    public void storeAll() throws IOException {
        storeUsers();
        storeBankAccounts();
        storeTransactions();
        storeDebitCards();
        storeCreditCards();
        storePersonalLoans();
        storeHomeLoans();
        storeVehicleLoans();
    }

    public void storeUsers() throws IOException {
        Path path = StoragePaths.USERS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<UserAccount> userAccountHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String userString) -> userAccountHashSet.add(gson.fromJson(userString, UserAccount.class)));

        userAccountMap.values().forEach((UserAccount userAccount) -> {
            if (!userAccountHashSet.add(userAccount)) {
                userAccountHashSet.remove(userAccount);
                userAccountHashSet.add(userAccount);
            }
        });

        userAccountHashSet.forEach((UserAccount userAccount) -> content.add(gson.toJson(userAccount)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeUsers(UserAccount userAccountToStore) throws IOException {
        Path path = StoragePaths.USERS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<UserAccount> userAccountHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String userString) -> userAccountHashSet.add(gson.fromJson(userString, UserAccount.class)));

        if (!userAccountHashSet.add(userAccountToStore)) {
            userAccountHashSet.remove(userAccountToStore);
            userAccountHashSet.add(userAccountToStore);
        }

        userAccountHashSet.forEach((UserAccount userAccount) -> content.add(gson.toJson(userAccount)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeBankAccounts() throws IOException {
        Path path = StoragePaths.BANK_ACCOUNTS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<BankAccount> bankAccountHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String bankAccountString) -> bankAccountHashSet.add(gson.fromJson(bankAccountString, BankAccount.class)));

        bankAccountMap.values().forEach((BankAccount bankAccount) -> {
            if (!bankAccountHashSet.add(bankAccount)) {
                bankAccountHashSet.remove(bankAccount);
                bankAccountHashSet.add(bankAccount);
            }
        });

        bankAccountHashSet.forEach((BankAccount bankAccount) -> content.add(gson.toJson(bankAccount)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeBankAccounts(BankAccount bankAccountToStore) throws IOException {
        Path path = StoragePaths.BANK_ACCOUNTS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<BankAccount> bankAccountHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String bankAccountString) -> bankAccountHashSet.add(gson.fromJson(bankAccountString, BankAccount.class)));

        if (!bankAccountHashSet.add(bankAccountToStore)) {
            bankAccountHashSet.remove(bankAccountToStore);
            bankAccountHashSet.add(bankAccountToStore);
        }

        bankAccountHashSet.forEach((BankAccount bankAccount) -> content.add(gson.toJson(bankAccount)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeTransactions() throws IOException {
        Path path = StoragePaths.TRANSACTIONS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<Transaction> transactionHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String transactionString) -> transactionHashSet.add(gson.fromJson(transactionString, Transaction.class)));

        bankAccountMap.values().forEach((BankAccount bankAccount) -> bankAccount.getTransactionMap().values().forEach((Transaction transaction) -> {
            if (!transactionHashSet.add(transaction)) {
                transactionHashSet.remove(transaction);
                transactionHashSet.add(transaction);
            }
        }));

        transactionHashSet.forEach((Transaction transaction) -> content.add(gson.toJson(transaction)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeTransactions(Transaction senderTransactionToStore, Transaction receiverTransactionToStore) throws IOException {
        Path path = StoragePaths.TRANSACTIONS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<Transaction> transactionHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String transactionString) -> transactionHashSet.add(gson.fromJson(transactionString, Transaction.class)));
        
        if (!transactionHashSet.add(senderTransactionToStore)) {
            transactionHashSet.remove(senderTransactionToStore);
            transactionHashSet.add(senderTransactionToStore);
        }
        if (!transactionHashSet.add(receiverTransactionToStore)) {
            transactionHashSet.remove(receiverTransactionToStore);
            transactionHashSet.add(receiverTransactionToStore);
        }

        transactionHashSet.forEach((Transaction transaction) -> content.add(gson.toJson(transaction)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storePersonalLoans() throws IOException {
        Path path = StoragePaths.PERSONAL_LOANS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<PersonalLoan> personalLoanHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String loanString) -> personalLoanHashSet.add(gson.fromJson(loanString, PersonalLoan.class)));

        bankAccountMap.values().forEach((BankAccount bankAccount) -> bankAccount.getLoanMap().values().forEach((Loan loan) -> {
            if (loan instanceof PersonalLoan) {
                if (!personalLoanHashSet.add((PersonalLoan) loan)) {
                    personalLoanHashSet.remove(loan);
                    personalLoanHashSet.add((PersonalLoan) loan);
                }
            }
        }));

        personalLoanHashSet.forEach((PersonalLoan loan) -> content.add(gson.toJson(loan)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storePersonalLoans(PersonalLoan loanToStore) throws IOException {
        Path path = StoragePaths.PERSONAL_LOANS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<PersonalLoan> personalLoanHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String loanString) -> personalLoanHashSet.add(gson.fromJson(loanString, PersonalLoan.class)));

        if (!personalLoanHashSet.add(loanToStore)) {
            personalLoanHashSet.remove(loanToStore);
            personalLoanHashSet.add(loanToStore);
        }

        personalLoanHashSet.forEach((PersonalLoan loan) -> content.add(gson.toJson(loan)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeHomeLoans() throws IOException {
        Path path = StoragePaths.HOME_LOANS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<HomeLoan> homeLoanHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String loanString) -> homeLoanHashSet.add(gson.fromJson(loanString, HomeLoan.class)));

        bankAccountMap.values().forEach((BankAccount bankAccount) -> bankAccount.getLoanMap().values().forEach((Loan loan) -> {
            if (loan instanceof HomeLoan) {
                if (!homeLoanHashSet.add((HomeLoan) loan)) {
                    homeLoanHashSet.remove(loan);
                    homeLoanHashSet.add((HomeLoan) loan);
                }
            }
        }));

        homeLoanHashSet.forEach((HomeLoan loan) -> content.add(gson.toJson(loan)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeHomeLoans(HomeLoan loanToStore) throws IOException {
        Path path = StoragePaths.HOME_LOANS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<HomeLoan> homeLoanHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String loanString) -> homeLoanHashSet.add(gson.fromJson(loanString, HomeLoan.class)));

        if (!homeLoanHashSet.add(loanToStore)) {
            homeLoanHashSet.remove(loanToStore);
            homeLoanHashSet.add(loanToStore);
        }

        homeLoanHashSet.forEach((HomeLoan loan) -> content.add(gson.toJson(loan)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
    
    public void storeVehicleLoans() throws IOException {
        Path path = StoragePaths.VEHICLE_LOANS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<VehicleLoan> vehicleLoanHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String loanString) -> vehicleLoanHashSet.add(gson.fromJson(loanString, VehicleLoan.class)));

        bankAccountMap.values().forEach((BankAccount bankAccount) -> bankAccount.getLoanMap().values().forEach((Loan loan) -> {
            if (loan instanceof VehicleLoan) {
                if (!vehicleLoanHashSet.add((VehicleLoan) loan)) {
                    vehicleLoanHashSet.remove(loan);
                    vehicleLoanHashSet.add((VehicleLoan) loan);
                }
            }
        }));

        vehicleLoanHashSet.forEach((Loan loan) -> content.add(gson.toJson(loan)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeVehicleLoans(VehicleLoan loanToStore) throws IOException {
        Path path = StoragePaths.VEHICLE_LOANS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<VehicleLoan> vehicleLoanHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String loanString) -> vehicleLoanHashSet.add(gson.fromJson(loanString, VehicleLoan.class)));

        if (!vehicleLoanHashSet.add(loanToStore)) {
            vehicleLoanHashSet.remove(loanToStore);
            vehicleLoanHashSet.add(loanToStore);
        }

        vehicleLoanHashSet.forEach((Loan loan) -> content.add(gson.toJson(loan)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
    
    public void storeDebitCards() throws IOException {
        Path path = StoragePaths.DEBIT_CARDS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<DebitCard> debitCardHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String cardString) -> debitCardHashSet.add(gson.fromJson(cardString, DebitCard.class)));

        bankAccountMap.values().forEach((BankAccount bankAccount) -> bankAccount.getCardMap().values().forEach((DebitCard debitCard) -> {
            if (!(debitCard instanceof CreditCard)) {
                if (!debitCardHashSet.add(debitCard)) {
                    debitCardHashSet.remove(debitCard);
                    debitCardHashSet.add(debitCard);
                }
            }
        }));

        debitCardHashSet.forEach((DebitCard debitCard) -> content.add(gson.toJson(debitCard)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeDebitCards(DebitCard cardToStore) throws IOException {
        Path path = StoragePaths.DEBIT_CARDS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<DebitCard> debitCardHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String cardString) -> debitCardHashSet.add(gson.fromJson(cardString, DebitCard.class)));

        if (!debitCardHashSet.add(cardToStore)) {
            debitCardHashSet.remove(cardToStore);
            debitCardHashSet.add(cardToStore);
        }

        debitCardHashSet.forEach((DebitCard debitCard) -> content.add(gson.toJson(debitCard)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeCreditCards() throws IOException {
        Path path = StoragePaths.CREDIT_CARDS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<CreditCard> creditCardHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String cardString) -> creditCardHashSet.add(gson.fromJson(cardString, CreditCard.class)));

        bankAccountMap.values().forEach((BankAccount bankAccount) -> bankAccount.getCardMap().values().forEach((DebitCard card) -> {
            if (card instanceof CreditCard) {
                if (!creditCardHashSet.add((CreditCard) card)) {
                    creditCardHashSet.remove(card);
                    creditCardHashSet.add((CreditCard) card);
                }
            }
        }));
        creditCardHashSet.forEach((CreditCard creditCard) -> content.add(gson.toJson(creditCard)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void storeCreditCards(CreditCard cardToStore) throws IOException {
        Path path = StoragePaths.CREDIT_CARDS.getPath();
        List<String> content = new ArrayList<>();
        HashSet<CreditCard> creditCardHashSet = new HashSet<>();
        Files.readAllLines(path).forEach((String cardString) -> creditCardHashSet.add(gson.fromJson(cardString, CreditCard.class)));

        if (!creditCardHashSet.add(cardToStore)) {
            creditCardHashSet.remove(cardToStore);
            creditCardHashSet.add(cardToStore);
        }

        creditCardHashSet.forEach((CreditCard creditCard) -> content.add(gson.toJson(creditCard)));

        Files.write(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    //TODO finalize implementation
    public void storeRequests() throws IOException {
        Path path = StoragePaths.REQUESTS.getPath();
        List<String> content = new ArrayList<>();

    }

    public void retrieveUsers() throws IOException {
        String file = StoragePaths.USERS.getStringPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        userAccountMap.clear();
        userEmailMap.clear();

        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            UserAccount userAccount = gson.fromJson(currentLine, UserAccount.class);
            userAccountMap.put(userAccount.getUserID(), userAccount);
            userEmailMap.put(userAccount.getEmailID(), userAccount.getUserID());
        }
        bufferedReader.close();
    }

    public void retrieveBankAccounts() throws IOException {
        String file = StoragePaths.BANK_ACCOUNTS.getStringPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        bankAccountMap.clear();

        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            BankAccount bankAccount = gson.fromJson(currentLine, BankAccount.class);
            bankAccount.flushMaps();
            bankAccountMap.put(bankAccount.getBankAccountID(), bankAccount);
        }
        bufferedReader.close();

        retrieveTransactions();
        retrieveDebitCards();
        retrieveCreditCards();
        retrievePersonalLoans();
        retrieveHomeLoans();
        retrieveVehicleLoans();
    }

    public void retrieveTransactions() throws IOException {
        String file = StoragePaths.TRANSACTIONS.getStringPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            Transaction transaction = gson.fromJson(currentLine, Transaction.class);
            getBankAccount(transaction.getBankAccountID()).addTransaction(transaction);
        }
        bufferedReader.close();
    }

    public void retrieveDebitCards() throws IOException {
        String file = StoragePaths.DEBIT_CARDS.getStringPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            DebitCard card = gson.fromJson(currentLine, DebitCard.class);
            getBankAccount(card.getBankAccountID()).addCard(card);
        }
        bufferedReader.close();
    }

    public void retrieveCreditCards() throws IOException {
        String file = StoragePaths.CREDIT_CARDS.getStringPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            CreditCard card = gson.fromJson(currentLine, CreditCard.class);
            getBankAccount(card.getBankAccountID()).addCard(card);
        }
        bufferedReader.close();
    }

    public void retrievePersonalLoans() throws IOException {
        String file = StoragePaths.PERSONAL_LOANS.getStringPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            Loan loan = gson.fromJson(currentLine, PersonalLoan.class);
            getBankAccount(loan.getBankAccountID()).addLoan(loan);
        }
        bufferedReader.close();
    }

    public void retrieveHomeLoans() throws IOException {
        String file = StoragePaths.HOME_LOANS.getStringPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            Loan loan = gson.fromJson(currentLine, HomeLoan.class);
            getBankAccount(loan.getBankAccountID()).addLoan(loan);
        }
        bufferedReader.close();
    }

    public void retrieveVehicleLoans() throws IOException {
        String file = StoragePaths.VEHICLE_LOANS.getStringPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            Loan loan = gson.fromJson(currentLine, VehicleLoan.class);
            getBankAccount(loan.getBankAccountID()).addLoan(loan);
        }
        bufferedReader.close();
    }

    public void deleteUserAccount(UUID userAccountID) {
        userAccountMap.remove(userAccountID);
    }
}
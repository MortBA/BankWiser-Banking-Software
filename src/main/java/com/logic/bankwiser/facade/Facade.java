package com.logic.bankwiser.facade;

import com.logic.bankwiser.controllers.*;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Acts as the middleman between the backend and user interface,
 * facilitates testing for the software.
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Facade {
//TODO: return types for all methods need to be updated -MH

    UserAccountController userAccountController;
    BankAccountController bankAccountController;
    TransactionController transactionController;
    CardController cardController;
    LoanController loanController;
    Storage storage;

    /**
     * Class constructor which initializes the storage and all controller classes
     */
    public Facade() {
        storage = new Storage();
        userAccountController = new UserAccountController(storage);
        bankAccountController = new BankAccountController(storage);
        transactionController = new TransactionController(storage);
        cardController = new CardController(storage, transactionController);
        loanController = new LoanController(storage);
    }

    /**
     * Attempts a user login with user details.
     *
     * @param username  the users' username
     * @param password  the users' password
     * @return String confirmation of successful login or failure
     */
    public String userLogin(String username, String password) {
        return "";
    }

    /**
     * Creates a user account based on the inputted parameters.
     *
     * @param password              the users' password which has to be smaller than 16 characters
     * @param confirmPwd            the users' password repeated for confirmation
     * @param socialSecurityNumber  the users' social security number
     * @return String confirmation of user creation or failure.
     */
    public String createUserAccount(String email, String fullName, String password, String confirmPwd,
                                    String phoneNumber, String userAddress, String socialSecurityNumber) {
        return userAccountController.createUserAccount(fullName, phoneNumber, userAddress, socialSecurityNumber, email, password);
    }

    //TODO finish deleteUserAccount method
    public String deleteUserAccount(String accountId, String username, String fullName, boolean signature) {
        return "";
    }

    /**
     * Resets the users' password.
     *
     * @param newPwd
     * @param newPwdConfirm
     * @return String confirmation of reset password.
     */
    public String resetUserPassword(String accountId, String username, String newPwd, String newPwdConfirm) {
        return "";
    }

    /**
     *
     * @param userAccount Users' UUID
     * @param accountName Name of bank account
     * @return success message
     */
    public String createBankAccount(UUID userAccount, String accountName) {
        return bankAccountController.createBankAccount(userAccount, accountName);
    }

    /**
     * Deletes a bank account
     * @param accountId
     * @return
     */
    public String deleteBankAccount(String accountId) {
        return "";
    }

    /**
     * Retrieves information about a bank account
     * @param accountName
     * @return
     */
    public HashMap<String, Double> bankAccountInformation(String accountName) {
        HashMap<String, Double> info = new HashMap<>();

        return info;
    }

    /**
     * Shows pending requests
     * @return
     */
    public ArrayList<String> pendingRequests() {
        ArrayList<String> requests = new ArrayList<>();

        return requests;
    }

    /**
     *
     * @param clerkId
     * @param username
     * @param newPwd
     * @param newPwdConfirm
     * @return
     */
    public String resetClerkPassword(String clerkId, String username, String newPwd, String newPwdConfirm) {
        return "";
    }

    /**
     *
     * @param sender
     * @param receiver
     * @param note
     * @param amount
     * @return
     */
    public String transferMoney(String sender, String receiver, String note, double amount) {
        return "";
    }

    /**
     *
     * @param bankAccountID
     * @return
     */
    public String viewTransactionHistory(String bankAccountID) {
        return transactionController.viewTransactionHistory(bankAccountID);
    }

    /**
     *
     * @param username
     * @param amount
     * @return
     */
    public String loanApplication(String username, double amount) {
        return "";
    }

    /**
     *
     * @param monthlyIncome
     * @param monthlyExpenses
     * @param liabilities
     * @param duration
     * @return
     */
    public String personalLoanApplication(double monthlyIncome, double monthlyExpenses,
                                          String liabilities, int duration) {
        return "";
    }

    /**
     *
     * @param monthlyIncome
     * @param monthlyExpenses
     * @param propertyPrice
     * @param propertySize
     * @param liabilities
     * @param homeAddress
     * @param homeType
     * @param storiesNum
     * @param duration
     * @return
     */
    public String homeLoanApplication(double monthlyIncome, double monthlyExpenses, double propertyPrice,
                                      double propertySize, String liabilities, String homeAddress,
                                      String homeType, int storiesNum, int duration) {
        return "";
    }

    /**
     *
     * @param monthlyIncome
     * @param monthlyExpenses
     * @param millage
     * @param liabilities
     * @param vehicleType
     * @param fuelType
     * @param yearOfManufacture
     * @return
     */
    public String vehicleLoanApplication(double monthlyIncome, double monthlyExpenses,
                                         double millage, String liabilities, String vehicleType,
                                         String fuelType, int yearOfManufacture) {
        return "";
    }

    /**
     *
     * @param monthlyIncome
     * @param duration
     * @return
     */
    public double calculateLoanSize(double monthlyIncome, int duration) {
        return -1;
    }

    /**
     *
     * @param loanType
     * @return
     */
    public double calculateInterestRate(String loanType) {
        return -1;
    }

    /**
     *
     * @param username
     * @param fullName
     * @param reason
     * @param loanSize
     * @return
     */
    public String loanApproval(String username, String fullName, String reason, double loanSize) {
        return "";
    }

    /**
     *
     * @return
     */
    public String createCreditCard(int pin) {
        return "";
    }

    /**
     *
     * @return
     */
    public String createDebitCard(int pin) {
        return "";
    }

    /**
     *
     * @param cardNumber
     * @return
     */
    public String freezeCard(String cardNumber) {
        return "";
    }

    /**
     *
     * @param cardNumber
     * @return
     */
    public String unfreezeCard(String cardNumber) {
        return "";
    }

    /**
     *
     * @param newLimit
     * @return
     */
    public String changeSpendingLimit(double newLimit) {
        return "";
    }

    /**
     *
     * @return
     */
    public String blockOnlineTransactions() {
        return "";
    }

    /**
     *
     * @param cardNumber
     * @param reason
     * @param pin
     * @return
     */
    public String deleteCard(String cardNumber, String reason, int pin) {
        return "";
    }

    /**
     *
     * @param cardNumber
     * @param expirationDate
     * @param CVV
     * @return
     */
    public String cardExpiration(String cardNumber, String expirationDate, int CVV) {
        return "";
    }

    /**
     *
     * @param customerName
     * @param username
     * @return
     */
    public String creditCardApproval(String customerName, String username) {
        return "";
    }

    /**
     *
     * @param cardNumber
     * @param username
     * @param oldPin
     * @param newPin
     * @param newPinConfirmation
     * @return
     */
    public String changePin(String cardNumber, String username, int oldPin, int newPin, int newPinConfirmation) {
        return "";
    }

    /**
     *
     * @param accountName
     * @param amount
     * @return
     */
    public String depositMoney(String accountName, double amount) {
        return "";
    }

    /**
     *
     * @param userName
     * @param bankAccount
     * @return
     */
    public ArrayList<String> getAccountInfo(String userName, String bankAccount) {
        ArrayList<String> history = new ArrayList<String>();

        return history;
    }

    /**
     *
     * @param userName
     * @return
     */
    public HashMap<String, Double> getUserAccountsInfo(String userName) {
        HashMap<String, Double> info = new HashMap<String, Double>();

        return info;
    }
}

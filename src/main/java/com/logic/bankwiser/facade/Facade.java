package com.logic.bankwiser.facade;

import com.logic.bankwiser.transactions.Transaction;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Acts as the middleman between the backend and user interface,
 * facilitates testing for the software.
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Facade {
//TODO: return types for all methods need to be updated -MH

    /**
     * Class constructor
     */
    public Facade() {

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
    public String createUserAccount(String userName, String fullName, String password, String confirmPwd,
                                    String phoneNumber, String userAddress, String socialSecurityNumber) {
        return "";
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

    public String createBankAccount(String accountId) {
        return "";
    }

    public String deleteBankAccount(String accountId) {
        return "";
    }

    public HashMap<String, Double> bankAccountInformation(String accountName) {
        HashMap<String, Double> info = new HashMap<>();

        return info;
    }

    public ArrayList<String> pendingRequests() {
        ArrayList<String> requests = new ArrayList<>();

        return requests;
    }

    public String resetClerkPassword(String clerkId, String username, String newPwd, String newPwdConfirm) {
        return "";
    }

    public String transferMoney(String sender, String receiver, String note, Double amount) {
        return "";
    }

    public ArrayList<Transaction> viewTransactionHistory(String accountName) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        return transactions;
    }

    public String loanApplication(String username, double amount) {
        return "";
    }

    public String personalLoanApplication(double monthlyIncome, double monthlyExpenses,
                                          String liabilities, int duration) {
        return "";
    }

    public String homeLoanApplication(double monthlyIncome, double monthlyExponses, double propertyPrice,
                                      double propertySize, String liabilities, String homeAddress,
                                      String homeType, int storiesNum, int duration) {
        return "";
    }

    public String vehicleLoanApplication(double monthlyIncome, double monthlyExpenses,
                                         double millage, String liabilities, String vehicleType,
                                         String fuelType, int yearOfManufacture) {
        return "";
    }

    public double calculateLoanSize(double monthlyIncome, int duration) {
        return -1;
    }

    public double calculateInterestRate(String loanType) {
        return -1;
    }

    public String loanApproval(String username, String fullName, String reason, double loanSize) {
        return "";
    }

    public String setPinCode(int pin, int pinConfirmation) {
        return "";
    }

    public String freezeCard(String cardNumber) {
        return "";
    }

    public String unfreezeCard(String cardNumber) {
        return "";
    }

    public String changeSpendingLimit(double oldLimit, double newLimit) {
        return "";
    }

    public String blockOnlineTransactions() {
        return "";
    }

    public String deleteCard(String cardNumber, String reason, int pin) {
        return "";
    }

    public String cardExpiration(String cardNumber, String expirationDate, int CVV) {
        return "";
    }

    public String creditCardApproval(String customerName, String username) {
        return "";
    }

    public String changePin(String cardNumber, String username, int oldPin, int newPin, int newPinConfirmation) {
        return "";
    }
}

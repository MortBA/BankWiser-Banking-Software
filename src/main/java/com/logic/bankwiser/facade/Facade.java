package com.logic.bankwiser.facade;

import java.util.HashMap;
import java.util.ArrayList;

public class Facade {
    public String userLogin(String username, String password) {
        return "";
    }

    public String createUserAccount(String username, String fullName, String password, String confirmPwd,
                                    String phoneNumber, String userAddress, String socialSecurityNumber) {
        return "";
    }

    public String deleteUserAccount(String accountId, String username, String fullName, boolean signature) {
        return "";
    }

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
        HashMap<String, Double> info = new HashMap<String, Double>();

        return info;
    }

    public ArrayList<String> pendingRequests() {
        ArrayList<String> requests = new ArrayList<String>();

        return requests;
    }

    public String resetClerkPassword(String clerkId, String username, String newPwd, String newPwdConfirm) {
        return "";
    }

    public String transferMoney(String sender, String receiver, String note, Double amount) {
        return "";
    }

    public ArrayList<String[]> viewTransactionHistory(String accountName) {
        ArrayList<String[]> history = new ArrayList<String[]>();

        return history;
    }

    public String loanApplication(String username, double amount) {
        return "";
    }

    public String personalLoanApplication(double monthlyIncome, double monthlyExpenses,
                                          String liabilities, int duration) {
        return "";
    }

    public String homeLoanApplication(double monthlyIncome, double montlyExponses, double propertyPrice,
                                      double propertySize, String liabilities, String homeAddress,
                                      String homeType, int storiesNum, int duration) {
        return "";
    }

    public String vehicleLoanApplication(double monthlyIncome, double monthlyExpenses,
                                         double millage, String liabilities, String vehicleType,
                                         String fuelType, int yearOfManifacture) {
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

package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.controllers.*;
import com.logic.bankwiser.storage.Storage;

import java.io.IOException;
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

    protected Storage storage;
    private final UserAccountController userAccountController;
    private final BankAccountController bankAccountController;
    private final TransactionController transactionController;
    private final CardController cardController;
    private final LoanController loanController;

    private static Facade facade_instance = null;
    UserAccount activeUser;
    UUID activeUserID;
    BankAccount activeBankAccount;


    /**
     * Class constructor which initializes the storage and all controller classes
     * The Facade class follows the Singleton pattern to make sure only one instance of Facade is ever created by the app.
     * Normally, an implementation of the Singleton pattern would mean that the constructor is private.
     * However, the facade constructor is public because tests still need to be able to create new instances of Facade.
     * On the other hand, neither the backend nor the frontend will ever call the Facade constructor directly.
     */
    public Facade() {
        storage = new Storage();
        userAccountController = new UserAccountController(storage);
        bankAccountController = new BankAccountController(storage);
        transactionController = new TransactionController(storage);
        cardController = new CardController(storage, transactionController);
        loanController = new LoanController(storage, transactionController);
    }

    /**
     * This method is crucial to how the Singleton pattern works.
     * When the frontend wants to communicate with the backend, it will go through the Facade by calling this method.
     * This ensures that the same instance of Facade, and by extension Storage, is always used.
     * In return, data loss is minimised and the application can run as expected.
     * @return The one and only instance of Facade that is ever used when operating the application.
     */
    public static Facade getInstance() {
        if (facade_instance == null) {
            facade_instance = new Facade();
        }
        return facade_instance;
    }

    public void storeAll() throws IOException {
        storage.storeAll();
    }

    /**
     * Attempts a user login with user details.
     *
     * @param username the users' username
     * @param password the users' password
     * @return String confirmation of successful login or failure
     */
    public String userLogin(String username, String password) {
        this.activeUserID = storage.getUserUUID(username);
        this.activeUser = storage.getUserFromMap(storage.getUserUUID(username));
        return "";
    }

    public String checkPayments(){
        StringBuilder userPaymentsString = new StringBuilder();

        userPaymentsString.append(cardController.annualCardPayment(activeUser));
        userPaymentsString.append(cardController.creditCardPayment(activeUser));
        userPaymentsString.append(loanController.loanRepayment(activeUser));

        return userPaymentsString.toString();
    }

    /**
     * Creates a user account based on the inputted parameters.
     *
     * @param password             the users' password which has to be smaller than 16 characters
     * @param confirmPwd           the users' password repeated for confirmation
     * @param socialSecurityNumber the users' social security number
     * @return String confirmation of user creation or failure.
     */
    public String createUserAccount(String userName, String fullName, String password, String confirmPwd,
                                    String phoneNumber, String userAddress, String socialSecurityNumber) {
        return userAccountController.createUserAccount(fullName, phoneNumber, userAddress, socialSecurityNumber, userName, password);
    }

    //TODO finish deleteUserAccount method

    /**
     * Deletes the user account based on the given id
     *
     * @param accountId     The id of the user account
     * @param username      The username of the user
     * @param fullName      The full name of the user
     * @param signature     The signature of the user
     * @return String confirmation of user deletion or failure
     */
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
    //TODO finish reset resetUserPassword method
    public String resetUserPassword(String accountId, String username, String newPwd, String newPwdConfirm) {
        return "";
    }

    /**
     * Creates the bank account
     *
     * @param userAccount     Users' UUID
     * @param accountName     Name of bank account
     * @return success message
     */
    public String createBankAccount(UUID userAccount, String accountName) {
        return bankAccountController.createBankAccount(userAccount, accountName);
    }

    public void selectedBankAccount(BankAccount activeBankAccount){
        this.activeBankAccount = activeBankAccount;
    }

    /**
     * Deletes the bank account
     *
     * Deletes a bank account
     * @param accountId     The id of the account that will be removed
     * @return String confirmation of deleting the bank account.
     */
    public String deleteBankAccount(String accountId) {
        return "";
    }

    /**
     * Retrieves information about a bank account
     *
     * @param accountName     the name of the bank account
     * @return
     */
    public String bankAccountInformation(String accountName) {
        return activeBankAccount.toString();
    }

    /**
     * Resets the password of the clerk with a chosen password
     *
     * @param clerkId           the ID of the clerk
     * @param username          the username of the clerk
     * @param newPwd            the new password
     * @param newPwdConfirm     the repeat of the new password
     * @return String confirmation of resetting the clerk password.
     */
    public String resetClerkPassword(String clerkId, String username, String newPwd, String newPwdConfirm) {
        return "";
    }

    /**
     * Transferring the money from one account to another
     *
     * @param sender       the account of the sender
     * @param receiver     the account of the receiver
     * @param note         a small message attached to the transaction
     * @param amount       total amount/price of transaction
     * @return String confirmation of deleting the bank account.
     */
    public String transferMoney(String sender, String receiver, String note, double amount) {
        return "";
    }

    /**
     * Shows history of transactions of a bank-account
     *
     * @param bankAccountID     the account that the history belongs to
     * @return String containing the history
     */
    public String viewTransactionHistory(String bankAccountID) {
        return transactionController.viewTransactionHistory(bankAccountID);
    }

    /**
     * Sends an application for a loan to be processed
     *
     * @param username     the username of the applicant
     * @param amount       the amount of the requested
     * @return confirmation of loan acceptation or rejection.
     */
    public String loanApplication(String username, double amount) {
        return "";
    }

    /**
     * Sends an application for a personal-loan to be processed
     *
     * @param monthlyIncome       total income on a monthly basis of the user
     * @param monthlyExpenses     total expenses on a monthly basis of the user
     * @param desiredLoanAmount   total loan amount
     * @param duration            the length of time for repayment
     * @param note                Personal note
     * @return confirmation of loan acceptation or rejection.
     */
    public String personalLoanApplication(double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, int duration, String note) {
        return loanController.personalLoanApplication(activeUser, activeUser.getBankAccountList().get(0), monthlyIncome, monthlyExpenses, desiredLoanAmount, duration, note);
    }

    /**
     * Sends an application for a home-loan to be processed
     *
     * @param monthlyIncome       total income on a monthly basis of the user
     * @param monthlyExpenses     total expenses on a monthly basis of the user
     * @param propertyPrice       the total price of the property
     * @param propertySize        the size of the total property
     * @param liabilities         the total liabilities
     * @param homeAddress         the address of the lodging
     * @param homeType            the type of lodging
     * @param storiesNum          //the number of the flat?
     * @param duration            the length of time for repayment
     * @return confirmation of loan acceptation or rejection.
     */
    public String homeLoanApplication(double monthlyIncome, double monthlyExpenses, double propertyPrice,
                                      double propertySize, String liabilities, String homeAddress,
                                      String homeType, int storiesNum, int duration) {
        return "";
    }

    /**
     * Sends an application for a vehicle-loan to be processed
     *
     * @param monthlyIncome       total income on a monthly basis of the user
     * @param monthlyExpenses     total expenses on a monthly basis of the user
     * @param millage             the age of the car by miles
     * @param liabilities         the total liabilities
     * @param vehicleType         the type of the vehicle
     * @param fuelType            the type of fuel on the car
     * @param yearOfManufacture
     * @return confirmation of loan acceptation or rejection.
     */
    public String vehicleLoanApplication(double monthlyIncome, double monthlyExpenses,
                                         double millage, String liabilities, String vehicleType,
                                         String fuelType, int yearOfManufacture) {
        return "";
    }

    /**
     * Calculates the loan size
     *
     * @param monthlyIncome     total income on a monthly basis of the user
     * @param duration          the duration of the loan
     * @return the size of the calculated loan
     */
    public double calculateLoanSize(double monthlyIncome, int duration) {
        return -1;
    }

    /**
     * Calculates the interest rate
     *
     * @param loanType     the type of the loan
     * @return  double containing interest rate depending on the loan type
     */
    public double calculateInterestRate(String loanType) {
        return -1;
    }

    /**
     * Gives either approval or disapproval of a loan
     *
     * @param username     the user that requests the loan
     * @param fullName     the fullname of the user
     * @param reason       the reasoning for the loan request
     * @param loanSize     the total amount of the loan
     * @return string containing approval or disapproval of the loan
     */
    public String loanApproval(String username, String fullName, String reason, double loanSize) {
        return "";
    }

    /**
     * Creates the credit card
     * @param pin input give in by the user as their card code
     * @return string with approval or disapproval for the card creation.
     */
    public String createCreditCard(int pin) {
        return cardController.addCard(activeBankAccount, pin);
    }

    /**
     * Creates the debit card
     * @param pin input given in by the user as their card code
     * @return string with approval or disapproval for the card creation.
     */
    public String createDebitCard(int pin) {
        return cardController.addCard(activeBankAccount,pin);
    }



    /**
     * Toggle the card to freeze
     *
     * @param cardNumber     the card to be frozen
     * @return
     */
    public String freezeCard(String cardNumber) {
        return cardController.modifyStatus(activeBankAccount, cardNumber);
    }

    /**
     * Toggle the card to unfreeze
     *
     * @param cardNumber     the card to be unfrozen
     * @return
     */
    public String unfreezeCard(String cardNumber) {
        return cardController.modifyStatus(activeBankAccount,cardNumber);
    }

    /**
     * Modify the instore spending limit
     *
     * @param newLimit     the new limit as chosen by the user
     * @return String confirmation of success or failure
     */
    public String changeSpendingLimit(String cardNumber, double newLimit) {
        return cardController.modifyExpenditureMax(activeBankAccount, cardNumber, newLimit);
    }

    public String allowOnlineTransactions() {
        return "";
    }

    /**
     * Modify the online transaction ability of a card
     *
     * @param cardNumber     the number of the card that will be modified
     * @return String confirmation of success or failure
     */
    public String blockOnlineTransactions(String cardNumber) {
        return cardController.modifyOnlineStatus(activeBankAccount, cardNumber);
    }

    /**
     * Delete the card of given card-number
     *
     * @param cardNumber     the number of the card that will be modified
     * @param reason         the reasoning to the deletion of the card
     * @param pin            the pin of the card
     * @return String confirmation of success or failure
     */
    public String deleteCard(String cardNumber, String reason, int pin) {
        return cardController.deleteCard(activeBankAccount, cardNumber, pin, reason);
    }

    /**
     * Reminds the user of card expiration
     *
     * @param cardNumber         the number of the card that will be modified
     * @return String with the reminder message
     */
    public String cardExpiration(String cardNumber) {
        return cardController.remainderDays(activeBankAccount, cardNumber, activeUser);
    }



    /**
     * Change the pin of the card
     *
     * @param cardNumber             the number of the card that will be modified
     * @param oldPin                 the current/old pin that the user wants to change
     * @param newPin                 the new pin that the user wants to change their current pin into
     * @param newPinConfirmation     the repeat of the new pin
     * @return String confirmation of success or failure
     */
    public String changePin(String cardNumber, int oldPin, int newPin, int newPinConfirmation) {
        return cardController.resetPin(activeBankAccount, cardNumber, oldPin, newPin, newPinConfirmation);
    }

    /**
     * Deposit the money into an account
     *
     * @param accountName     name of the account that will receive the deposit
     * @param amount          the amount that will deposit
     * @return String confirmation of success or failure
     */
    public String depositMoney(String accountName, double amount) {
        return "";
    }
}

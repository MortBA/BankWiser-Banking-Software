package com.logic.bankwiser.facade;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.controllers.*;
import com.logic.bankwiser.loans.Loan;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.utils.Input;
import javafx.util.Pair;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

/**
 * Acts as the middleman between the backend and user interface,
 * facilitates testing for the software.
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Facade {

    protected Storage storage;
    private final UserAccountController userAccountController;
    private final BankAccountController bankAccountController;
    private final TransactionController transactionController;
    private final CardController cardController;
    private final LoanController loanController;

    private static Facade facade_instance = null;
    UserAccount activeUser;
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

    public Facade(boolean tests) {
        storage = new Storage(tests);
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
     *
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
        Pair<UserAccount, String> loginRequest = userAccountController.loginUser(username, password);
        if (loginRequest.getKey() != null) {
            this.activeUser = loginRequest.getKey();
            if (!activeUser.getBankAccountList().isEmpty()) {
                this.activeBankAccount = storage.getBankAccount(activeUser.getBankAccountList().get(0));
            }
        }
        return loginRequest.getValue();
    }

    public UserAccount getActiveUser() {
        return activeUser;
    }

    /**
     * A method checks payments on current user in regards to existing credit cards and loans.
     *
     * @return a String
     */

    public String checkPayments() {
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
     * @param confirmPassword      the users' password repeated for confirmation
     * @param socialSecurityNumber the users' social security number
     * @return String confirmation of user creation or failure.
     */
    public String createUserAccount(String userName, String fullName, String password, String confirmPassword,
                                    String phoneNumber, String userAddress, String socialSecurityNumber) {
        return userAccountController.createUserAccount(fullName, phoneNumber, userAddress, socialSecurityNumber, userName, password, confirmPassword);
    }

    /**
     * Deletes the user account based on the given username
     *
     * @param username The username of the user
     * @return String confirmation of user deletion or failure
     */
    public String deleteUserAccount(String username) {
        Pair<UserAccount, String> request = userAccountController.processDeleteUserAccountRequest(storage.getUserFromMap(username));

        if (request.getKey() != null) {
            //storage.addDeleteUserRequest(request.getKey());
        }
        return request.getValue();
    }

    /**
     * Resets the users' password.
     *
     * @return String confirmation of reset password.
     */
    public String resetUserPassword(String accountId) {
        return userAccountController.resetPassword(accountId);
    }

    /**
     * Creates the bank account
     *
     * @param accountName Name of bank account
     * @return success message
     */
    public String createBankAccount(String accountName) {
        return bankAccountController.createBankAccount(activeUser, accountName);
    }

    /**
     * @param accountName
     * @return
     */
    public String createBankAccount(UUID userAccountID, String accountName) {
        return bankAccountController.createBankAccount(storage.getUserFromMap(userAccountID), accountName);
    }

    /**
     * @return currently selected BankAccount
     */
    public BankAccount getActiveBankAccount() {
        return activeBankAccount;
    }

    /**
     * @return List of users' bank accounts
     */
    public List<String> getBankAccounts() {
        return activeUser.getBankAccountList();
    }

    /**
     * Changes the active bank account to input
     */
    public void selectedBankAccount(String bankAccountID) {
        this.activeBankAccount = storage.getBankAccount(bankAccountID);
    }


    public String renameBankAccount(String bankAccountID, String newName) {
        return bankAccountController.renameBankAccount(activeUser, bankAccountID, newName);
    }

    /**
     * Deletes the bank account.
     *
     * @param accountId The id of the account that will be removed
     * @return String confirmation of deleting the bank account.
     */
    public String deleteBankAccount(String accountId) {
        return bankAccountController.deleteBankAccount(activeUser, accountId);
    }

    /**
     * Transferring the money from one account to another.
     *
     * @param sender   the account of the sender
     * @param receiver the account of the receiver
     * @param note     a small message attached to the transaction
     * @param amount   total amount/price of transaction
     * @return String confirmation on success or fail.
     */
    public String transferMoney(String sender, String receiver, String note, double amount) {
        return transactionController.transferMoney(sender, receiver, BigDecimal.valueOf(amount), note, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    /**
     * Shows history of transactions of a bank-account.
     *
     * @param bankAccountID the account that the history belongs to
     * @return String containing the history
     */
    public String viewTransactionHistory(String bankAccountID) {
        return transactionController.viewTransactionHistory(bankAccountID);
    }

    /**
     * Sends an application for a personal-loan to be processed.
     *
     * @param monthlyIncome     total income on a monthly basis of the user
     * @param monthlyExpenses   total expenses on a monthly basis of the user
     * @param desiredLoanAmount total loan amount
     * @param duration          the length of time for repayment
     * @param note              Personal note
     * @return confirmation of loan acceptation or rejection.
     */
    public String personalLoanApplication(double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, int duration, String note) {
        return loanController.personalLoanApplication(activeUser, activeBankAccount.getBankAccountID(), monthlyIncome, monthlyExpenses, desiredLoanAmount, duration, note);
    }

    /**
     * Sends an application for a home-loan to be processed.
     *
     * @param monthlyIncome   total income on a monthly basis of the user
     * @param monthlyExpenses total expenses on a monthly basis of the user
     * @param propertySize    the size of the total property
     * @param propertyAddress the address of the lodging
     * @param propertyType    the type of lodging
     * @param propertyFloor   //the number of the flat?
     * @param loanDuration    the length of time for repayment in months
     * @return confirmation of loan acceptation or rejection.
     */
    public String homeLoanApplication(double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, int loanDuration,
                                      double propertySize, String propertyAddress, String propertyType, int propertyFloor) {
        return loanController.homeLoanApplication(activeUser, activeBankAccount.getBankAccountID(), monthlyIncome, monthlyExpenses, desiredLoanAmount, loanDuration, propertyAddress, propertyType, propertySize, propertyFloor);
    }

    /**
     * Sends an application for a vehicle-loan to be processed.
     *
     * @param monthlyIncome     total income on a monthly basis of the user
     * @param monthlyExpenses   total expenses on a monthly basis of the user
     * @param mileage           the age of the car by miles
     * @param vehicleType       the type of the vehicle
     * @param fuelType          the type of fuel on the car
     * @param yearOfManufacture the year the car was built
     * @param desiredLoanAmount the desired loan amount
     * @param loanDuration      the duration of the loan in months
     * @return confirmation of loan acceptation or rejection.
     */
    public String vehicleLoanApplication(double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, int loanDuration,
                                         double mileage, String vehicleType, String fuelType, int yearOfManufacture) {
        return loanController.vehicleLoanApplication(activeUser, activeBankAccount.getBankAccountID(), monthlyIncome, monthlyExpenses, desiredLoanAmount, loanDuration, vehicleType, fuelType, mileage, yearOfManufacture);
    }

    public String loanOverview() {

        StringBuilder sb = new StringBuilder();

        for(Loan loans : activeBankAccount.getLoanMap().values()) {

            sb.append(loans.toString()).append(Input.EOL);

        }

        return sb.toString();
    }


    /**
     * Creates the credit card
     *
     * @param pin input give in by the user as their card code
     * @return string with approval or disapproval for the card creation.
     */
    public String createCreditCard(int pin, int pinConfirmation, double monthlyIncome, double monthlyExpenses) {
        return cardController.addCard(activeBankAccount, pin, pinConfirmation, monthlyIncome, monthlyExpenses);
    }

    /**
     * Creates the debit card
     *
     * @param pin input given in by the user as their card code
     * @return string with approval or disapproval for the card creation.
     */
    public String createDebitCard(int pin, int pinConfirmation) {
        return cardController.addCard(activeBankAccount, pin, pinConfirmation);
    }

    /**
     * Creates the debit card
     *
     * @param pin input given in by the user as their card code
     * @return string with approval or disapproval for the card creation.
     */
    public String createDebitCard(String cardNumber, int pin, int pinConfirmation) {
        return cardController.addCard(activeBankAccount, cardNumber, pin, pinConfirmation);
    }


    /**
     * Toggle the card to unfreeze
     *
     * @param cardNumber the card to be toggled
     * @return String confirmation of success or failure
     */
    public String toggleFreezeCard(String cardNumber) {
        return cardController.modifyStatus(activeBankAccount, cardNumber);
    }

    public String modifyRegion(String cardNumber, String region) {
        return cardController.modifyRegion(activeBankAccount, cardNumber, region);
    }

    /**
     * Modify the spending limit of a card.
     *
     * @param newLimit the new limit as chosen by the user
     * @return String confirmation of success or failure
     */
    public String changeSpendingLimit(String cardNumber, double newLimit) {
        return cardController.modifyExpenditureMax(activeBankAccount, cardNumber, newLimit);
    }

    /**
     * Toggle online transactions
     *
     * @return String confirmation of success or failure
     */
    public String toggleOnlineTransactions(String cardNumber) {
        return cardController.modifyOnlineStatus(activeBankAccount, cardNumber);
    }

    /**
     * Delete the card of given card-number
     *
     * @param cardNumber the number of the card that will be modified
     * @param reason     the reasoning to the deletion of the card
     * @param pin        the pin of the card
     * @return String confirmation of success or failure
     */
    public String deleteCard(String cardNumber, String reason, int pin) {
        return cardController.deleteCard(activeBankAccount, cardNumber, pin, reason);
    }

    /**
     * Reminds the user of card expiration
     *
     * @param cardNumber the number of the card that will be modified
     * @return String with the reminder message
     */
    public String cardExpiration(String cardNumber) {
        return cardController.remainderDays(activeBankAccount, cardNumber, activeUser);
    }


    /**
     * Change the pin of the card
     *
     * @param cardNumber         the number of the card that will be modified
     * @param oldPin             the current/old pin that the user wants to change
     * @param newPin             the new pin that the user wants to change their current pin into
     * @param newPinConfirmation the repeat of the new pin
     * @return String confirmation of success or failure
     */
    public String changePin(String cardNumber, int oldPin, int newPin, int newPinConfirmation) {
        return cardController.resetPin(activeBankAccount, cardNumber, oldPin, newPin, newPinConfirmation);
    }


}

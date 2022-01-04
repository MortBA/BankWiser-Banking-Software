package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.loans.Loan;
import com.logic.bankwiser.storage.Storage;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author Kevin Collins
 */
public class UserAccountController {

    private final Storage storage;

    public UserAccountController(Storage storage) {
        this.storage = storage;
    }

    /**
     * The method creates and stores a user account with the relevant information while handling exceptions if needed.
     *
     * @param fullName
     * @param phoneNumber
     * @param address
     * @param socialSecurityNum
     * @param emailID
     * @param password
     * @return A string that confirms successful user account creation or specifies the relevant input error.
     */
    public String createUserAccount(String fullName, String phoneNumber, String address,
                                    String socialSecurityNum, String emailID, String password) {
        StringBuilder sb = new StringBuilder();
        UUID userID = UUID.randomUUID();

        try {
            storage.addUserAccount(userID, new UserAccount(userID, fullName, phoneNumber, address, socialSecurityNum, emailID, password));
            sb.append("New account for ").append(emailID).append(" was successfully created.");
        } catch (Exception e) {
            System.out.println(e);
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    // TODO Active user implementation needs to be done in facade -KC
    public String loginUser(String email, String password) {
        StringBuilder sb = new StringBuilder();
        for (UserAccount userAccount : storage.getUserAccountMap().values()) {
            if (email == userAccount.getEmailID()){
                if (password == userAccount.getPassword()) {
                    sb.append("Successfully logged in");
                } else {
                    sb.append("Password is incorrect.");
                }
            } else {
                sb.append("No account is registered with that email address.");
            }
        }
        return sb.toString();
    }

    public String processDeleteUserAccountRequest(UserAccount user) {
        StringBuilder sb = new StringBuilder();
        while (sb.isEmpty()){
            for (String bankAccountID : user.getBankAccountList()) {
                BankAccount bankAccount = storage.getBankAccount(bankAccountID);
                if (bankAccount.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                    sb.append("Please transfer money from all bank accounts first.");
                } else if (bankAccount.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                    sb.append("Please pay off your credit card first.");
                } else if (!bankAccount.getLoanMap().isEmpty()) {
                    sb.append("Please speak to the clerk about how loans can be transferred first.");
                }
            }
        }
        if (sb.isEmpty()) {
            sb.append("User Account deletion request has been sent.");
        }
        return sb.toString();
    }

    public String deleteUserAccount(String email) {
        StringBuilder sb = new StringBuilder();
        for (UserAccount userAccount : storage.getUserAccountMap().values()) {
            if (email == userAccount.getEmailID()) {
                storage.deleteUserAccount(userAccount.getUserID());
                sb.append("User account has been deleted");
            } else {
                sb.append("User Account not found.");
            }
        }
        return sb.toString();
    }

}
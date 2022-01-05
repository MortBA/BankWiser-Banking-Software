package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.storage.Storage;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Controller responsible for all user accounts.
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
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    public Pair<UserAccount, String> loginUser(String email, String password) {
        if (storage.getUserFromMap(email) != null) {
            if (Objects.equals(password, storage.getUserFromMap(email).getPassword())) {
                return new Pair<>(storage.getUserFromMap(email), "Successfully logged in");
            } else {
                return new Pair<>(null,"Password is incorrect.");
            }
        } else {
            return new Pair<>(null,"No account is registered with that email address.");
        }
    }

    public Pair<UserAccount, String> processDeleteUserAccountRequest(UserAccount userAccount) {
        StringBuilder sb = new StringBuilder();
        while (sb.isEmpty()) {
            for (String bankAccountID : userAccount.getBankAccountList()) {
                BankAccount bankAccount = storage.getBankAccount(bankAccountID);
                if (bankAccount.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                    sb.append("Please transfer money from all bank accounts first.");
                } else if (bankAccount.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                    sb.append("Please pay off your credit card first.");
                }
                if (!bankAccount.getLoanMap().isEmpty()) {
                    sb.append("Please speak to the clerk about how loans can be transferred first.");
                }
            }
        }
        if (sb.isEmpty()) {
            sb.append("User Account deletion request has been sent.");
        }
        return new Pair<>(userAccount, sb.toString());
    }

    public String resetPassword(String emailID) {
        return "An email has been sent to " + emailID + " with a link that will allow you to reset your password.";
    }

}
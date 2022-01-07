package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.storage.Storage;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

/**
 * Controller responsible for user accounts.
 *
 * @author Kevin Collins
 * @author Mathias Hallander
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
                                    String socialSecurityNum, String emailID, String password, String confirmPassword) {
        StringBuilder sb = new StringBuilder();
        UUID userID = UUID.randomUUID();
        HashSet<UserAccount> userAccountHashSet = new HashSet<>(storage.getUserAccountMap().values());

        try {
            if (Objects.equals(password, confirmPassword)) {
                if (userAccountHashSet.add(new UserAccount(userID, fullName, phoneNumber, address, socialSecurityNum, emailID, password))) {
                    storage.addUserAccount(userID, new UserAccount(userID, fullName, phoneNumber, address, socialSecurityNum, emailID, password));
                    sb.append("New account for ").append(emailID).append(" was successfully created.");
                } else {
                    sb.append("A user with these details already exists.");
                }
            } else {
                sb.append("The passwords don't match.");
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    /**
     * Facilitates user login from facade.
     * Accepts user parameters and returns that information paired.
     *
     * @return Pair&#60;UserAccount, String&#62; where userAccount is null on a fail, and String always returns a success or fail response.
     */
    public Pair<UserAccount, String> loginUser(String email, String password) {
        if (storage.getUserFromMap(email) != null) {
            if (Objects.equals(password, storage.getUserFromMap(email).getPassword())) {
                return new Pair<>(storage.getUserFromMap(email), "Successfully logged in.");
            } else {
                return new Pair<>(null, "Password is incorrect.");
            }
        } else {
            return new Pair<>(null, "No account is registered with that email address.");
        }
    }

    /**
     * Checks the provided user account to verify if it is allowed to be deleted or not.
     * There must be no money on any user bank account, and no liabilities (such as credit or loans) either
     *
     * @return Pair&#60;UserAccount, String&#62; where userAccount is null on a fail, and String always returns a success or fail response.
     */
    public Pair<UserAccount, String> processDeleteUserAccountRequest(UserAccount userAccount) {
        StringBuilder sb = new StringBuilder();
        if (userAccount != null) {
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
            if (sb.isEmpty()) {
                sb.append("User account deletion request has been sent.");
                return new Pair<>(userAccount, sb.toString());
            }
        } else {
            sb.append("A user with that email does not exist.");
        }
        return new Pair<>(null, sb.toString());
    }

    /**
     * The method acts as an abstraction for how a password might be reset.
     *
     * @return A string that either confirms that reset password link has been to the inputted email or
     * states that the email is not registered.
     */
    public String resetPassword(String emailID) {
        if (storage.getUserFromMap(emailID) != null) {
            return "An email has been sent to " + emailID + " with a link that will allow you to reset your password.";
        } else {
            return "A user with that email does not exist.";
        }
    }
}
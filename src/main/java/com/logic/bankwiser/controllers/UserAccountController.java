package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.storage.Storage;

import java.util.UUID;

/**
 * @author Kevin Collins
 */

public class UserAccountController {

    private final Storage storage;

    public UserAccountController(Storage storage) {
        this.storage = storage;
    }

    /**
     * The method creates and stores a user account with the relevant information while handling exceptions if needed.
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
            sb.append("New account for " + fullName + " was successfully created.");
        } catch (Exception e) {
            System.out.println(e);
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

}

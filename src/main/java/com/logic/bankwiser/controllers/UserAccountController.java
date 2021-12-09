package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.storage.Storage;

import java.util.Random;
import java.util.UUID;

public class UserAccountController {

    private final Storage storage;

    public UserAccountController(Storage storage) {
        this.storage = storage;
    }

    public void createUserAccount(String fullName, String phoneNumber, String address,
                                  int socialSecurityNum, String emailID, String password) {
        UUID userID = UUID.randomUUID();
        try {
            storage.addUserAccount(userID, new UserAccount(userID, fullName, phoneNumber, address, socialSecurityNum, emailID, password));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

package com.logic.bankwiser.accounts;

import java.util.HashMap;
import java.util.UUID;

public class ClerkAccount {

    private String fullName;
    private final String EMAIL_ID;
    private String password;
    private final HashMap<UUID, UserAccount> usersToBeDeleted;

    public ClerkAccount(String fullName, String emailID, String password) {
        this.fullName = fullName;
        this.EMAIL_ID = emailID;
        this.password = password;
        usersToBeDeleted = new HashMap<>();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailID() {
        return EMAIL_ID;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<UUID, UserAccount> getUsersToBeDeleted() {
        return usersToBeDeleted;
    }
}

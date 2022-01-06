package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.storage.Storage;

/**
 * Controller class responsible for clerk accounts.
 *
 * @author Kevin Collins
 */
public class ClerkController {

    private final Storage storage;

    public ClerkController(Storage storage) {
        this.storage = storage;
    }

    public String reportError(String errorReport) {
        storage.addErrorReport(errorReport);
        return "Thank you for reporting this error! Your error report has been sent to the development team.";
    }

    public String deleteUserAccount(UserAccount userAccount) {
        StringBuilder sb = new StringBuilder();
        storage.deleteUserAccount(userAccount.getUserID());
        return sb.toString();
    }
}

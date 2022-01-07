package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.storage.Storage;

/**
 * Controller responsible for the clerk account
 *
 * @author Kevin Collins
 */
public class ClerkController {

    private final Storage storage;

    public ClerkController(Storage storage) {
        this.storage = storage;
    }

    /**
     * This method stores the error report so that the development team can consider it.
     * @param errorReport   A message that a clerk would type to submit
     *
     * @return
     */
    public String reportError(String errorReport) {
        storage.addErrorReport(errorReport);
        return "Thank you for reporting this error! Your error report has been sent to the development team.";
    }

    public void deleteUserAccount(UserAccount userAccount) {
        storage.deleteUserAccount(userAccount.getUserID());
    }
}

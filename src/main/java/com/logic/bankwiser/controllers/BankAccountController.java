package com.logic.bankwiser.controllers;

import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.utils.MathUtils;

import java.util.UUID;

/**
 * @author Kevin Collins
 * @author Mathias Hallander
 */

public class BankAccountController {

    private final Storage storage;

    public BankAccountController(Storage storage) {
        this.storage = storage;
    }

    /**
     * The method handles bank account creation and links it to the appropriate user account.
     *
     * @return A string that confirms bank account creation or an input error that
     * informs the user of an invalid bank account name.
     */
    public String createBankAccount(UUID UserID, String bankAccountName) {
        StringBuilder sb = new StringBuilder();
        try {
            String bankAccountID = generateBankAccountID();
            BankAccount bankAccount = new BankAccount(bankAccountID, bankAccountName);
            storage.addBankAccount(bankAccountID, bankAccount);
            storage.getUserFromMap(UserID).addBankAccount(bankAccount.getBankAccountID());
            sb.append("New banking account ").append(bankAccountName).append(" has been created.");
        } catch (Exception e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    //TODO return strings not specified in requirements

    /**
     * The method allows the user to rename the selected bank account.
     *
     * @return A string that either confirms the bank account being renamed or
     * informs the user that the bank account could not be renamed.
     */
    public String renameBankAccount(String bankAccountID, String bankAccountName) {
        StringBuilder sb = new StringBuilder();
        if (bankAccountName.length() <= 30) {
            storage.getBankAccount(bankAccountID).setBankAccountName(bankAccountName);
            sb.append("Your bank account has been renamed to ").append(bankAccountName).append(".");
        } else {
            sb.append("Your bank account cannot be renamed as the new name is ").append(bankAccountName.length()).append(" characters long.");
        }
        return sb.toString();
    }

    //TODO Implement after UserAccount link is functional -K
    public void deleteBankAccount() {
        /*
        The user has permission to delete any bank account they own, provided
        it is not their only bank account left, and the bank account is empty.

        Should the user attempt to remove an account which does not fulfil this
        criteria, the system will inform the user that the action was not performed.
         */
    }

    public String generateBankAccountID() {
        return MathUtils.generateUniqueID(storage.getBankAccountMap().keySet().toString());
    }

    public BankAccount getBankAccount(String bankAccountID) {
        return storage.getBankAccount(bankAccountID);
    }
}

package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.utils.MathUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible for bank accounts.
 *
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
    public String createBankAccount(UserAccount userAccount, String bankAccountName) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> usedNames = new ArrayList<>();
            userAccount.getBankAccountList().forEach((String bankAccountID) -> usedNames.add(storage.getBankAccount(bankAccountID).getBankAccountName()));
            if (!usedNames.contains(bankAccountName)) {
                String bankAccountID = generateBankAccountID();
                BankAccount bankAccount = new BankAccount(bankAccountID, bankAccountName);
                storage.addBankAccount(bankAccountID, bankAccount);
                userAccount.addBankAccount(bankAccount.getBankAccountID());
                sb.append("New banking account ").append(bankAccountName).append(" has been created.");
            } else {
                sb.append("Cannot create a new account named ").append(bankAccountName).append(": an account of that name already exists.");
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }


    /**
     * The method allows the user to rename the selected bank account.
     *
     * @return A string that either confirms the bank account being renamed or
     * informs the user that the bank account could not be renamed.
     */
    public String renameBankAccount(UserAccount userAccount, String bankAccountID, String bankAccountName) {
        StringBuilder sb = new StringBuilder();

        if (userAccount.getBankAccountList().contains(bankAccountID)) {
            if (bankAccountName.length() <= 30) {
                storage.getBankAccount(bankAccountID).setBankAccountName(bankAccountName);
                sb.append("Your bank account has been renamed to ").append(bankAccountName).append(".");
            } else {
                sb.append("Your bank account cannot be renamed as the new name is ").append(bankAccountName.length()).append(" characters long.");
            }
        } else {
            sb.append("That bank account does not exist.");
        }
        return sb.toString();
    }

    /**
     * A method that allows for the deletion of a user's bank account.
     * Checks existing balance of the account before proceeding.
     *
     * @param userAccount
     * @param bankAccountID
     * @return
     */
    public String deleteBankAccount(UserAccount userAccount, String bankAccountID) {
        if (userAccount.getBankAccountList().contains(bankAccountID)) {
            if (storage.getBankAccount(bankAccountID).getBalance().compareTo(BigDecimal.ZERO) > 0) {
                return "Please transfer all money from this bank account first.";
            } else if (storage.getBankAccount(bankAccountID).getBalance().compareTo(BigDecimal.ZERO) < 0) {
                return "Please pay off or transfer your credit card to another account first.";
            } else if (!storage.getBankAccount(bankAccountID).getLoanMap().isEmpty()) {
                return "Please speak to the clerk about how loans can be transferred first.";
            } else {
                String name = storage.getBankAccount(bankAccountID).getBankAccountName();
                userAccount.getBankAccountList().remove(bankAccountID);
                storage.deleteBankAccount(bankAccountID);
                return "Deleted bank account " + name + ".";
            }
        } else {
            return "That bank account does not exist.";
        }
    }


    /**
     * Method used for the random generation of a Bank Account ID.
     *
     * @return
     */
    public String generateBankAccountID() {
        return MathUtils.generateUniqueID(storage.getBankAccountMap().keySet().toString());
    }

    public BankAccount getBankAccount(String bankAccountID) {
        return storage.getBankAccount(bankAccountID);
    }
}

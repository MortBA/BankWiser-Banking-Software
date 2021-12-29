package com.logic.bankwiser.accounts;

import com.logic.bankwiser.bank_accounts.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.*;

/**
 * @author Kevin Collins
 */

public class UserAccount {

    private final UUID userID;
    private String fullName;
    private String phoneNumber;
    private String address;
    private int socialSecurityNum;
    private String emailID;
    private String password;
    private List<BankAccount> bankAccountList;

    /**
     * Constructor for the UserAccount class that handles input validation.
     * @param userID                User ID is generated by the system
     * @param fullName              Full name of the user
     * @param phoneNumber           Phone Number of the user
     * @param address               Address of the user
     * @param socialSecurityNum     Social security number of the user
     * @param emailID               Email address that is associated with the user account
     * @param password              Password that the user specific for this user account
     * @throws Exception            If invalid input is provided
     */
    public UserAccount(UUID userID, String fullName, String phoneNumber, String address,
                       int socialSecurityNum, String emailID, String password) throws Exception {
        // TODO This regex pattern is a simple one I wrote myself. Should we switch to a premade one? -KC
        String emailPattern = "^[a-zA-Z]+@[a-zA-Z]+.[a-zA-Z]+";

        if (fullName.isEmpty()) {
            throw new Exception("The name field shouldn't be left blank.");
        } else if (phoneNumber.isEmpty()) {
            throw new Exception("The phone number field shouldn't be left blank.");
        } else if (address.isEmpty()) {
            throw new Exception("The address field shouldn't be left blank.");
        } else if (String.valueOf(socialSecurityNum).isEmpty()) {
            throw new Exception("The social security number field shouldn't be left blank.");
        } else if (emailID.isEmpty()) {
            throw new Exception("The email ID field shouldn't be left blank.");
        } else if (password.isEmpty()) {
            throw new Exception("The password field shouldn't be left blank.");
        } else {
            if (!Pattern.matches(emailPattern, emailID)) {
                throw new Exception("Email address is invalid.");
            } else {
                this.userID = userID;
                this.fullName = fullName;
                this.phoneNumber = phoneNumber;
                this.address = address;
                this.socialSecurityNum = socialSecurityNum;
                this.emailID = emailID;
                this.password = password;
                this.bankAccountList = new ArrayList<>();
            }
        }
    }

    public UUID getUserID() {
        return userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public int getSocialSecurityNum() {
        return socialSecurityNum;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getPassword() {
        return password;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }
}

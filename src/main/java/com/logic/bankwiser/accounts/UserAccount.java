package com.logic.bankwiser.accounts;

import com.logic.bankwiser.bank_accounts.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserAccount {

    private final UUID userID;
    private String fullName;
    private String phoneNumber;
    private String address;
    private int socialSecurityNum;
    private String emailID;
    private String password;
    private List<BankAccount> bankAccountList;

    public UserAccount(UUID userID, String fullName, String phoneNumber, String address,
                       int socialSecurityNum, String emailID, String password) throws Exception {

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

package com.logic.bankwiser.accounts;

/**
 * @author Kevin Collins
 */
public class ClerkAccount {

    private String fullName;
    private final String EMAIL_ID;
    private String password;

    /**
     * Constructor for the ClerkAccount class.
     *
     * @param fullName
     * @param emailID
     * @param password
     */

    /**
     * Constructor for the ClerkAccount class
     *
     * @param fullName
     * @param emailID
     * @param password
     */
    public ClerkAccount(String fullName, String emailID, String password) {
        this.fullName = fullName;
        this.EMAIL_ID = emailID;
        this.password = password;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

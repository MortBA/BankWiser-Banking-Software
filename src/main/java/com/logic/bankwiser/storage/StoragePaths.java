package com.logic.bankwiser.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This enum stores the local paths to the different storage text files.
 *
 * @author Mathias Hallander
 */
public enum StoragePaths {
    USERS(Paths.get("userStorage.txt"), "userStorage.txt"),
    BANK_ACCOUNTS(Paths.get("bankAccountStorage.txt"), "bankAccountStorage.txt"),
    TRANSACTIONS(Paths.get("transactionStorage.txt"), "transactionStorage.txt"),
    DEBIT_CARDS(Paths.get("debitCardStorage.txt"), "debitCardStorage.txt"),
    CREDIT_CARDS(Paths.get("creditCardStorage.txt"), "creditCardStorage.txt"),
    PERSONAL_LOANS(Paths.get("personalLoanStorage.txt"), "personalLoanStorage.txt"),
    HOME_LOANS(Paths.get("homeLoanStorage.txt"), "homeLoanStorage.txt"),
    VEHICLE_LOANS(Paths.get("vehicleLoanStorage.txt"), "vehicleLoanStorage.txt");

    StoragePaths(Path path, String stringPath) {
        this.path = path;
        this.stringPath = stringPath;
    }

    private final Path path;
    private final String stringPath;

    public Path getPath() {
        return path;
    }

    public String getStringPath() {
        return stringPath;
    }
}

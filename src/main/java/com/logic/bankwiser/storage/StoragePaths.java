package com.logic.bankwiser.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This enum stores the local paths to the different storage text files.
 *
 * @author Mathias Hallander
 */
public enum StoragePaths {
    USERS("src/main/resources/com/logic/bankwiser/storage/userStorage.txt"),
    BANK_ACCOUNTS("src/main/resources/com/logic/bankwiser/storage/bankAccountStorage.txt"),
    TRANSACTIONS("src/main/resources/com/logic/bankwiser/storage/transactionStorage.txt"),
    DEBIT_CARDS("src/main/resources/com/logic/bankwiser/storage/debitCardStorage.txt"),
    CREDIT_CARDS("src/main/resources/com/logic/bankwiser/storage/creditCardStorage.txt"),
    PERSONAL_LOANS("src/main/resources/com/logic/bankwiser/storage/personalLoanStorage.txt"),
    HOME_LOANS("src/main/resources/com/logic/bankwiser/storage/homeLoanStorage.txt"),
    VEHICLE_LOANS("src/main/resources/com/logic/bankwiser/storage/vehicleLoanStorage.txt");

    StoragePaths(String stringPath) {
        this.stringPath = stringPath;
    }

    private final String stringPath;

    public Path getPath() {
        return Paths.get(stringPath);
    }

    public String getStringPath() {
        return stringPath;
    }
}

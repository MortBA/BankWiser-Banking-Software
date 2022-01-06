module main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires org.junit.jupiter.api;

    opens com.gui.bankwiser to javafx.fxml;
    opens com.gui.bankwiser.controllers to javafx.fxml;
    opens com.logic.bankwiser.accounts to com.google.gson;
    opens com.logic.bankwiser.bank_accounts to com.google.gson;
    opens com.logic.bankwiser.transactions to com.google.gson;
    opens com.logic.bankwiser.controllers to com.google.gson;
    opens com.logic.bankwiser.cards to com.google.gson;
    opens com.logic.bankwiser.loans to com.google.gson;
    opens com.logic.bankwiser.facade to org.junit.platform.commons;

    exports com.gui.bankwiser;
    exports com.gui.bankwiser.controllers;
    exports com.logic.bankwiser;
    exports com.logic.bankwiser.transactions;
    exports com.logic.bankwiser.cards;
    exports com.logic.bankwiser.controllers;
    exports com.logic.bankwiser.loans;
    exports com.logic.bankwiser.storage;
    exports com.logic.bankwiser.utils;
    exports com.logic.bankwiser.accounts;
    exports com.logic.bankwiser.bank_accounts;
    exports com.logic.bankwiser.facade;
}
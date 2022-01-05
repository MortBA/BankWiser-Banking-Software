module main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens com.gui.bankwiser to javafx.fxml;
    opens com.gui.bankwiser.Controllers to javafx.fxml;
    opens com.logic.bankwiser to com.google.gson;
    opens com.logic.bankwiser.accounts to com.google.gson;
    opens com.logic.bankwiser.bank_accounts to com.google.gson;
    opens com.logic.bankwiser.transactions to com.google.gson;
    opens com.logic.bankwiser.controllers to com.google.gson;
    opens com.logic.bankwiser.cards to com.google.gson;
    opens com.logic.bankwiser.loans to com.google.gson;

    exports com.gui.bankwiser.Controllers;
    exports com.gui.bankwiser;
}
module main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.gui.bankwiser to javafx.fxml;
    opens com.gui.bankwiser.Controllers to javafx.fxml;
    exports com.gui.bankwiser.Controllers;
    exports com.gui.bankwiser;
}
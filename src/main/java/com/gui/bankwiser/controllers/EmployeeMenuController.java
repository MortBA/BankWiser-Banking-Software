package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class EmployeeMenuController {

    private final Facade facade = Facade.getInstance();

    @FXML
    private MenuButton customerRequests;

    @FXML
    private Label logOut;

    @FXML
    private TextArea textArea;

    @FXML
    public void initialize() {


    }


    @FXML
    private void logOutHoverIn() {
        logOut.setUnderline(true);
    }

    @FXML
    private void logOutHoverOut() {
        logOut.setUnderline(false);
    }

    @FXML
    public void logOutClick() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoginScreenEmployee.fxml");
    }

    @FXML
    public void onCustomerAccReqClicked(ActionEvent event) throws IOException {
        new BankWiserApp().changeScene("DeleteCustomerAccountScreen.fxml");
    }

    @FXML
    public void onUserAccReqClicked() throws IOException {
        new BankWiserApp().changeScene("DeleteCustomerAccountScreen.fxml");
    }

    @FXML
    public void onReportErrorClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EMreportError.fxml");
    }
}



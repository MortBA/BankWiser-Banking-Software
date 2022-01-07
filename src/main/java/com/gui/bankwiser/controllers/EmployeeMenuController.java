package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeMenuController {

    @FXML
    private Button customerAccountRequests;

    @FXML
    private Label logOut;


    @FXML
    private Label dateLabel;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {


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
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EmployeeMenuCustomerAccountRequest.fxml");
    }

    @FXML
    public void onReportErrorClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EmployeeMenuReportError.fxml");
    }
}



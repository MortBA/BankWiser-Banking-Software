package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class EmployeeMenuController {

    @FXML
    private Button customerAccountRequestsButton;
    @FXML
    private Label logOut;
    @FXML
    private Label dateLabel;

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



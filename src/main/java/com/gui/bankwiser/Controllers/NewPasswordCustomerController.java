package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewPasswordCustomerController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField passwordLabel;

    @FXML
    private TextField retypePasswordLabel;

    @FXML
    public void cancelButtonClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("ForgotPasswordCustomer.fxml");
    }
    @FXML
    public void confirmButtonClicked(){
    }
}

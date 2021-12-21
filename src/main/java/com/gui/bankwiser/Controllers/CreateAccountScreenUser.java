package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreateAccountScreenUser {

    @FXML
    private AnchorPane BankwiserLogo;

    @FXML
    private AnchorPane LoginElements;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonLogin;

    @FXML
    void CancelClicked(MouseEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoginScreenCustomerController.fxml");
    }

    @FXML
    private void CancelHoverIn(){
        buttonCancel.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void CancelHoverOut(){
        buttonCancel.setStyle("-fx-background-color: #ed2762;");
    }


    @FXML
    void LoginClicked(MouseEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CustomerMenuScreen.fxml");
    }


    @FXML
    private void LoginHoverIn(){
        buttonLogin.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void LoginHoverOut(){
        buttonLogin.setStyle("-fx-background-color: #2d9bf0;");
    }


}

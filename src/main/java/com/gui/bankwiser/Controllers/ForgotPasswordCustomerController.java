package com.gui.bankwiser.Controllers;
import com.gui.bankwiser.BankWiserApp;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPasswordCustomerController {


    @FXML
    TextField reTypeEmail;
    @FXML
    TextField email = new TextField();

    @FXML
    Button cancelButton = new Button();
    @FXML
    Button confirmButton = new Button();

    @FXML
    private void confirmHoverIn() {
        confirmButton.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut() {
        confirmButton.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn() {
        cancelButton.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut() {
        cancelButton.setStyle("-fx-background-color: #ed2762;");
    }

    //cancel button
    @FXML
    private void cancelButtonClicked() throws Exception {
       // cancelButton.setOnAction(actionEvent -> );
    }

    @FXML
    private void confirmButtonClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("NewPasswordCustomer.fxml");

    }
}
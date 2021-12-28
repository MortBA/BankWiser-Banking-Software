package com.gui.bankwiser.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ForgotPasswordEmployeeController {

    @FXML
    private Button confirmForgotPassButton;

    @FXML
    private Button cancelForgotPassButton;

    @FXML
    private void confirmHoverIn() {
        confirmForgotPassButton.setStyle("-fx-background-color: #4bacf7;");
    }
    @FXML
    private void confirmHoverOut() {
        confirmForgotPassButton.setStyle("-fx-background-color: #2d9bf0;");
    }
    @FXML
    private void cancelHoverIn() {
        cancelForgotPassButton.setStyle("-fx-background-color: #fc4a7f;");
    }
    @FXML
    private void cancelHoverOut() {
        cancelForgotPassButton.setStyle("-fx-background-color: #ed2762;");
    }

    @FXML public Stage stg = new Stage();

    @FXML
    private void confirmButtonClicked() throws IOException {
        stg.showAndWait();
    }

    @FXML
    private void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/NewPasswordEmployee.fxml"));
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handleCloseForgotPasswordAction(ActionEvent event) {
        Stage stage = (Stage) cancelForgotPassButton.getScene().getWindow();
        stage.close();
    }



}

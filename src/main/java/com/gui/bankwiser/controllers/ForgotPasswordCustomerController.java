package com.gui.bankwiser.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class to perform all functionalities for 'Forgot password' screen for customer.
 *
 * @author Sejal
 */

public class ForgotPasswordCustomerController {

    @FXML
    private Button confirmForgotPassButton;
    @FXML
    private Button cancelForgotPassButton;
    @FXML
    private TextField ssNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField retypeEmail;

    @FXML
    public Stage stg = new Stage();

    /**
     * Initializes new stage for 'new password' screen
     * The stage is initialized when forgot password (this) screen appears.
     * The new stage is used to fill new password for the customer.
     * The stage has initModality functionality.
     */
    @FXML
    public void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/NewPasswordCustomer.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Functions as a controller method to display the 'new password' screen.
     * The new screen appears when confirm button on 'forgot password' screen is clicked.
     *
     * @throws IOException IOException
     */
    @FXML
    private void confirmButtonClicked() throws IOException {
        if (ssNumber.getText().trim().isEmpty() || email.getText().trim().isEmpty() || retypeEmail.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            stg.showAndWait();
        }
    }

    /**
     * Closes the forgot password window when cancel button on 'forgot password' screen is clicked.
     *
     * @param event
     */
    @FXML
    public void handleCloseForgotPasswordAction(ActionEvent event) {
        Stage stage = (Stage) cancelForgotPassButton.getScene().getWindow();
        stage.close();
    }

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

}

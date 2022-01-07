package com.gui.bankwiser.controllers;

import com.logic.bankwiser.facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class to perform all functionalities for 'Forgot password' screen for customer.
 *
 * @author Sejal
 */
public class ForgotPasswordCustomerController {
    Facade facade = Facade.getInstance();

    @FXML
    private Button confirmForgotPassButton;
    @FXML
    private Button cancelForgotPassButton;

    @FXML
    private TextField email;
    @FXML
    private TextField retypeEmail;


    /**
     * Functions as a controller method to display the 'new password' screen.
     * The new screen appears when confirm button on 'forgot password' screen is clicked.
     *
     * @throws IOException IOException
     */
    @FXML
    private void confirmButtonClicked() throws IOException {
        if (email.getText().trim().isEmpty() || retypeEmail.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("An email will be sent to set new password.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
            facade.resetUserPassword(retypeEmail.getText());
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

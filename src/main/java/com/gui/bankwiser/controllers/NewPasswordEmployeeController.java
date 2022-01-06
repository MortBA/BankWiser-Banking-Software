package com.gui.bankwiser.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class to perform all functionalities for 'New password' screen for employee.
 *
 * @author Sejal
 */
public class NewPasswordEmployeeController {
    @FXML
    private Button confirmNewPassword;

    @FXML
    private Button cancelNewPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmNewPass;

    /**
     * The method displays a confirmation alert box when confirm button on 'new password' screen is clicked.
     * The box notifies user that the new password is set successfully.
     *
     * @throws IOException IOException
     */
    @FXML
    private void onConfirmNewPasswordClicked() throws IOException {
        if (newPassword.getText().trim().isEmpty() || confirmNewPass.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }else if(!(newPassword.getText().trim().equals(confirmNewPass.getText().trim()))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Both passwords must match.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your password is updated successfully.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                // probably not implement anything here
            }
        }
    }

    /**
     * The method closes the new password screen when cancel button on the screen is clicked.
     *
     * @param event
     */
    @FXML
    public void handleCloseNewPasswordAction(ActionEvent event) {
        Stage stage = (Stage) cancelNewPassword.getScene().getWindow();
        stage.close();
    }
}

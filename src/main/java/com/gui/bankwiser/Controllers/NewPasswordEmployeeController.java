package com.gui.bankwiser.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
     * An alert box appears when confirm button for on new password window for employee is clicked.
     * @throws IOException
     */
    @FXML
    private void onConfirmNewPasswordClicked() throws IOException {
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setContentText("Your password is updated successfully.");
        Optional<ButtonType> result = alertBox.showAndWait();
        if (result.get() == ButtonType.OK) {
            // probably not implement anything here
        }
    }

    /**
     * Closes the new password window when cancel button is clicked.
     * @param event
     */
    @FXML
    public void handleCloseNewPasswordAction(ActionEvent event) {
        Stage stage = (Stage) cancelNewPassword.getScene().getWindow();
        stage.close();
    }

}

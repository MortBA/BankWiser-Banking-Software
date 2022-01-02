package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for 'Change Password' screen
 * Opens when Change Password button on Account Settings screen is clicked.
 * @author Sejal
 */

public class ChangePasswordController {

    @FXML
    private Button cancelChangePassword;

    @FXML
    private Button confirmChangePassword;

    @FXML
    private PasswordField confirmNewPass;

    @FXML
    private PasswordField newPassword;

    /**
     *
     * The method functions when 'confirm' button on 'change password' screen is clicked.
     * The method checks if all fields on the screen are filled and then changes the password.
     * Alert box showing warning appears if any field is empty or unchecked and informs user to fill them.
     * Confirmation alert box appears when all fields are filled and the password is changed.
     * @param event
     * @throws IOException
     */

    @FXML
    void onConfirmChangePasswordClicked(MouseEvent event) throws IOException {
        if (confirmNewPass.getText().trim().isEmpty() || newPassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else{
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your password is changed successfully");
            Optional<ButtonType> result = alertBox.showAndWait();
            if(result.get() == ButtonType.OK){
                BankWiserApp app = new BankWiserApp();
                app.changeScene("AccountSettingsScreen.fxml");
            }
        }
    }

    /**
     *
     * The method closes the change password screen, when cancel button on the screen is clicked.
     */

    @FXML
    void onCancelChangePasswordClicked(){
        Stage stage = (Stage) cancelChangePassword.getScene().getWindow();
        stage.close();
    }
}

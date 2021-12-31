package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ChangePasswordController {
    @FXML
    private Button cancelChangePassword;

    @FXML
    private Button confirmChangePassword;

    @FXML
    private PasswordField confirmNewPass;

    @FXML
    private PasswordField newPassword;

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

    @FXML
    void onCancelChangePasswordClicked(){
        Stage stage = (Stage) cancelChangePassword.getScene().getWindow();
        stage.close();
    }
}

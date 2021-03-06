package com.gui.bankwiser.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Controller class to perform all functionalities for 'Delete bank account' screen.
 *
 * @author Chanisra
 */
public class DeleteBankAccountScreenController {

    @FXML
    public Button buttonConfirm;
    @FXML
    private Button buttonCancel;
    @FXML
    public TextField socialSecurityNumber;
    @FXML
    public TextField socialSecurityNumber1;
    @FXML
    public TextField fullName;
    @FXML
    public TextField email;
    @FXML
    public CheckBox agreementCheckbox;

    /**
     * The method shows confirmation alert box when confirm button on the screen is clicked
     *
     * @author Sejal
     */
    @FXML
    private void ConfirmClicked() {
        if (socialSecurityNumber.getText().trim().isEmpty() || socialSecurityNumber1.getText().trim().isEmpty()
                || fullName.getText().trim().isEmpty() || email.getText().trim().isEmpty() || !agreementCheckbox.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your bank account is deleted successfully.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                // probably not implement anything here
            }
        }
    }

    /**
     * The method closes the 'delete bank account' screen when cancel button on the screen is clicked.
     *
     * @author Sejal
     */
    @FXML
    private void CancelClicked() {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }
}

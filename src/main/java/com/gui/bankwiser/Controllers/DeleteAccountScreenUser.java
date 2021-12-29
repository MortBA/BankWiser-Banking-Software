package com.gui.bankwiser.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteAccountScreenUser {

    @FXML
    public Button buttonCancel;
    @FXML
    public Button buttonConfirm;
    @FXML
    public AnchorPane LoginElements;

    //popup
    @FXML
    private TextField socialSecurityNumber  = new TextField();
    @FXML
    private TextField fullName              = new TextField();
    @FXML
    private TextField email                 = new TextField();
    @FXML
    private CheckBox  agreementCheckbox     = new CheckBox();

    @FXML
    private void ConfirmClicked(){
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setContentText("Your user account is deleted successfully.");
        Optional<ButtonType> result = alertBox.showAndWait();
        if (result.get() == ButtonType.OK) {
            // probably not implement anything here
        }
    }

    @FXML
    private void CancelClicked(){
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void CancelHoverIn(){

    }
    @FXML
    private void CancelHoverOut(){

    }
    @FXML
    private void ConfirmHoverIn(){

    }
    @FXML
    private void ConfirmHoverOut(){

    }

}

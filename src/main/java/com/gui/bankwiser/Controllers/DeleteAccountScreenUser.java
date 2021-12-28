package com.gui.bankwiser.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteAccountScreenUser implements Initializable {






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
    private void CancelClicked(){

    }
    @FXML
    private void CancelHoverIn(){

    }
    @FXML
    private void CancelHoverOut(){

    }

    @FXML
    private void ConfirmClicked(){

    }
    @FXML
    private void ConfirmHoverIn(){

    }
    @FXML
    private void ConfirmHoverOut(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

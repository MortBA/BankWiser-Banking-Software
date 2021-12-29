package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DeleteBankAccountScreenController {

    @FXML
    public Button buttonConfirm;
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

    @FXML
    private MenuItem accountSettings;

    @FXML
    private MenuItem deleteBankAccount;

    @FXML
    private MenuItem deleteUserAccount;

    @FXML
    private MenuItem loans;

    @FXML
    private MenuItem myCards;

    @FXML
    private Button overview;

    @FXML
    private Button buttonLogOut;

    @FXML
    private Button buttonCancel;


    @FXML
    void transferMoney (ActionEvent event) throws IOException {
        //new BankWiserApp().changeScene("TransferMoney.fxml");
        //}
        BankWiserApp app = new BankWiserApp();
        app.changeScene("TransferMoney.fxml");
    }

    @FXML
    private MenuItem transactionsHistory;

    @FXML
    void onDeleteUserAccountClicked(ActionEvent event) {

    }

    @FXML
    void onMyCardsClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenuController.fxml");
    }

    @FXML
    void onOverviewClicked(ActionEvent event) throws IOException {
        new BankWiserApp().changeScene("CustomerMenuScreen.fxml");
    }

    /*@FXML
    public void onLogOutClicked() throws IOException{
        new BankWiserApp().changeScene("LoginScreenCustomer.fxml");
    }

     */

    @FXML
    private void ConfirmClicked(){
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setContentText("Your bank account is deleted successfully.");
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

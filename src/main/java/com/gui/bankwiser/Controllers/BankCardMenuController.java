package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class BankCardMenuController {

    @FXML
    private MenuItem accountSettings;

    @FXML
    private Label cardFunctionality;

    @FXML
    private Label changeCardPin;

    @FXML
    private MenuItem deleteBankAccount;

    @FXML
    private Label deleteCard;

    @FXML
    private MenuItem deleteUserAccount;

    @FXML
    private MenuItem loans;

    @FXML
    private Button logOut;

    @FXML
    private MenuItem myCards;

    @FXML
    private Label newCreditCard;

    @FXML
    private Label newDebitCard;

    @FXML
    private Button overview;

    @FXML
    private MenuItem personalInfo;

    @FXML
    private MenuItem transactionsHistory;

    @FXML
    private CheckBox TnC;

    @FXML
    private Button cancelChangePin;

    @FXML
    private Button submitChangePin;

    @FXML
    private TextField newPin;

    @FXML
    private TextField confirmPin;

    @FXML
    private TextField currentPin;

    @FXML
    private TextField cardNumber;

    @FXML
    private Button cancelDeleteCard;

    @FXML
    private Button confirmDeleteCard;

    @FXML
    private CheckBox lostCard;

    @FXML
    private CheckBox dislikeService;

    @FXML
    private CheckBox cardUnused;

    @FXML
    private TextField cardNumberToDelete;

    @FXML
    private TextField cardPinToDelete;

    @FXML
    private TextField creditCardPin;

    @FXML
    private TextField confirmCreditCardPin;

    @FXML
    private Button cancelCreditCard;

    @FXML
    private Button submitCreditCard;

    @FXML
    private TextField debitCardPin;

    @FXML
    private TextField confirmDebitCardPin;

    @FXML
    private Button cancelDebitCard;

    @FXML
    private Button submitDebitCard;



    @FXML
    void onDeleteUserAccountClicked(ActionEvent event) {
    }

    @FXML
    void onLogOutClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoginScreenCustomer.fxml");
    }

    @FXML
    void onMyCardsClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenuController.fxml");
    }

    @FXML
    void onOverviewClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CustomerMenuScreen.fxml");
    }
    @FXML
    public void onChangePinClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("ChangePin.fxml");
    }
    @FXML
    public void onDeleteCardClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("DeleteCard.fxml");
    }
    @FXML
    public void onCreditCardClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("NewCreditCard.fxml");
    }
    @FXML
    public void onDebitCardClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("NewDebitCard.fxml");
    }
    @FXML
    public void onModifyFuncClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("ModifyFunctionality.fxml");
    }

    @FXML
    public void cardFuncHoverIn(){
        cardFunctionality.setUnderline(true);
    }
    @FXML
    public void cardFuncHoverOut(){
        cardFunctionality.setUnderline(false);
    }
    @FXML
    public void deleteCardHoverIn(){
        deleteCard.setUnderline(true);
    }
    @FXML
    public void deleteCardHoverOut(){
        deleteCard.setUnderline(false);
    }
    @FXML
    public void creditCardHoverIn(){
        newCreditCard.setUnderline(true);
    }
    @FXML
    public void creditCardHoverOut(){
        newCreditCard.setUnderline(false);
    }
    @FXML
    public void debitCardHoverIn(){
        newDebitCard.setUnderline(true);
    }
    @FXML
    public void debitCardHoverOut(){
        newDebitCard.setUnderline(false);
    }
    @FXML
    public void changePinHoverIn(){
        changeCardPin.setUnderline(true);
    }
    @FXML
    public void changePinHoverOut(){
        changeCardPin.setUnderline(false);
    }
    @FXML
    public void forgotPinHoverIn(){
        cardFunctionality.setUnderline(true);
    }
    @FXML
    public void forgotPinHoverOut(){
        cardFunctionality.setUnderline(false);
    }

    @FXML
    public void onSubmitChangePinClicked() throws IOException {
        if (!TnC.isSelected() || newPin.getText().trim().isEmpty() || currentPin.getText().trim().isEmpty()
            || confirmPin.getText().trim().isEmpty() || cardNumber.getText().trim().isEmpty())  {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }
        else{
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your card pin is changed successfully.");
            alertBox.setTitle("Success!");
            Optional<ButtonType> result = alertBox.showAndWait();
            if(result.get() == ButtonType.OK){
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        }
    }

    @FXML
    public void onForgotCurrentPinClicked() throws IOException{
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setContentText("Send request for pin change and our employee will get back to you soon");
        Optional<ButtonType> result = alertBox.showAndWait();
        if(result.get() == ButtonType.OK){
           Alert alertBox2 = new Alert(Alert.AlertType.CONFIRMATION);
           alertBox.setContentText("Your request is sent successfully.");
           alertBox.setTitle("Success!");
           Optional<ButtonType> result2 = alertBox2.showAndWait();
           if(result2.get() == ButtonType.OK){
               BankWiserApp app = new BankWiserApp();
               app.changeScene("BankCardMenu.fxml");

        }
    }else if(result.get() == ButtonType.CANCEL){
            BankWiserApp app = new BankWiserApp();
            app.changeScene("BankCardMenu.fxml");
        }
    }

    @FXML
    public void onConfirmDeleteCardClicked() throws IOException{
        if (!TnC.isSelected() || lostCard.getText().trim().isEmpty() || dislikeService.getText().trim().isEmpty()
                || cardPinToDelete.getText().trim().isEmpty() || cardNumberToDelete.getText().trim().isEmpty()
                || cardUnused.getText().trim().isEmpty())  {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else{
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your card is deleted successfully.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if(result.get() == ButtonType.OK){
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        }
    }

    @FXML
    public void onCancelDeleteCardClicked() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenu.fxml");
    }


    @FXML
    public void onCancelCreditCardClicked() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenu.fxml");
    }

    @FXML
    public void onSubmitCreditCardClicked() throws IOException {
        if (!TnC.isSelected() || creditCardPin.getText().trim().isEmpty() || confirmCreditCardPin.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your application for a credit card has been submitted." +
                    "We’ll let you know whether it has been accepted or rejected after evaluation.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        }
    }

    @FXML
    public void onSubmitDebitCardClicked() throws IOException{
        if (!TnC.isSelected() || debitCardPin.getText().trim().isEmpty() || confirmDebitCardPin.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else{
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Application accepted. We’ll let you know when the card is shipped");
            Optional<ButtonType> result = alertBox.showAndWait();
            if(result.get() == ButtonType.OK){
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        }
    }
}

package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for performing all functionalities of Bank Card menu.
 *
 * @author Sejal
 */

public class BankCardMenuController {

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
    private MenuItem personalInfo;
    @FXML
    private MenuItem transactionsHistory;

    @FXML
    private Label cardFunctionality;
    @FXML
    private Label changeCardPin;
    @FXML
    private Label deleteCard;
    @FXML
    private Label newCreditCard;
    @FXML
    private Label newDebitCard;

    @FXML
    private Button logOut;
    @FXML
    private Button overview;
    @FXML
    private Button cancelChangePin;
    @FXML
    private Button submitChangePin;
    @FXML
    private Button cancelDeleteCard;
    @FXML
    private Button confirmDeleteCard;
    @FXML
    private Button cancelCreditCard;
    @FXML
    private Button submitCreditCard;
    @FXML
    private Button cancelDebitCard;
    @FXML
    private Button submitDebitCard;
    @FXML
    private Button cancelFunc;
    @FXML
    private Button submitFunc;

    @FXML
    private TextField cardNumberToDelete;
    @FXML
    private TextField cardPinToDelete;
    @FXML
    private TextField creditCardPin;
    @FXML
    private TextField debitCardPin;
    @FXML
    private TextField confirmDebitCardPin;
    @FXML
    private TextField newPin;
    @FXML
    private TextField confirmPin;
    @FXML
    private TextField currentPin;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField confirmCreditCardPin;
    @FXML
    private TextField transactionLimit;

    @FXML
    private CheckBox TnC;
    @FXML
    private CheckBox lostCard;
    @FXML
    private CheckBox dislikeService;
    @FXML
    private CheckBox cardUnused;

    @FXML
    private RadioButton blockCard;
    @FXML
    private RadioButton unblockCard;
    @FXML
    private RadioButton blockTransaction;
    @FXML
    private RadioButton unblockTransaction;

    @FXML
    private ChoiceBox cardList;

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
        app.changeScene("BankCardMenu.fxml");
    }

    @FXML
    void onAccountSettingsClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("AccountSettingsScreen.fxml");
    }

    @FXML
    void onTransactionHistoryClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("TransactionHistoryScreen.fxml");
    }

    @FXML
    void onTransferMoneyClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("TransferMoneyScreen.fxml");
    }

    @FXML
    void onOverviewClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CustomerMenuScreen.fxml");
    }

    @FXML
    void onLoansClicked() throws Exception {
        new BankWiserApp().changeScene("LoansOverview.fxml");
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
        if ((!blockCard.isSelected() && !unblockCard.isSelected())
                || (!blockTransaction.isSelected() && !unblockTransaction.isSelected()) || !cardList.isShowing()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your card pin is changed successfully.");
            alertBox.setTitle("Success!");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        }
    }

    /**
     * The method functions when 'submit' button on 'change pin' screen is clicked.
     * The method checks if all fields on 'change pin' screen are filled and then submits request for changing card pin.
     * Alert box showing warning appears if any field is empty or unchecked and informs user to fill them.
     * Confirmation alert box appears when all fields are filled and the card pin is changed.
     *
     * @throws IOException IOException
     */
    @FXML
    public void onSubmitChangePinClicked() throws IOException {
        if (!TnC.isSelected() || newPin.getText().trim().isEmpty() || currentPin.getText().trim().isEmpty()
                || confirmPin.getText().trim().isEmpty() || cardNumber.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your card pin is changed successfully.");
            alertBox.setTitle("Success!");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        }
    }

    /**
     * Information alert box appears when the customer clicks on the 'forgot current pin' label on 'change pin' screen,
     * alert box appears, notifying the next step to take as a customer.
     *
     * @throws IOException IOException
     */
    @FXML
    public void onForgotCurrentPinClicked() throws IOException {
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setContentText("Send request for pin change and our employee will get back to you soon");
        Optional<ButtonType> result = alertBox.showAndWait();
        if (result.get() == ButtonType.OK) {
            Alert alertBox2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your request is sent successfully.");
            alertBox.setTitle("Success!");
            Optional<ButtonType> result2 = alertBox2.showAndWait();
            if (result2.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        } else if (result.get() == ButtonType.CANCEL) {
            BankWiserApp app = new BankWiserApp();
            app.changeScene("BankCardMenu.fxml");
        }
    }

    /**
     * The method functions when 'confirm' button on delete card menu screen is clicked.
     * The method checks if all fields are filled on 'delete card' screen and then confirms delete card request.
     * Warning alert box appears if any field is empty or unchecked, notifying the user to fill them.
     * Confirmation alert box appears when all fields are filled and the Card is deleted.
     *
     * @throws IOException IOException
     */
    @FXML
    public void onConfirmDeleteCardClicked() throws IOException {
        if (!TnC.isSelected() || lostCard.getText().trim().isEmpty() || dislikeService.getText().trim().isEmpty()
                || cardPinToDelete.getText().trim().isEmpty() || cardNumberToDelete.getText().trim().isEmpty()
                || cardUnused.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your card is deleted successfully.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        }
    }

    @FXML
    public void onCancelButtonClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenu.fxml");
    }

    /**
     * The method functions when 'submit' button on 'new credit card' screen is clicked.
     * The method checks if all fields are filled on 'credit card' screen and then submits the new credit card request.
     * Warning alert box appears if any field is empty or unchecked.
     * Confirmation alert box appears when all fields are filled and request is sent.
     *
     * @throws IOException IOException
     */
    @FXML
    public void onSubmitCreditCardClicked() throws IOException {
        if (!TnC.isSelected() || creditCardPin.getText().trim().isEmpty() || confirmCreditCardPin.getText().trim().isEmpty()) {
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

    /**
     * The method functions when 'submit' button on 'debit card' screen is clicked.
     * THe method checks if all fields on 'debit card' screen are filled and then submits the new debit card request.
     * Warning alert box appears if any field is empty or unchecked.
     * Confirmation alert box appears when all fields are filled and card is made.
     *
     * @throws IOException IOException
     */
    @FXML
    public void onSubmitDebitCardClicked() throws IOException {
        if (!TnC.isSelected() || debitCardPin.getText().trim().isEmpty() || confirmDebitCardPin.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Application accepted. We’ll let you know when the card is shipped");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        }
    }

    @FXML
    public void cardFuncHoverIn() {
        cardFunctionality.setUnderline(true);
    }

    @FXML
    public void cardFuncHoverOut() {
        cardFunctionality.setUnderline(false);
    }

    @FXML
    public void deleteCardHoverIn() {
        deleteCard.setUnderline(true);
    }

    @FXML
    public void deleteCardHoverOut() {
        deleteCard.setUnderline(false);
    }

    @FXML
    public void creditCardHoverIn() {
        newCreditCard.setUnderline(true);
    }

    @FXML
    public void creditCardHoverOut() {
        newCreditCard.setUnderline(false);
    }

    @FXML
    public void debitCardHoverIn() {
        newDebitCard.setUnderline(true);
    }

    @FXML
    public void debitCardHoverOut() {
        newDebitCard.setUnderline(false);
    }

    @FXML
    public void changePinHoverIn() {
        changeCardPin.setUnderline(true);
    }

    @FXML
    public void changePinHoverOut() {
        changeCardPin.setUnderline(false);
    }

    @FXML
    public void forgotPinHoverIn() {
        cardFunctionality.setUnderline(true);
    }

    @FXML
    public void forgotPinHoverOut() {
        cardFunctionality.setUnderline(false);
    }

    // Button color change for delete card menu
    @FXML
    private void confirmHoverIn() {
        confirmDeleteCard.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut() {
        confirmDeleteCard.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn() {
        cancelDeleteCard.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut() {
        cancelDeleteCard.setStyle("-fx-background-color: #ed2762;");
    }

    // Button color change for credit card menu
    @FXML
    private void confirmHoverIn2() {
        submitCreditCard.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut2() {
        submitCreditCard.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn2() {
        cancelCreditCard.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut2() {
        cancelCreditCard.setStyle("-fx-background-color: #ed2762;");
    }

    // Button color change for debit card menu

    @FXML
    private void confirmHoverIn3() {
        submitDebitCard.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut3() {
        submitDebitCard.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn3() {
        cancelDebitCard.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut3() {
        cancelDebitCard.setStyle("-fx-background-color: #ed2762;");
    }

    // Button color change for modify card functionality menu

    @FXML
    private void confirmHoverIn4() {
        submitFunc.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut4() {
        submitFunc.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn4() {
        cancelFunc.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut4() {
        cancelFunc.setStyle("-fx-background-color: #ed2762;");
    }

    // Button color change for change card pin menu

    @FXML
    private void confirmHoverIn5() {
        submitChangePin.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut5() {
        submitChangePin.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn5() {
        cancelChangePin.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut5() {
        cancelChangePin.setStyle("-fx-background-color: #ed2762;");
    }

}

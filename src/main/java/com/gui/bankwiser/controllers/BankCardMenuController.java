package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.logic.bankwiser.facade.Facade;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for performing all functionalities of Bank Card menu.
 *
 * @author Sejal
 */

public class BankCardMenuController {

    private final Facade facade = Facade.getInstance();

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
    private Stage stg = new Stage();


    @FXML
    public void initialize() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/DeleteAccountScreenUserPopup.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/DeleteBankAccountScreenPopup.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onDeleteUserAccountClicked(ActionEvent event) {
        stg.showAndWait();
    }
    @FXML
    void onDeleteBankAccountClicked(ActionEvent event) {
        stg.showAndWait();
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
        BankWiserApp app = new BankWiserApp();
        app.changeScene("ModifyFunctionality.fxml");
    }

    @FXML
    public void onCancelButtonClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenu.fxml");
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


}

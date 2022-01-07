package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.facade.Facade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for Transfer money screen.
 *
 * @author Chanisra
 */
public class TransferMoneyScreenController {


    Facade facade = Facade.getInstance();

    @FXML
    private MenuItem deleteBankAccount;

    @FXML
    private MenuItem deleteUserAccount;

    @FXML
    private MenuItem transactionsHistory;

    @FXML
    private MenuItem loans;

    @FXML
    private MenuItem myCards;

    @FXML
    private MenuItem transferMoney;

    @FXML
    private Button overview;

    @FXML
    private Button buttonLogOut;

    @FXML
    private ChoiceBox fromAccount;
    @FXML
    public TextField receiverAccount;

    @FXML
    public TextField amount;
    @FXML
    public TextArea note;
    @FXML
    public Button buttonConfirm;
    @FXML
    public Button buttonCancel;
    @FXML
    public MenuItem transactionHistory;
    @FXML
    private MenuItem accountSettings;

    @FXML
    private Stage stg = new Stage();

    @FXML
    private void initialize() {

        ObservableList<String> cardList = FXCollections.observableArrayList(facade.getBankAccounts());
        fromAccount.setItems(cardList);

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
    void onOverviewClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CustomerMenuScreen.fxml");
    }

    @FXML
    public void onLogOutClicked() throws IOException {
        new BankWiserApp().changeScene("LoginScreenCustomer.fxml");
    }

    @FXML
    void onAccountSettingsClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("AccountSettingsScreen.fxml");
    }

    @FXML
    void onMyCardsClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenu.fxml");
    }

    @FXML
    void onTransferMoneyClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("TransferMoneyScreen.fxml");
    }

    @FXML
    void onTransactionHistoryClicked() throws Exception {
        new BankWiserApp().changeScene("TransactionHistoryScreen.fxml");
    }

    @FXML
    void onLoansClicked() throws Exception {
        new BankWiserApp().changeScene("LoansOverview.fxml");
    }

    @FXML
    void onDeleteBankAccountClicked() throws Exception {
        stg.showAndWait();
    }

    @FXML
    void onDeleteUserAccountClicked() throws Exception {
        stg.showAndWait();
    }

    public void onSubmitClicked(ActionEvent event) throws IOException {
        if (receiverAccount.getText().trim().isEmpty() || amount.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }else{
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Money transferred successfully.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("CustomerMenuScreen.fxml");
                StringBuilder stringBuilder = new StringBuilder();
                facade.transferMoney(fromAccount.getSelectionModel().getSelectedItem().toString(),
                        receiverAccount.getText(), note.getText(), Double.parseDouble(amount.getText()));
            }
        }
    }
}

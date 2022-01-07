package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.facade.Facade;
import com.logic.bankwiser.utils.Input;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/**
 * Controller class for performing all functionalities of 'Customer Menu' screen
 *
 * @author Chanisra
 */
public class CustomerMenuScreenController {

    Facade facade = Facade.getInstance();

    @FXML
    private MenuItem accountSettings;
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
    public Label PersonAccountAmount;
    @FXML
    public Label SharedAccountNumber;
    @FXML
    public Label SharedAccountAmount;
    @FXML
    public Label PersonAccountNumber;
    @FXML
    public MenuItem transactionHistory;
    @FXML
    public Label acccountNameLabel;
    @FXML
    public Label accontNameLabel;
    @FXML
    public Label expirationLabel;
    @FXML
    public Label cardNumberLabel;
    @FXML
    public Stage stg = new Stage();
    @FXML
    public Stage stg2 = new Stage();
    @FXML
    private Label accountNameLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label cardStatusLabel;
    @FXML
    private ChoiceBox<String> cardChoiceBox;
    @FXML
    private ChoiceBox<String> bankAccountChoiceBox;

    /**
     * Initializes new stages to delete user account and bank account.
     * Both stages have initModality functionality.
     *
     * @author Sejal
     */
    @FXML
    private void initialize() {

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
            stg2.setScene(scene);
            stg2.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!facade.getBankAccounts().isEmpty()) {
            ObservableList<String> bankAccountNumbers = FXCollections.observableArrayList();

            bankAccountNumbers.addAll(facade.getBankAccounts());

            bankAccountChoiceBox.setItems(bankAccountNumbers);
            bankAccountChoiceBox.setValue(bankAccountNumbers.get(0));
            facade.selectedBankAccount(bankAccountChoiceBox.getValue());
            updateScreenInformation(facade.getBankAccount(bankAccountNumbers.get(0)));
            updateCardChoiceBox();
        }

        bankAccountChoiceBox.setOnAction((actionEvent -> {
            updateScreenInformation(facade.getBankAccount(bankAccountChoiceBox.getValue()));
            facade.selectedBankAccount(bankAccountChoiceBox.getValue());
            if (facade.getBankAccount(bankAccountChoiceBox.getValue()).getCardMap().isEmpty()) {
                ObservableList<String> emptyList = FXCollections.observableArrayList();
                emptyList.add("No cards on this account");
                cardChoiceBox.setItems(emptyList);
                cardChoiceBox.setValue("No cards on this account");
            } else {
                updateCardChoiceBox();
            }
        }));
        cardChoiceBox.setOnAction((actionEvent -> {
            updateScreenInformation(facade.getActiveBankAccount().getCard(cardChoiceBox.getValue()));
        }));
    }

    @FXML
    public void updateCardChoiceBox() {
        HashMap<String, DebitCard> cardHashMap = facade.getActiveBankAccount().getCardMap();
        if (!cardHashMap.isEmpty()) {
            ObservableList<String> cardNumberList = FXCollections.observableArrayList();
            cardNumberList.clear();
            cardNumberList.addAll(cardHashMap.keySet().stream().toList());
            cardChoiceBox.setItems(cardNumberList);
            cardChoiceBox.setValue(cardNumberList.get(0));
            updateScreenInformation(facade.getActiveBankAccount().getCard(cardNumberList.get(0)));
        }
    }

    @FXML
    public void updateScreenInformation(BankAccount bankAccount) {
        accountNameLabel.setText(bankAccount.getBankAccountName());
        amountLabel.setText(bankAccount.getBalance().toString());
    }

    @FXML
    public void updateScreenInformation(DebitCard debitCard) {
        StringBuilder stringBuilder = new StringBuilder();
        if (debitCard != null) {
            stringBuilder.append((debitCard.getFrozenStatus() ? "Card is currently: Frozen" : "Card is currently: Not frozen")).append(Input.EOL);
            stringBuilder.append((debitCard.getOnlineStatus() ? "Card is currently: Blocked" : "Card is currently: Open to transactions")).append(Input.EOL);
            stringBuilder.append("Transaction limit: ").append(debitCard.getExpenditureMax()).append(Input.EOL);
            stringBuilder.append("Current region: ").append(debitCard.getRegion()).append(Input.EOL);
        } else {
            stringBuilder.append("Nothing here");
        }
        cardStatusLabel.setText(stringBuilder.toString());
    }
    /**
     * Opens new stage to delete the user account when 'delete user account' option in customer menu screen is clicked
     *
     * @throws IOException IOException
     * @author Sejal
     */
    @FXML
    public void onDeleteUserAccountClicked() throws IOException {
        stg.showAndWait();
    }

    /**
     * Opens new stage to delete the bank account when 'delete bank account' option in customer menu screen is clicked.
     *
     * @throws IOException IOException
     */
    @FXML
    public void onDeleteBankAccountClicked() throws IOException {
        stg2.showAndWait();
    }

    @FXML
    void onOverviewClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CustomerMenuScreen.fxml");
    }

    @FXML
    public void onLogOutClicked(ActionEvent event) throws IOException {
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
    void onTransactionHistoryClicked(ActionEvent event) throws Exception {
        new BankWiserApp().changeScene("TransactionHistoryScreen.fxml");
    }

    @FXML
    void onLoansClicked(ActionEvent event) throws Exception {
        new BankWiserApp().changeScene("LoansOverview.fxml");
    }
}


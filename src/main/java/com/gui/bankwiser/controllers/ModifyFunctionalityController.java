package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
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
import org.controlsfx.control.ListActionView;

import java.io.IOException;
import java.util.Optional;

public class ModifyFunctionalityController {

    private final Facade facade = Facade.getInstance();

    @FXML
    public Button logOut;
    @FXML
    public Button overview;
    @FXML
    public MenuItem personalInfo;
    @FXML
    public MenuItem accountSettings;
    @FXML
    public MenuItem myCards;
    @FXML
    public MenuItem transferMoney;
    @FXML
    public MenuItem transactionsHistory;
    @FXML
    public MenuItem deleteBankAccount;
    @FXML
    public MenuItem deleteUserAccount;

    @FXML
    private Button cancelFunc;
    @FXML
    private Button submitFunc;

    @FXML
    private TextField transactionLimit;

    @FXML
    private RadioButton toggleCardBlocked;
    @FXML
    private RadioButton toggleCardFreeze;
    @FXML
    private RadioButton blockTransaction;
    @FXML
    private RadioButton unblockTransaction;

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
    private Label cardFrozenStatus;
    @FXML
    private Label cardBlockedStatus;
    @FXML
    private Label transactionLimitLabel;
    @FXML
    private Label currentRegionLabel;

    @FXML
    private ChoiceBox<String> cardListChoiceBox;
    @FXML
    private ChoiceBox<String> regionsChoiceBox;

    @FXML
    private Stage stg = new Stage();

    ObservableList<String> activeRegions = FXCollections.observableArrayList();

    private void regionsData(){
        String a = "Europe";
        String b = "North America";
        String c = "Asia";
        String d = "South America";
        String e = "Oceania";
        String f = "Africa";
        activeRegions.addAll(a,b,c,d,e,f);
    }

    @FXML
    public void initialize() {
        cardListChoiceBox.setOnAction((actionEvent -> {
            updateScreenInformation(facade.getActiveBankAccount().getCard(cardListChoiceBox.getValue()));
        }));
        regionsData();
        regionsChoiceBox.setItems(activeRegions);
        if (!facade.getActiveBankAccount().getCardMap().isEmpty()) {
            ObservableList<String> cardList = FXCollections.observableArrayList(facade.getActiveBankAccount().getCardMap().keySet());
            cardListChoiceBox.setItems(cardList);
            cardListChoiceBox.setValue(cardList.get(0));
            updateScreenInformation(facade.getActiveBankAccount().getCard(cardListChoiceBox.getValue()));
        } else {
            cardListChoiceBox.setValue("No cards on this account");
        }

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
    public void updateScreenInformation(DebitCard debitCard) {
        cardFrozenStatus.setText((debitCard.getFrozenStatus() ? "Card is currently: Frozen" : "Card is currently: Open"));
        cardBlockedStatus.setText((debitCard.getOnlineStatus() ? "Card is currently: Blocked" : "Card is currently: Open"));
        transactionLimitLabel.setText("Transaction limit: " + debitCard.getExpenditureMax());
        currentRegionLabel.setText("Current region: " + debitCard.getRegion());
    }

    @FXML
    public void onSubmitFuncClicked() throws Exception {
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You've made the following changes:").append(Input.EOL);
        alertBox.setTitle("Success!");
        if (toggleCardFreeze.isSelected()) {
            System.out.println("toggled freeze");
            stringBuilder.append(facade.toggleFreezeCard(cardListChoiceBox.getSelectionModel().getSelectedItem())).append(Input.EOL);
        }
        if (toggleCardBlocked.isSelected()) {
            System.out.println("toggled block");
            stringBuilder.append(facade.toggleOnlineTransactions(cardListChoiceBox.getSelectionModel().getSelectedItem())).append(Input.EOL);
        }
        if (!transactionLimit.getText().isEmpty()) {
            System.out.println("changed transaction limit");
            stringBuilder.append(facade.changeSpendingLimit(cardListChoiceBox.getSelectionModel().getSelectedItem(), Double.parseDouble(transactionLimit.getText()))).append(Input.EOL);
        }
        if (regionsChoiceBox.getValue() != null) {
            System.out.println("changed region");
            stringBuilder.append(facade.modifyRegion(cardListChoiceBox.getSelectionModel().getSelectedItem(), regionsChoiceBox.getValue())).append(Input.EOL);
        }
        alertBox.setContentText(stringBuilder.toString());
        Optional<ButtonType> result = alertBox.showAndWait();
        if (result.get() == ButtonType.OK) {
            BankWiserApp app = new BankWiserApp();
            app.changeScene("BankCardMenu.fxml");
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
}

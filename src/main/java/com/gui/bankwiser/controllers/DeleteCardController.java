package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.cards.DebitCard;
import com.logic.bankwiser.facade.Facade;
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
import java.util.HashMap;
import java.util.Optional;

public class DeleteCardController {

    private final Facade facade = Facade.getInstance();

    @FXML
    public TextArea reasonToDelete;
    @FXML
    public Button logOut;
    @FXML
    public MenuItem personalInfo;
    @FXML
    public MenuItem myCards;
    @FXML
    public MenuItem accountSettings;
    @FXML
    public MenuItem transferMoney;
    @FXML
    public MenuItem transactionsHistory;
    @FXML
    public MenuItem deleteBankAccount;
    @FXML
    public MenuItem deleteUserAccount;
    @FXML
    private Button cancelDeleteCard;
    @FXML
    private Button confirmDeleteCard;
    @FXML
    private TextField cardPinToDelete;
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
    private CheckBox termsAndConditions;

    @FXML
    private Stage stg = new Stage();


    @FXML
    private ChoiceBox<String> cardChoiceBox;

    @FXML
    public void initialize() {
        HashMap<String, DebitCard> cardHashMap = facade.getActiveBankAccount().getCardMap();
        if (!cardHashMap.isEmpty()) {
            ObservableList<String> cardNumberList = FXCollections.observableArrayList();
            cardNumberList.addAll(cardHashMap.keySet().stream().toList());
            cardChoiceBox.setItems(cardNumberList);
            cardChoiceBox.setValue(cardNumberList.get(0));
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

    /**
     * The method functions when 'confirm' button on delete card menu screen is clicked.
     * The method checks if all fields are filled on 'delete card' screen and then confirms delete card request.
     * Warning alert box appears if any field is empty or unchecked, notifying the user to fill them.
     * Confirmation alert box appears when all fields are filled and the Card is deleted.
     *
     * @throws IOException IOException
     */
    @FXML
    void deleteCard(ActionEvent event) throws IOException {
        if (!termsAndConditions.isSelected() || cardPinToDelete.getText().trim().isEmpty() || cardChoiceBox.getSelectionModel().getSelectedItem().trim().isEmpty()) {
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
                facade.deleteCard(cardChoiceBox.getSelectionModel().getSelectedItem(), reasonToDelete.getText(), Integer.parseInt(cardPinToDelete.getText()));
            }
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

package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for performing all functionalities of Account settings screen.
 */
public class AccountSettingsScreenController {

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
    private Button changePassword;

    @FXML
    private AnchorPane BankwiserLogo;
    @FXML
    private AnchorPane LoginElements;

    @FXML
    private ImageView QuestionMarkButton;

    @FXML
    private final Stage stg = new Stage();

    /**
     * Initializes new stage for 'change password' screen.
     * The stage is initialized when the account settings screen appears.
     * The stage has initModality functionality.
     */
    @FXML
    public void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/ChangePasswordScreen.fxml"));
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

    //Todo loan screen
    @FXML
    void onLoansClicked() throws Exception {
        new BankWiserApp().changeScene("");
    }

    @FXML
    void onDeleteBankAccountClicked() throws Exception {
        new BankWiserApp().changeScene("");
    }

    @FXML
    void onDeleteUserAccountClicked() throws Exception {
        new BankWiserApp().changeScene("");
    }

    /**
     * Opens the 'change password' screen, which is initialized in the beginning.
     * The screen opens only when 'change password' button on the account settings screen is clicked.
     *
     * @throws Exception
     */
    @FXML
    void onChangePasswordClicked() throws Exception {
        stg.showAndWait();
    }
}

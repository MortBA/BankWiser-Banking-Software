package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CustomerMenuScreenController {

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
    private MenuItem transferMoney;

    @FXML
    private Button overview;

    @FXML
    private Button buttonLogOut;

    @FXML
    private void transferMoney (ActionEvent event) throws IOException {
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
        app.changeScene("BankCardMenu.fxml");
    }

    @FXML
    void onOverviewClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CustomerMenuScreen.fxml");
    }

    @FXML
    public void onLogOutClicked() throws IOException{
        new BankWiserApp().changeScene("LoginScreenCustomer.fxml");
    }

    @FXML
    void onAccountSettingsClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("AccountSettingsScreen.fxml");
    }

    @FXML
    void onTransferMoneyClicked(MouseEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("TransferMoneyScreen.fxml");
    }

    @FXML
    void onTransactionHistoryClicked() throws Exception{
        new BankWiserApp().changeScene("TransactionHistoryScreen.fxml");
    }

}


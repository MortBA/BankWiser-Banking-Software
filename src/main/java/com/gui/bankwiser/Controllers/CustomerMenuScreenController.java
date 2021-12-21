package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

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
    private Button overview;

    @FXML
    private Button logOut;

    @FXML
    private MenuItem personalInfo;

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
    void onOverviewClicked(ActionEvent event) {

    }

    @FXML
    public void onLogOutClicked() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoginScreenCustomerController.fxml");
    }

}


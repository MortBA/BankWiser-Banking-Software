package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for performing all functionalities of 'Customer Menu' screen
 *
 * @author Chanisra
 */

public class CustomerMenuScreenController {

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
    public Stage stg = new Stage();

    @FXML
    public Stage stg2 = new Stage();

    /**
     * Initializes new stages to delete user account and bank account.
     * Both stages have initModality functionality.
     *
     * @param
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
    }

    /**
     * Opens new stage to delete the user account when 'delete user account' option in customer menu screen is clicked
     *
     * @throws IOException
     * @author Sejal
     */
    @FXML
    public void onDeleteUserAccountClicked() throws IOException {
        stg.showAndWait();
    }

    /**
     * Opens new stage to delete the bank account when 'delete bank account' option in customer menu screen is clicked.
     *
     * @throws IOException
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
}


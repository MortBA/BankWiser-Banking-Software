package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML public Stage stg = new Stage();

    /**
     *
     * Opens new window to delete user account.
     * @param
     */
    @FXML
    void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/DeleteAccountScreenUserPopup.fxml"));
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onDeleteUserAccountClicked() throws IOException{
        stg.showAndWait();
    }

    /**
     *
     * Opens new window to delete user bank account.
     * @param
     */
    @FXML
    void initialize(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/DeleteBankAccountScreenPopup.fxml"));
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onDeleteBankAccountClicked() throws IOException{
        stg.showAndWait();
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

}


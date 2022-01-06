package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.transactions.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controller class for transaction history screen.
 *
 * @author Chanisra
 */
public class TransactionHistoryScreenController implements Initializable {
    @FXML
    private TableView<Transaction> transactionHistoryTable;
    @FXML
    private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn amountColumn;
    @FXML
    private TableColumn accountNumberColumn;
    @FXML
    private TableColumn balanceColumn;
    @FXML
    private Stage stg = new Stage();


    //***********//just to test//**********//
    private BankAccount BAcc;

    //TODO only works in main branch?? \/\/\/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                BAcc = new BankAccount("280872273", "Channi's fake bankacount");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        BAcc.addTransaction(new Transaction(BAcc.getBankAccountID(), BigDecimal.valueOf(-40.20), "N/A", LocalDate.of(2019, 3, 21), BigDecimal.valueOf(600.50)));
//        BAcc.addTransaction(new Transaction(BAcc.getBankAccountID(), BigDecimal.valueOf(609.60), "N/A", LocalDate.of(2021, 11, 14), BigDecimal.valueOf(623.51)));
//        BAcc.addTransaction(new Transaction(BAcc.getBankAccountID(), BigDecimal.valueOf(231.10), "N/A", LocalDate.of(2022, 12, 20), BigDecimal.valueOf(1003.57)));


        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("TransactionDate"));


        transactionHistoryTable.setItems(getObservableTransactionList());
    }

    private ObservableList<Transaction> getObservableTransactionList() {
        return (ObservableList<Transaction>) FXCollections.observableArrayList(BAcc.getTransactionMap().values());

        //for delete account pop up screens

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

    //todo Sejal input fxml
    @FXML
    void onDeleteBankAccountClicked() throws Exception {
        stg.showAndWait();
    }

    @FXML
    void onDeleteUserAccountClicked() throws Exception {
        stg.showAndWait();
    }

}
// TODO: 2021-12-22
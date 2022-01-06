package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.facade.Facade;
import com.logic.bankwiser.transactions.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

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


    //***********//just to test//**********//
    private Facade facade;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facade = Facade.getInstance();

//        BAcc.addTransaction(new Transaction(BAcc.getBankAccountID(), BigDecimal.valueOf(-40.20), "N/A", LocalDate.of(2019, 3, 21), BigDecimal.valueOf(600.50)));
//        BAcc.addTransaction(new Transaction(BAcc.getBankAccountID(), BigDecimal.valueOf(609.60), "N/A", LocalDate.of(2021, 11, 14), BigDecimal.valueOf(623.51)));
//        BAcc.addTransaction(new Transaction(BAcc.getBankAccountID(), BigDecimal.valueOf(231.10), "N/A", LocalDate.of(2022, 12, 20), BigDecimal.valueOf(1003.57)));


        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("TransactionDate"));


        transactionHistoryTable.setItems(getObservableTransactionList());
    }

    private ObservableList<Transaction> getObservableTransactionList() {
        try {
            facade.createUserAccount("Channi", "Chanisra Magnusson", "Yuki" , "Yuki", "199703241114", "channi@email.com", "Yuki0324");
            facade.userLogin("Channi", "Yuki");
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> bankaccounts = facade.getBankAccounts();
        HashMap<String, Transaction> transactionHashMap;
        BankAccount Temp;

        ObservableList<Transaction> allTransactions = FXCollections.observableArrayList();

        for (String bankaccount : bankaccounts) {
            facade.selectedBankAccount(bankaccount);
            Temp = facade.getActiveBankAccount();

            transactionHashMap = Temp.getTransactionMap();
            transactionHashMap.forEach((id, transaction) -> {
                allTransactions.add(transaction);
            });
        }


        return allTransactions;
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

    //todo Sejal input fxml
    @FXML
    void onDeleteBankAccountClicked() throws Exception {
        new BankWiserApp().changeScene("");
    }

    @FXML
    void onDeleteUserAccountClicked() throws Exception {
        new BankWiserApp().changeScene("");
    }

}
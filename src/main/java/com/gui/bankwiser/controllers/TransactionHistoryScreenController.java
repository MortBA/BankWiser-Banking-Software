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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Controller class for transaction history screen.
 *
 * @author Chanisra
 */
public class TransactionHistoryScreenController implements Initializable {
    public MenuItem deleteBankAccount;
    public MenuItem deleteUserAccount;
    public MenuItem loans;
    public MenuItem transferMoney;
    public MenuItem transactionHistory;
    public MenuItem myCards;
    public MenuItem accountSettings;
    public Button overview;
    public Button buttonLogOut;
    Facade facade = Facade.getInstance();
    @FXML
    private TableView<Transaction> transactionHistoryTable;
    @FXML
    private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML
    private TableColumn<Transaction, String> nameColumn;
    @FXML
    private TableColumn<Transaction, BigDecimal> amountColumn;
    @FXML
    private TableColumn<Transaction, String> accountNumberColumn;
    @FXML
    private TableColumn<Transaction, BigDecimal> balanceColumn;
    @FXML
    public Stage stg = new Stage();

    @FXML
    public Stage stg2 = new Stage();


    //***********//just to test//**********//
    UserAccount temp;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facade = Facade.getInstance();

        try {
            BankAccount temporary ;
           facade.userLogin("second@gmail.com", "password");
            System.out.println(facade.createBankAccount("savings"));
            facade.selectedBankAccount("63136517");
            temporary = facade.getActiveBankAccount();
            temporary.addTransaction(new Transaction("001", temporary.getBankAccountID(), new BigDecimal("69.42" ), "", LocalDateTime.of(2021, 3, 21, 14, 59), new BigDecimal("2401.40")));
            temporary.addTransaction(new Transaction("002", temporary.getBankAccountID(), new BigDecimal("69.52" ), "", LocalDateTime.of(2021, 3, 21, 15, 5), new BigDecimal("2413.40")));
            temporary.addTransaction(new Transaction("003", temporary.getBankAccountID(), new BigDecimal("-69.62" ), "", LocalDateTime.of(2021, 3, 21, 17, 1), new BigDecimal("240183.40")));

        } catch (Exception e) {
            e.printStackTrace();
        }


        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("TransactionDate"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>(facade.getActiveBankAccount().getBankAccountName()));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, BigDecimal>("MoneyTransferred"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<Transaction, BigDecimal>("BalanceAfterTransaction"));
        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("BankAccountID"));


        transactionHistoryTable.setItems(getObservableTransactionList());

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

    private ObservableList<Transaction> getObservableTransactionList() {

        List<String> bankaccounts                   = facade.getBankAccounts();
        ObservableList<Transaction> transactions    = FXCollections.observableArrayList();

        for (String bankaccount : bankaccounts) {
            facade.selectedBankAccount(bankaccount);
            BankAccount temp = facade.getActiveBankAccount();

            HashMap<String, Transaction> transactionHashMap = temp.getTransactionMap();
            transactionHashMap.forEach( (id, transaction) -> {
                transactions.add(transaction);
            });
        }

        return transactions;
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
}

package com.gui.bankwiser.controllers;


import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.facade.Facade;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class DeleteCustomerAccountScreenController implements Initializable {

    public Button buttonLogOut;
    public Button reportError1;
    public Button reportError;
    public Button customerAccountReq;
    private ArrayList<UserAccount> userAccounts;
    private Facade facade;

    @FXML
    private Label socialSecurityLabel;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label accountIdLabel;
    @FXML
    private CheckBox agreementCheckbox;
    @FXML
    private TextArea noteTextArea;


    @FXML
    private ListView<UserAccount> requestList;


    private void selectionChanged(UserAccount userAccount) {
        fullNameLabel.setText(userAccount.getFullName());
        socialSecurityLabel.setText(String.valueOf(userAccount.getSocialSecurityNum()));
        emailLabel.setText(userAccount.getEmailID());
        accountIdLabel.setText(String.valueOf(userAccount.getUserID()));
    }
    //List<String> bankaccounts                   = facade.getBankAccounts();
    //        ObservableList<Transaction> transactions    = FXCollections.observableArrayList();
    //
    //        for (String bankaccount : bankaccounts) {
    //            facade.selectedBankAccount(bankaccount);
    //            BankAccount bankAccount = facade.getActiveBankAccount();
    //
    //            HashMap<String, Transaction> transactionHashMap = bankAccount.getTransactionMap();
    //            transactionHashMap.forEach( (id, transaction) -> {
    //                transactions.add(transaction);
    //            });
    //        }
    //
    //        return transactions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
       // userAccounts.addAll(facade.)
       // facade.getActiveBankAccount().getCardMap().keySet();

        /*try {
            temp.add(new UserAccount(UUID.randomUUID(), "Chanisra Magnusson", "0712345678", "Drottningatan 14", "199703241114", "channi@email.com", "Yuki0324"));
            temp.add(new UserAccount(UUID.randomUUID(), "Sejal Kanaskar", "0723456789", "Drottningatan 15", "199803241114", "sejal@email.com", "Sejal0324"));
            temp.add(new UserAccount(UUID.randomUUID(), "Kevin Collins", "0734567890", "Drottningatan 16", "199903241114", "kevin@email.com", "Kevin0324"));

        } catch (Exception e) {
            e.printStackTrace();
        }

         */

        requestList.getItems().addAll(userAccounts);

        requestList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserAccount>() {
            @Override
            public void changed(ObservableValue<? extends UserAccount> observableValue, UserAccount userAccount, UserAccount t1) {
                selectionChanged(requestList.getSelectionModel().getSelectedItem());
            }
        });

    }


    public void onDeleteClicked(ActionEvent event) {
    }

    public void onDeclineClicked(ActionEvent event) {
    }

    public void onLogOutClicked(ActionEvent event) throws Exception {
        new BankWiserApp().changeScene("LoginScreenEmployee.fxml");
    }

    public void onReportErrorClicked(ActionEvent event) throws IOException {
        new BankWiserApp().changeScene("EmployeeMenuReportError.fxml");
    }

    public void onUserAccReqClicked(ActionEvent event) {
    }

    public void onCustomerAccReqClicked(ActionEvent event) throws IOException {
        new BankWiserApp().changeScene("EmployeeMenuCustomerAccountRequest.fxml");
    }

}

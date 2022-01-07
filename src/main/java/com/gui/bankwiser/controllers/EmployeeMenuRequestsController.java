package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.facade.Facade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.HashMap;

public class EmployeeMenuRequestsController {

    private final Facade facade = Facade.getInstance();

    @FXML
    private Label fullNameLabel;

    @FXML
    public Button buttonLogOut;
    @FXML
    public Button reportError1;
    @FXML
    public Button reportError;
    @FXML
    public Button customerAccountReq;

    @FXML
    private Label socialSecurityLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label accountIdLabel;
    @FXML
    private CheckBox agreementCheckbox;
    @FXML
    private TextArea noteTextArea;

    @FXML
    public TableColumn request;

    @FXML
    public TableColumn viewRequest;

    @FXML
    private ListView<String> requestList;

    @FXML
    private Label logOut;

    @FXML
    public void initialize() {
        ObservableList<String> requests = FXCollections.observableArrayList();
        HashMap<String, UserAccount> userAccountHashMap = facade.getDeletionRequests();

        requests.addAll(userAccountHashMap.keySet());

        requestList.setItems(requests);
        requestList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String currentRequest = requestList.getSelectionModel().getSelectedItem();
            updateRequestScreen(userAccountHashMap.get(currentRequest));
        });
    }

    @FXML
    public void updateRequestScreen(UserAccount userAccount) {
        fullNameLabel.setText(userAccount.getFullName());
        socialSecurityLabel.setText(String.valueOf(userAccount.getSocialSecurityNum()));
        emailLabel.setText(userAccount.getEmailID());
        accountIdLabel.setText(String.valueOf(userAccount.getUserID()));
    }

    @FXML
    private void logOutHoverIn() {
        logOut.setUnderline(true);
    }

    @FXML
    private void logOutHoverOut() {
        logOut.setUnderline(false);
    }

    @FXML
    public void onReportErrorClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EmployeeMenuReportError.fxml");
    }

    @FXML
    public void logOutClick() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoginScreenEmployee.fxml");
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
        new BankWiserApp().changeScene("EmployeeMenuRequests.fxml");
    }

}

package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeMenuController {

    @FXML
    private MenuButton customerRequests;

    @FXML
    private Label logOut;

    @FXML
    private TextArea textArea;

    @FXML
    private Button viewRequestButton;

    @FXML
    private MenuButton services;

    @FXML
    private MenuItem loanApprovalReq;

    @FXML
    private MenuItem creditCardReq;

    @FXML
    private MenuItem feedback;

    @FXML
    private MenuItem reportError;

    @FXML
    private MenuItem userAccountReq;

    @FXML
    private ListView requestList;

    @FXML
    public void logOutClick() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoginScreenEmployee.fxml");
    }

    @FXML
    public void onUserAccReqClick() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EMuserAccountReq.fxml");

    }

    @FXML
    public void onLoanApprovalReqClick() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EMloanReq.fxml");
    }
    @FXML
    public void onCreditCardReqClick() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EMcreditCardReq.fxml");

    }
    @FXML
    public void onFeedbackClick() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EMfeedback.fxml");

    }
    @FXML
    public void onReportErrorClick() throws IOException{



    }

 /* @Override
    public void initialize(URL url, ResourceBundle rb){
        requestList.getItems().addAll("You're all caught up!", "Golf");
        requestList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

  */

    public void onViewReqClicked(ActionEvent actionEvent) {
        String textAreaStr = "";
        ObservableList listOfRequests = requestList.getSelectionModel().getSelectedItems();

        for (Object request : listOfRequests) {
            textAreaStr += String.format("/n", (String) request);

            textArea.setText(textAreaStr);
        }
    }
}
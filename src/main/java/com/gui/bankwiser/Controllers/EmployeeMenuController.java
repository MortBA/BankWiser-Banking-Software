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
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeMenuController {

    @FXML
    private MenuButton customerRequests;

        @FXML
        private Label logOut;

        @FXML
        private Button viewRequestButton;

        @FXML
        private Button loanApprovalReq;

        @FXML
        private Button reportError;

        @FXML
        private Button creditCardReq;

        @FXML
        private Button userAccountReq;

        @FXML
        private ListView requestList;

        @FXML
        private TextArea textArea;

        @FXML
        private void logOutHoverIn(){
            logOut.setUnderline(true);
        }

        @FXML
        private void logOutHoverOut() {
            logOut.setUnderline(false);
        }

        @FXML
        public void logOutClick() throws IOException {
            BankWiserApp app = new BankWiserApp();
            app.changeScene("LoginScreenEmployee.fxml");
        }

            public void onUserAccReqClicked() throws IOException{
                BankWiserApp app = new BankWiserApp();
                app.changeScene("EMuserAccountReq.fxml");

            }

            @FXML
            public void onLoanApprovalReqClicked() throws IOException{
                    BankWiserApp app = new BankWiserApp();
                    app.changeScene("EMloanReq.fxml");
                }

                @FXML
                public void onCreditCardReqClicked() throws IOException {
                    BankWiserApp app = new BankWiserApp();
                    app.changeScene("EMcreditCardReq.fxml");

                }

                @FXML
                public void onReportErrorClicked() throws IOException{
                    BankWiserApp app = new BankWiserApp();
                    app.changeScene("EMreportError.fxml");
                }

                @FXML
                public void onSubmitErrorClick() throws IOException{
                    Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
                    alertBox.setContentText("Thank you for reporting the error. We will try to fix it");
                    alertBox.setTitle("Error sent");
                    Optional <ButtonType> result = alertBox.showAndWait();
                    if(result.get() == ButtonType.OK){
                        BankWiserApp app = new BankWiserApp();
                        app.changeScene("EmployeeMenu.fxml");
                    }
                }

                @FXML
                public void onCancelErrorClick() throws IOException{
                    BankWiserApp app = new BankWiserApp();
                    app.changeScene("EmployeeMenu.fxml");
                }

 /* @Override
    @FXML
    public void onCancelFeedbackClick() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EmployeeMenu.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        requestList.getItems().addAll("You're all caught up!", "Golf");
        requestList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

  */
                public void onViewReqClicked(ActionEvent actionEvent) {
                    String textAreaStr = "";
                    ObservableList listOfRequests = requestList.getSelectionModel().getSelectedItems();

                    textArea.setText(textAreaStr);
                }
            }



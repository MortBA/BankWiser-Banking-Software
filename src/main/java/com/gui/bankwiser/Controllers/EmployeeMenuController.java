package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeMenuController implements Initializable{

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
        private Label requestLabel;

        @FXML
        private Button userAccountReq;

        @FXML
        private ListView<String> requestList;

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
        @FXML
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

        String[] requests = {"Pizza", "Sushi", "Ramen"};
        String currentRequest;


       /* @Override
        public void initialize(URL url, ResourceBundle rb) {
            requestList.getItems().addAll(requests);
            requestList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    currentRequest = requestList.getSelectionModel().getSelectedItem();
                    requestLabel.setText(currentRequest);
                }
            });

        }

        */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> items = FXCollections.observableArrayList("test1", "test2");
        ListView<String> list = new ListView<>(items);

        list.setEditable(true);
        list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(ListView<String> param) {
                return new TextFieldListCell<>(new StringConverter<String>() {

                    @Override
                    public String toString(String object) {
                        return object;
                    }

                    @Override
                    public String fromString(String string) {
                        return string;
                    }
                });
            }
        });
    }

}



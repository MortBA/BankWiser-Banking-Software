package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.bank_accounts.BankAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class EmployeeMenuController {

    @FXML
    public TableColumn date;

    @FXML
    public TableView requestTable;

    @FXML
    public TableColumn request;

    @FXML
    public TableColumn viewRequest;

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

    private BankAccount BAcc;

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            BAcc = new BankAccount(280_872_273, "Channi's fake bankacount");
        }catch (Exception e) {e.printStackTrace();}

        BAcc.addTransaction(new Transaction(BAcc.getBankAccountID(), BigDecimal.valueOf(-40.20), "N/A", LocalDate.of(2019, 3, 21), BigDecimal.valueOf(600.50) ) );
        BAcc.addTransaction(new Transaction(BAcc.getBankAccountID(), BigDecimal.valueOf(609.60), "N/A", LocalDate.of(2021, 11, 14), BigDecimal.valueOf(623.51) ) );
        BAcc.addTransaction(new Transaction(BAcc.getBankAccountID(), BigDecimal.valueOf(231.10), "N/A", LocalDate.of(2022, 12, 20), BigDecimal.valueOf(1003.57) ) );


        date.setCellValueFactory( new PropertyValueFactory<Transaction, LocalDate>("TransactionDate") );

        requestTable.setItems(getObservableTransactionList());
    }

    private ObservableList<Transaction> getObservableTransactionList() {
        return (ObservableList<Transaction>) FXCollections.observableArrayList(BAcc.getTransactionList());
    }

     */

    @FXML
    private void logOutHoverIn() {
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
    public void onCustomerAccReqClicked(ActionEvent event) throws IOException {
        new BankWiserApp().changeScene("DeleteCustomerAccountScreen.fxml");
    }

    @FXML
    public void onUserAccReqClicked() throws IOException {
        new BankWiserApp().changeScene("DeleteCustomerAccountScreen.fxml");
    }

    @FXML
    public void onReportErrorClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EMreportError.fxml");
    }

    @FXML
    public void onSubmitErrorClick() throws IOException {
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setContentText("Thank you for reporting the error. We will try to fix it");
        alertBox.setTitle("Error sent");
        Optional<ButtonType> result = alertBox.showAndWait();
        if (result.get() == ButtonType.OK) {
            BankWiserApp app = new BankWiserApp();
            app.changeScene("EmployeeMenu.fxml");
        }
    }

    @FXML
    public void onCancelErrorClick() throws IOException {
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

        */

}



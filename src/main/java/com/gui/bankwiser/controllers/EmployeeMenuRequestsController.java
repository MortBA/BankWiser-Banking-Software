package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.facade.Facade;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class EmployeeMenuRequestsController {

    private final Facade facade = Facade.getInstance();

    @FXML
    private Button customerAccountRequests;
    @FXML
    private Button viewRequestButton;

    @FXML
    private Label requestLabel;
    @FXML
    public TableColumn date;

    @FXML
    public TableView requestTable;

    @FXML
    public TableColumn request;

    @FXML
    public TableColumn viewRequest;

    @FXML
    private ListView<String> requestList;

    @FXML
    private Label logOut;

    String[] requests = {"Pizza", "Sushi", "Ramen"};
    String currentRequest;

    @FXML
    public void initialize() {

        requestList = new ListView<>();
        requestList.getItems().addAll(requests);
        requestList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentRequest = requestList.getSelectionModel().getSelectedItem();
                requestLabel.setText(currentRequest);
            }
        });
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
}

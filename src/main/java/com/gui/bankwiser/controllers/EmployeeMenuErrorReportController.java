package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.facade.Facade;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeMenuErrorReportController {

    private final Facade facade = Facade.getInstance();

    @FXML
    private Label logOut;


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
}

package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class NewCreditCardController {

    private final Facade facade = Facade.getInstance();

    @FXML
    public TextField monthlyExpense;
    @FXML
    public TextField monthlyIncome;

    @FXML
    private Button cancelCreditCard;
    @FXML
    private Button submitCreditCard;

    @FXML
    private TextField creditCardPin;
    @FXML
    private TextField confirmCreditCardPin;

    @FXML
    private CheckBox TnC;


    @FXML
    private Stage stg = new Stage();


    @FXML
    public void initialize() {

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
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * The method functions when 'submit' button on 'new credit card' screen is clicked.
     * The method checks if all fields are filled on 'credit card' screen and then submits the new credit card request.
     * Warning alert box appears if any field is empty or unchecked.
     * Confirmation alert box appears when all fields are filled and request is sent.
     *
     * @throws IOException IOException
     */
    @FXML
    void createCreditCard(ActionEvent event) throws IOException {
        if (!TnC.isSelected() || creditCardPin.getText().trim().isEmpty() || confirmCreditCardPin.getText().trim().isEmpty()
                || monthlyExpense.getText().trim().isEmpty() || monthlyIncome.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else if (!(creditCardPin.getText().trim().equals(confirmCreditCardPin.getText().trim()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Both pins should match");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your application for a credit card has been submitted." +
                    "We’ll let you know whether it has been accepted or rejected after evaluation.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
                //TODO more inputs for credit card needed.
                facade.createCreditCard(Integer.parseInt(creditCardPin.getText()), Integer.parseInt(confirmCreditCardPin.getText()),
                        Integer.parseInt(monthlyIncome.getText()), Integer.parseInt(monthlyExpense.getText()));
            }
        }

    }

    // Button color change for credit card menu
    @FXML
    private void confirmHoverIn2() {
        submitCreditCard.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut2() {
        submitCreditCard.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn2() {
        cancelCreditCard.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut2() {
        cancelCreditCard.setStyle("-fx-background-color: #ed2762;");
    }
}

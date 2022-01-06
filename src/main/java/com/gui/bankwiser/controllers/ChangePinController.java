package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.facade.Facade;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class ChangePinController {

    private final Facade facade = Facade.getInstance();

    @FXML
    private MenuItem accountSettings;
    @FXML
    private MenuItem deleteBankAccount;
    @FXML
    private MenuItem deleteUserAccount;
    @FXML
    private MenuItem loans;
    @FXML
    private MenuItem myCards;
    @FXML
    private MenuItem personalInfo;
    @FXML
    private MenuItem transactionsHistory;

    @FXML
    private Label cardFunctionality;
    @FXML
    private Label changeCardPin;
    @FXML
    private Label newCreditCard;
    @FXML
    private Label newDebitCard;

    @FXML
    private Button logOut;
    @FXML
    private Button overview;
    @FXML
    private Button cancelChangePin;
    @FXML
    private Button submitChangePin;

    @FXML
    private TextField newPin;
    @FXML
    private TextField confirmPin;
    @FXML
    private TextField currentPin;

    @FXML
    private CheckBox TnC;

    @FXML
    private ChoiceBox cardList;

    @FXML
    private Stage stg = new Stage();

    @FXML
    public void initialize() {

        // cardList.setItems(activeCards);

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
     * The method functions when 'submit' button on 'change pin' screen is clicked.
     * The method checks if all fields on 'change pin' screen are filled and then submits request for changing card pin.
     * Alert box showing warning appears if any field is empty or unchecked and informs user to fill them.
     * Confirmation alert box appears when all fields are filled and the card pin is changed.
     *
     * @throws IOException IOException
     */
    @FXML
    void changeCardPin(ActionEvent event) throws IOException {
        if (!TnC.isSelected() || newPin.getText().trim().isEmpty() || currentPin.getText().trim().isEmpty()
                || confirmPin.getText().trim().isEmpty() || cardList.getSelectionModel().getSelectedItem().toString().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }else if(!(newPin.getText().trim().equals(confirmPin.getText().trim()))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Both new pins should match");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }
        else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your card pin is changed successfully.");
            alertBox.setTitle("Success!");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
                facade.changePin(cardList.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(currentPin.getText()),
                        Integer.parseInt(newPin.getText()), Integer.parseInt(confirmPin.getText()));
            }
        }
    }

    /**
     * Information alert box appears when the customer clicks on the 'forgot current pin' label on 'change pin' screen,
     * alert box appears, notifying the next step to take as a customer.
     *
     * @throws IOException IOException
     */
    @FXML
    public void onForgotCurrentPinClicked() throws IOException {
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setContentText("Send request for pin change and our employee will get back to you soon");
        Optional<ButtonType> result = alertBox.showAndWait();
        if (result.get() == ButtonType.OK) {
            Alert alertBox2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your request is sent successfully.");
            alertBox.setTitle("Success!");
            Optional<ButtonType> result2 = alertBox2.showAndWait();
            if (result2.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
            }
        } else if (result.get() == ButtonType.CANCEL) {
            BankWiserApp app = new BankWiserApp();
            app.changeScene("BankCardMenu.fxml");
        }
    }

    public TextField getNewPin() {
        newPin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!t1.matches("\\d*")){
                    newPin.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });
        return newPin;
    }

    // Button color change for change card pin menu

    @FXML
    public void forgotPinHoverIn() {
        cardFunctionality.setUnderline(true);
    }

    @FXML
    public void forgotPinHoverOut() {
        cardFunctionality.setUnderline(false);
    }

    @FXML
    private void confirmHoverIn5() {
        submitChangePin.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut5() {
        submitChangePin.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn5() {
        cancelChangePin.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut5() {
        cancelChangePin.setStyle("-fx-background-color: #ed2762;");
    }
}

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

public class DeleteCardController {

    private final Facade facade = Facade.getInstance();

    @FXML
    private Button cancelDeleteCard;
    @FXML
    private Button confirmDeleteCard;


    @FXML
    private TextField cardPinToDelete;

    @FXML
    private Label deleteCard;

    @FXML
    private CheckBox lostCard;
    @FXML
    private CheckBox dislikeService;
    @FXML
    private CheckBox cardUnused;

    @FXML
    private CheckBox TnC;

    @FXML
    private Stage stg = new Stage();


    @FXML
    private ChoiceBox cardNumberToDelete;

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
     * The method functions when 'confirm' button on delete card menu screen is clicked.
     * The method checks if all fields are filled on 'delete card' screen and then confirms delete card request.
     * Warning alert box appears if any field is empty or unchecked, notifying the user to fill them.
     * Confirmation alert box appears when all fields are filled and the Card is deleted.
     *
     * @throws IOException IOException
     */
    @FXML
    void deleteCard(ActionEvent event) throws IOException {
        if (!TnC.isSelected() || lostCard.getText().trim().isEmpty() || dislikeService.getText().trim().isEmpty()
                || cardPinToDelete.getText().trim().isEmpty() || cardNumberToDelete.getSelectionModel().getSelectedItem().toString().trim().isEmpty()
                || cardUnused.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your card is deleted successfully.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("BankCardMenu.fxml");
                facade.deleteCard(cardNumberToDelete.getSelectionModel().getSelectedItem().toString(), cardUnused.getText(), Integer.parseInt(cardPinToDelete.getText()));
            }
        }
    }

    // Button color change for delete card menu
    @FXML
    private void confirmHoverIn() {
        confirmDeleteCard.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut() {
        confirmDeleteCard.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn() {
        cancelDeleteCard.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut() {
        cancelDeleteCard.setStyle("-fx-background-color: #ed2762;");
    }
}

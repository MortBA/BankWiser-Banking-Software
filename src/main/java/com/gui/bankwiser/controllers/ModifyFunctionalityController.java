package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.facade.Facade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class ModifyFunctionalityController {

    private final Facade facade = Facade.getInstance();

    @FXML
    private Button cancelFunc;
    @FXML
    private Button submitFunc;

    @FXML
    private TextField transactionLimit;

    @FXML
    private RadioButton blockCard;
    @FXML
    private RadioButton unblockCard;
    @FXML
    private RadioButton blockTransaction;
    @FXML
    private RadioButton unblockTransaction;

    @FXML
    private ChoiceBox cardList;
    @FXML
    private ChoiceBox regionsChoiceBox;

    @FXML
    private Stage stg = new Stage();


    ObservableList<String> activeRegions = FXCollections.observableArrayList();

    private void regionsData(){
        activeRegions.removeAll();
        String a = "Africa";
        String b = "North America";
        String c = "Asia";
        String d = "South America";
        String e = "Oceania";
        String f = "Europe";
        activeRegions.addAll(a,b,c,d,e,f);
    }

    @FXML
    public void initialize() {
        regionsChoiceBox = new ChoiceBox();
        regionsChoiceBox.getItems().add("a");
        regionsData();

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

    @FXML
    public void onSubmitFuncClicked() throws Exception {
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setContentText("Your card pin is changed successfully.");
        alertBox.setTitle("Success!");
        Optional<ButtonType> result = alertBox.showAndWait();
        if (result.get() == ButtonType.OK) {
            BankWiserApp app = new BankWiserApp();
            app.changeScene("BankCardMenu.fxml");
            facade.toggleFreezeCard(cardList.getSelectionModel().getSelectedItem().toString());
            facade.toggleFreezeCard(cardList.getSelectionModel().getSelectedItem().toString());
            facade.toggleOnlineTransactions(cardList.getSelectionModel().getSelectedItem().toString());
            facade.changeSpendingLimit(cardList.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(transactionLimit.getText()));
        }
    }

    // Button color change for modify card functionality menu

    @FXML
    private void confirmHoverIn4() {
        submitFunc.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut4() {
        submitFunc.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn4() {
        cancelFunc.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut4() {
        cancelFunc.setStyle("-fx-background-color: #ed2762;");
    }
}

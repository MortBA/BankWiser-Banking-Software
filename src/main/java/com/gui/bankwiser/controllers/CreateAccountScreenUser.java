package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for performing functionalities for 'Create Account' screen for customers.
 *
 * @author Chanisra
 */

public class CreateAccountScreenUser {

    private final Facade facade = Facade.getInstance();

    @FXML
    private MenuItem accountSettings;
    @FXML
    private MenuItem deleteBankAccount;
    @FXML
    private MenuItem deleteUserAccount;
    @FXML
    private MenuItem transactionsHistory;
    @FXML
    private MenuItem loans;
    @FXML
    private MenuItem myCards;
    @FXML
    private Button overview;
    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonLogin;
    @FXML
    private AnchorPane BankwiserLogo;
    @FXML
    private AnchorPane LoginElements;
    @FXML
    private ImageView QuestionMarkButton;

    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private PasswordField retypedPassword;

    @FXML
    private TextField socialSecurityNumber;

    @FXML
    private TextField fullName;

    @FXML
    private final Stage stg = new Stage();

    /**
     * Initializes the 'question mark' screen which opens as a new stage when the question mark is clicked
     *
     * @author Sejal
     */

    @FXML
    public void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/QuestionMarkScreen.fxml"));
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
     * Opens new stage having related information when the question mark on 'Create account' screen is clicked.
     *
     * @author Sejal
     */

    @FXML
    private void questionMarkClicked() {
        stg.showAndWait();
    }

    @FXML
    void onMyCardsClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenuController.fxml");
    }

    @FXML
    void onOverviewClicked(ActionEvent event) {

    }

    @FXML
    public void onLogOutClicked() throws IOException {
        new BankWiserApp().changeScene("LoginScreenCustomer.fxml");
    }

    @FXML
    void CancelClicked(MouseEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoginScreen.fxml");
    }

    @FXML
    private void CancelHoverIn() {
        buttonCancel.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void CancelHoverOut() {
        buttonCancel.setStyle("-fx-background-color: #ed2762;");
    }


    @FXML
    void LoginClicked(MouseEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoginScreenCustomer.fxml");
        // TODO: 2021-12-21 Make A Screen "Account Successfully registered. 
    }


    @FXML
    private void LoginHoverIn() {
        buttonLogin.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void LoginHoverOut() {
        buttonLogin.setStyle("-fx-background-color: #2d9bf0;");
    }

    //QuestionMarkButton // todo:  implement if time over
    @FXML
    private void QuestionMarkHoverIn() {
    }

    @FXML
    private void QuestionMarkHoverOut() {

    }

    @FXML
    void createUser(ActionEvent event) throws IOException {
        if (email.getText().trim().isEmpty() || fullName.getText().trim().isEmpty() || password.getText().trim().isEmpty()
        || retypedPassword.getText().trim().isEmpty() || phoneNumber.getText().trim().isEmpty() || address.getText().trim().isEmpty()
        || socialSecurityNumber.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }else if(!(password.getText().trim().equals(retypedPassword.getText().trim()))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Both passwords should match");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }
        else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your account is successfully registered.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                app.changeScene("LoginScreenCustomer.fxml");
                facade.createUserAccount(email.getText(), fullName.getText(), password.getText(), retypedPassword.getText(),
                        phoneNumber.getText(), address.getText(), socialSecurityNumber.getText());
            }
        }

    }

}

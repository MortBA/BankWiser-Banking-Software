package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenCustomerController implements Initializable {

    @FXML
    private TextField UsernameBox;

    @FXML
    private PasswordField PasswordBox;

    @FXML
    private Label Error_Message;

    @FXML
    private AnchorPane LoginElements;

    @FXML
    private Label ForgottenPassword;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonCancel;

    @FXML
    private Label NotCustomer;

    //NotCustomer
    @FXML
    private void NotCustomerClicked() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CreateUserScreen.fxml");
    }

    @FXML
    private void NotCustomerHoverIn() {
        NotCustomer.setUnderline(true);
    }

    @FXML
    private void NotCustomerHoverOut() {
        NotCustomer.setUnderline(false);
    }


    //Login Button
    @FXML
    private void LoginClicked() throws IOException{
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CustomerMenuScreen.fxml");
    }

    @FXML
    private void LoginHoverIn() {
        buttonLogin.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void LoginHoverOut() {
        buttonLogin.setStyle("-fx-background-color: #2d9bf0;");
    }

    //Cancel Button
    @FXML
    private void CancelClicked() throws IOException {
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


    //Forgot Password
    @FXML
    public  Stage stage = new Stage();

    @FXML
    private void ForgotPasswordClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/ForgotPasswordCustomer.fxml"));
        try{
            Parent root = loader.load();
            Scene scene2 = new Scene(root);
            stage.setScene(scene2);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Forgot Password");

            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ForgotPasswordHoverIn() {
        ForgottenPassword.setUnderline(true);
    }

    @FXML
    private void ForgotPasswordHoverOut(ActionEvent e) {
        ForgottenPassword.setUnderline(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setNode(LoginElements);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();
    }

//Forgot password screen controls

        @FXML
        Button cancelButton = new Button();
        @FXML
        Button confirmButton = new Button();

        @FXML
        private void confirmHoverIn() {
            confirmButton.setStyle("-fx-background-color: #4bacf7;");
        }

        @FXML
        private void confirmHoverOut() {
            confirmButton.setStyle("-fx-background-color: #2d9bf0;");
        }

        @FXML
        private void cancelHoverIn() {
            cancelButton.setStyle("-fx-background-color: #fc4a7f;");
        }

        @FXML
        private void cancelHoverOut() {
            cancelButton.setStyle("-fx-background-color: #ed2762;");
        }

        //cancel button
        @FXML
        private void cancelButtonClicked() throws Exception {
            cancelButton.setOnAction(e -> ForgotPasswordClicked());
        }

        @FXML
        private void confirmButtonClicked() throws IOException {
            BankWiserApp app = new BankWiserApp();
            app.changeScene("NewPasswordCustomer.fxml");
        }
}

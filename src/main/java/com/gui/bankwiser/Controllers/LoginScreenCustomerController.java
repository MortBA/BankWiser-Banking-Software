package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    private void NotCustomerClicked(){
        Error_Message.setText("Too bad for you buckeroo");
    }

    @FXML
    private void NotCustomerHoverIn(){
        NotCustomer.setUnderline(true);
    }

    @FXML
    private void NotCustomerHoverOut(){
        NotCustomer.setUnderline(false);
    }





    //Login Button
    @FXML
    private void LoginClicked(){
        Error_Message.setText("not implemented yet");
    }

    @FXML
    private void LoginHoverIn(){
        buttonLogin.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void LoginHoverOut(){
        buttonLogin.setStyle("-fx-background-color: #2d9bf0;");
    }

    //Cancel Button
    @FXML
    private void CancelClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoginScreen.fxml");
    }

    @FXML
    private void CancelHoverIn(){
        buttonCancel.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void CancelHoverOut(){
        buttonCancel.setStyle("-fx-background-color: #ed2762;");
    }


    //Forgot Password
    @FXML
    private void ForgotPasswordClicked(){

    }

    @FXML
    private void ForgotPasswordHoverIn(){
        ForgottenPassword.setUnderline(true);
    }

    @FXML
    private void ForgotPasswordHoverOut() {
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
}

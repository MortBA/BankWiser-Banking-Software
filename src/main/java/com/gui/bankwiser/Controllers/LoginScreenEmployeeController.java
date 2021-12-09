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

public class LoginScreenEmployeeController implements Initializable {

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

    //Login Button
    @FXML
    private void LoginClicked() throws IOException {
       BankWiserApp app = new BankWiserApp();
       app.changeScene("EmployeeMenu.fxml");
        if(UsernameBox.getText().toString().equals("Channi")){
            buttonLogin.setStyle("-fx-background-color: #f4d3d3");
        }
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

package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.animation.FadeTransition;
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

/**
 * Controller class to perform functionality for Login screen for employee.
 *
 * @author Chanisra
 */
public class LoginScreenEmployeeController implements Initializable {

    @FXML
    private AnchorPane LoginElements;

    @FXML
    private TextField UsernameBox;

    @FXML
    private PasswordField PasswordBox;

    @FXML
    private Label Error_Message;

    @FXML
    private Label ForgottenPassword;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonCancel;

    @FXML
    public Stage stg = new Stage();

    /**
     * The method initializes transition necessary effects to display the login screen.
     * Also, initializes new stage for 'Forgot password' screen for employee.
     * The 'forgot password' screen has initModality functionality.
     *
     * @author Sejal
     * @author Chanisra
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setNode(LoginElements);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/ForgotPasswordEmployee.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Forgot Password

    /**
     * The method acts as a controller to open the initialized 'Forgot Password' screen.
     * The screen appears when the forgot password; label on Login screen is clicked.
     *
     * @throws IOException IOException
     * @author Sejal
     */
    @FXML
    private void ForgotPasswordClicked() throws IOException {
        stg.showAndWait();
    }

    //Login Button
    @FXML
    private void LoginClicked() throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("EmployeeMenu.fxml");
        // if (UsernameBox.getText().toString().equals("Channi")) {
        // buttonLogin.setStyle("-fx-background-color: #f4d3d3");
        //}
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

    @FXML
    private void ForgotPasswordHoverIn() {
        ForgottenPassword.setUnderline(true);
    }

    @FXML
    private void ForgotPasswordHoverOut() {
        ForgottenPassword.setUnderline(false);
    }
}

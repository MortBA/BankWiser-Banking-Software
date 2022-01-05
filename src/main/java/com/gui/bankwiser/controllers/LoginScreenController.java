package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class to perform all functionalities for 'Login' screen.
 *
 * @author Chanisra
 */

public class LoginScreenController implements Initializable {
    @FXML
    private AnchorPane BankwiserLogo;

    @FXML
    private Label NotCustomer;

    @FXML
    private AnchorPane LoginElements;

    @FXML
    private AnchorPane EmployeeBox;

    @FXML
    private AnchorPane CustomerBox;

    //Animation Loop

    /**
     * The method initializes necessary transition effects/animations to display the screen.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setNode(LoginElements);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();
    }

    @FXML
    private void EmployeeBoxClicked() throws IOException {
        BankWiserApp App = new BankWiserApp();
        try {
            App.changeScene("LoginScreenEmployee.fxml");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void CustomerBoxClicked() {
        BankWiserApp App = new BankWiserApp();
        try {
            App.changeScene("LoginScreenCustomer.fxml");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    //NotCustomer
    @FXML
    private void NotCustomerClicked() throws IOException {
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

}
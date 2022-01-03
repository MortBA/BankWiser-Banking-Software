package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * Controller class to perform all functionalities for Login screen for customer.
 * @author Chanisra
 */

    public class LoginScreenCustomerController implements Initializable {

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
        private Label NotCustomer;
        @FXML
        private Button buttonLogin;
        @FXML
        private Button buttonCancel;

        @FXML public Stage stg2 = new Stage();

    /**
     *
     * The method initializes transition necessary effects to display the login screen.
     * Also, initializes new stage for 'Forgot password' screen for customer.
     * The 'forgot password' screen has initModality functionality.
     * @author Sejal (stage) & Chanisra (transition)
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/ForgotPasswordCustomer.fxml"));
            try{
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stg2.setScene(scene);
                stg2.initModality(Modality.APPLICATION_MODAL);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Forgot Password

    /**
     *
     * The method acts as a controller to open the initialized 'Forgot Password' screen.
     * The screen appears when the forgot password; label on Login screen is clicked.
     * @author Sejal
     * @throws IOException
     */
    @FXML
        private void ForgotPasswordClicked() throws IOException{
            stg2.showAndWait();
        }

        //NotCustomer
        @FXML
        private void NotCustomerClicked() throws IOException{
            BankWiserApp app = new BankWiserApp();
            app.changeScene("CreateUserScreen.fxml");
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
        private void LoginClicked() throws IOException{
            BankWiserApp app = new BankWiserApp();
            app.changeScene("CustomerMenuScreen.fxml");
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

        @FXML
        private void ForgotPasswordHoverIn(){
            ForgottenPassword.setUnderline(true);
        }
        @FXML
        private void ForgotPasswordHoverOut() {
            ForgottenPassword.setUnderline(false);
    }
    }


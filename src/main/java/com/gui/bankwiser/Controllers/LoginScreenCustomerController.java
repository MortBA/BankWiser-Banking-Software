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
        private Button cancelNewPassword;

        @FXML
        private Label NotCustomer;

        @FXML
        Button cancelForgotPassButton;

        @FXML
        Button confirmForgotPassButton;

        @FXML public Stage stg2 = new Stage();

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


        //Forgot Password


       /* @FXML
        private void confirmButtonClicked() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/NewPasswordCustomer.fxml"));
            try{
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stg2.setScene(scene);
                stg2.initModality(Modality.APPLICATION_MODAL);
                stg2.setTitle("Set New Password");
                stg2.showAndWait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        */

        @FXML
        private void ForgotPasswordHoverIn(){
            ForgottenPassword.setUnderline(true);
        }
        @FXML
        private void ForgotPasswordHoverOut() { ForgottenPassword.setUnderline(false); }
        @FXML
        private void confirmHoverIn() {
            confirmForgotPassButton.setStyle("-fx-background-color: #4bacf7;");
        }
        @FXML
        private void confirmHoverOut() {
            confirmForgotPassButton.setStyle("-fx-background-color: #2d9bf0;");
        }
        @FXML
        private void cancelHoverIn() {
            cancelForgotPassButton.setStyle("-fx-background-color: #fc4a7f;");
        }
        @FXML
        private void cancelHoverOut() {
            cancelForgotPassButton.setStyle("-fx-background-color: #ed2762;");
        }


        @FXML
        private void onConfirmNewPasswordClicked() throws IOException {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Your password is updated successfully.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                // probably not implement anything here
            }
        }

        @FXML
        public void handleCloseForgotPasswordAction(ActionEvent event) {
            Stage stage = (Stage) cancelForgotPassButton.getScene().getWindow();
            stage.close();
        }

        @FXML
        public void handleCloseNewPasswordAction(ActionEvent event) {
            Stage stage = (Stage) cancelNewPassword.getScene().getWindow();
            stage.close();
        }



    }


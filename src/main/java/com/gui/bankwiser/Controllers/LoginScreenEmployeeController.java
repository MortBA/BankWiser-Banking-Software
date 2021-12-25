package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    @FXML
    private Button confirmForgotPassButton;

    @FXML
    private Button cancelForgotPassButton;

    @FXML
    private Button confirmNewPassword;

    @FXML
    private Button cancelNewPassword;

    //Login Button
    @FXML
    private void LoginClicked() throws IOException {
       BankWiserApp app = new BankWiserApp();
       app.changeScene("EmployeeMenu.fxml");
       // if(UsernameBox.getText().toString().equals("Channi")){
           // buttonLogin.setStyle("-fx-background-color: #f4d3d3");
       // }
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
    @FXML public Stage stg = new Stage();

    /**
     * Opens forgot password window as a new stage when 'forgot password' label is clicked
     * The label is present in employee menu login screen
     * The screen has initModality functionality
     */
    @FXML
    private void ForgotPasswordClicked(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/ForgotPasswordEmployee.fxml"));
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void ForgotPasswordHoverIn(){
        ForgottenPassword.setUnderline(true);
    }
    @FXML
    private void ForgotPasswordHoverOut() {
        ForgottenPassword.setUnderline(false);
    }
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

    /**
     * Opens 'New Password' window as a new stage when confirm button from forgot password screen is clicked.
     * The stage has initModality functionality.
     *
     */
    @FXML
    private void confirmButtonClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/NewPasswordEmployee.fxml"));
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setTitle("New Password");
            stg.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Displays alert confirmation box as a confirmation for a successful password change.
     * The alert box has OK and cancel button
     * @throws IOException
     */
    @FXML
    private void onConfirmNewPasswordClicked() throws IOException {
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setContentText("Your password is updated successfully.");
        Optional<ButtonType> result = alertBox.showAndWait();
        if (result.get() == ButtonType.OK) {
            // probably not implement anything here
        }
    }

    /**
     * Used to close forgot password stage (window) using the button called in the method.
     * @param event
     */
    @FXML
    public void handleCloseForgotPasswordAction(ActionEvent event) {
        Stage stage = (Stage) cancelForgotPassButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Used to close new password stage (window) using the button called in the method.
     * @param event
     */
    @FXML
    public void handleCloseNewPasswordAction(ActionEvent event) {
        Stage stage = (Stage) cancelNewPassword.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void handleCloseNewPasswordAfterAction(ActionEvent event) {
        Stage stage = (Stage) confirmNewPassword.getScene().getWindow();
        stage.close();
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

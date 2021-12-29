package com.gui.bankwiser.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class ForgotPasswordCustomerController {

    @FXML
    private Button confirmForgotPassButton;

    @FXML
    private Button cancelForgotPassButton;

    @FXML
    private TextField ssNumber;

    @FXML
    private TextField email;

    @FXML
    private TextField retypeEmail;


    @FXML
    public Stage stg = new Stage();

    /**
     * Here, used to initialize new stage have initModality function.
     * The new stage is used to fill new password for the customer.
     */
    @FXML
    public void initialize() {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/NewPasswordCustomer.fxml"));
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Functions as a controller method for initialize.
     * Opens a new window to fill new password for the customer when confirm button of forgot password window is clicked.
     * The window has initModality functionality.
     * @throws IOException
     */
    @FXML
    private void confirmButtonClicked() throws IOException {
       stg.showAndWait();
    }

    /**
     * Closes the forgot password window when cancel button is clicked.
     * @param event
     */
    @FXML
    public void handleCloseForgotPasswordAction(ActionEvent event) {
        Stage stage = (Stage) cancelForgotPassButton.getScene().getWindow();
        stage.close();
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


}

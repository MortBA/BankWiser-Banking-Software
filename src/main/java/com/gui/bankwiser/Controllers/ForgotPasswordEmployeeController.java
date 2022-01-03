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

/**
 *
 * Controller class to perform all functionalities for 'forgot password' screen for employee.
 * @author Sejal
 */

public class ForgotPasswordEmployeeController {

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

    @FXML public Stage stg = new Stage();

    /**
     *
     * Initializes 'new password' screen for employee as a new stage when 'forgot password' (this) screen appears.
     * The stage has initModality functionality.
     */
    @FXML
    private void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/NewPasswordEmployee.fxml"));
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
     *
     * Functions as a controller method to display 'new password' screen.
     * The 'new password' screen opens when confirm button on forgot password window is clicked.
     * @throws IOException
     */
    @FXML
    private void confirmButtonClicked() throws IOException {
        stg.showAndWait();
    }

    /**
     *
     * Closes the forgot password window when the cancel button on 'forgot password' screen is clicked.
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

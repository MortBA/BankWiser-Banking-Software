package com.gui.bankwiser.Controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreateAccountScreenUser {

    @FXML
    private MenuItem accountSettings;

    @FXML
    private MenuItem deleteBankAccount;

    @FXML
    private MenuItem deleteUserAccount;

    @FXML
    private MenuItem loans;

    @FXML
    private MenuItem myCards;

    @FXML
    private Button overview;

    @FXML
    private Button buttonLogOut;

    @FXML
    void transferMoney (ActionEvent event) throws IOException {
        //new BankWiserApp().changeScene("TransferMoney.fxml");
        //}
        BankWiserApp app = new BankWiserApp();
        app.changeScene("TransferMoney.fxml");
    }

    @FXML
    private MenuItem transactionsHistory;

    @FXML
    void onDeleteUserAccountClicked(ActionEvent event) {

    }

    @FXML
    void onMyCardsClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenuController.fxml");
    }

    @FXML
    void onOverviewClicked(ActionEvent event) {

    }

    @FXML
    public void onLogOutClicked() throws IOException{
        new BankWiserApp().changeScene("LoginScreenCustomer.fxml");
    }


    @FXML
    private AnchorPane BankwiserLogo;

    @FXML
    private AnchorPane LoginElements;

    @FXML
    private ImageView QuestionMarkButton;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonLogin;

    //QuestionMarkButton // todo:  implement if time over
    @FXML
    private void QuestionMarkHoverIn(){

    }

    @FXML
    private void QuestionMarkHoverOut(){

    }



    @FXML
    void CancelClicked(MouseEvent event) throws IOException {
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
    void LoginClicked(MouseEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene(".fxml");
        // TODO: 2021-12-21 Make A Screen "Account Successfully registered. 
    }


    @FXML
    private void LoginHoverIn(){
        buttonLogin.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void LoginHoverOut(){
        buttonLogin.setStyle("-fx-background-color: #2d9bf0;");
    }


}

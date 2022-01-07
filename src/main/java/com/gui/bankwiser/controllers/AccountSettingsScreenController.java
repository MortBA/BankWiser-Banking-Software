package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for performing all functionalities of Account settings screen.
 */
public class AccountSettingsScreenController {


    Facade facade = Facade.getInstance();

    @FXML
    public MenuItem transactionHistory;
    @FXML
    public TextField firstName;
    @FXML
    public TextField surName;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField email;
    @FXML
    public TextField address2;
    @FXML
    public TextField address1;
    @FXML
    public Label socialSecurityNum;
    @FXML
    public Label setFirstName;
    @FXML
    public Label setSurname;
    @FXML
    public Label setPhoneNumber;
    @FXML
    public Label setEmail;
    @FXML
    public Label setAddress1;
    @FXML
    public Label setAddress2;
    @FXML
    private MenuItem accountSettings;
    @FXML
    private MenuItem deleteBankAccount;
    @FXML
    private MenuItem deleteUserAccount;
    @FXML
    private MenuItem transactionsHistory;
    @FXML
    private MenuItem loans;
    @FXML
    private MenuItem myCards;
    @FXML
    private MenuItem transferMoney;
    @FXML
    public Button cancelSettings;
    @FXML
    public Button submitChanges;
    @FXML
    private Button overview;
    @FXML
    private Button buttonLogOut;
    @FXML
    private Button changePassword;

    @FXML
    private AnchorPane BankwiserLogo;
    @FXML
    private AnchorPane LoginElements;

    @FXML
    private ImageView QuestionMarkButton;

    @FXML
    private final Stage stg = new Stage();

    /**
     * Initializes new stage for 'change password' screen.
     * The stage is initialized when the account settings screen appears.
     * The stage has initModality functionality.
     */
    @FXML
    public void initialize() {

        updateScreenInformation(facade.getActiveUser());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/DeleteAccountScreenUserPopup.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loader = new FXMLLoader(getClass().getResource("/com/gui/bankwiser/DeleteBankAccountScreenPopup.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stg.setScene(scene);
            stg.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onOverviewClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CustomerMenuScreen.fxml");
    }

    @FXML
    public void onLogOutClicked() throws IOException {
        new BankWiserApp().changeScene("LoginScreenCustomer.fxml");
    }

    @FXML
    void onAccountSettingsClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("AccountSettingsScreen.fxml");
    }

    @FXML
    void onMyCardsClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("BankCardMenu.fxml");
    }


    @FXML
    void onTransferMoneyClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("TransferMoneyScreen.fxml");
    }


    @FXML
    void onTransactionHistoryClicked() throws Exception {
        new BankWiserApp().changeScene("TransactionHistoryScreen.fxml");
    }

    @FXML
    void onLoansClicked() throws Exception {
        new BankWiserApp().changeScene("LoansOverview.fxml");
    }

    @FXML
    void onDeleteBankAccountClicked() throws Exception {
        stg.showAndWait();
    }

    @FXML
    void onDeleteUserAccountClicked() throws Exception {
        stg.showAndWait();
    }


    /**
     * Opens the 'change password' screen, which is initialized in the beginning.
     * The screen opens only when 'change password' button on the account settings screen is clicked.
     *
     * @throws Exception
     */
    @FXML
    void onChangePasswordClicked() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("We have sent a link to change password on your email.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        }
    }

    @FXML
    void updateScreenInformation(UserAccount userAccount){
        setPhoneNumber.setText(userAccount.getPhoneNumber());
        setAddress1.setText(userAccount.getAddress());
        setEmail.setText(userAccount.getEmailID());
        setFirstName.setText(userAccount.getFullName());
        socialSecurityNum.setText(userAccount.getSocialSecurityNum());
    }

    public void submitChangesClicked() throws Exception {
        if (phoneNumber.getText().trim().isEmpty() || address1.getText().trim().isEmpty() || firstName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            updateUserInformation(facade.getActiveUser());
            new BankWiserApp().changeScene("AccountSettingsScreen.fxml");
        }
    }

    @FXML
    public void updateUserInformation(UserAccount userAccount){
        userAccount.setPhoneNumber(phoneNumber.getText());
        userAccount.setAddress(address1.getText());
        userAccount.setFullName(firstName.getText());
    }
}

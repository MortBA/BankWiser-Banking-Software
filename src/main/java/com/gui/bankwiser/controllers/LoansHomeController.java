package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoansHomeController {

    Facade facade = Facade.getInstance();

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
    private Button overview;
    @FXML
    private Button buttonLogOut;
    @FXML
    private Button cancel;
    @FXML
    private Button confirm;

    @FXML
    private Label overviewLoan;
    @FXML
    private Label applyPersonalLoan;
    @FXML
    private Label applyHomeLoan;
    @FXML
    private Label applyVehicleLoan;
    @FXML
    public TextField loanAmount;
    @FXML
    private TextField totalMonthlyIncome;
    @FXML
    private TextField totalMonthlyExpense;
    @FXML
    private TextField durationOfLoan;
    @FXML
    private TextField propertyType;
    @FXML
    private TextField propertySize;
    @FXML
    private TextField address;
    @FXML
    private TextField propertyFloor;

    @FXML
    public Stage stg = new Stage();
    @FXML
    public Stage stg2 = new Stage();

    /**
     * Initializes new stages to delete user account and bank account.
     * Both stages have initModality functionality.
     *
     * @author Sejal
     */
    @FXML
    private void initialize() {
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
    public void onLogOutClicked(ActionEvent event) throws IOException {
        new BankWiserApp().changeScene("LoginScreenCustomer.fxml");
    }

    @FXML
    void onOverviewClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("CustomerMenuScreen.fxml");
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

    public void onApplyPersonalLoanClicked(MouseEvent mouseEvent) throws Exception {
        new BankWiserApp().changeScene("LoansPersonal.fxml");
    }

    public void onApplyHomeLoanClicked(MouseEvent mouseEvent) throws Exception {
        new BankWiserApp().changeScene("LoansHome.fxml");
    }

    public void onOverviewLoanClicked(MouseEvent mouseEvent) throws Exception {
        new BankWiserApp().changeScene("LoansOverview.fxml");
    }

    public void onApplyVechileLoanClicked(MouseEvent mouseEvent) throws Exception {
        new BankWiserApp().changeScene("LoansVehicle.fxml");
    }

    @FXML
    public void onDeleteUserAccountClicked() throws IOException {
        stg.showAndWait();
    }

    /**
     * Opens new stage to delete the bank account when 'delete bank account' option in customer menu screen is clicked.
     *
     * @throws IOException IOException
     */
    @FXML
    public void onDeleteBankAccountClicked() throws IOException {
        stg.showAndWait();
    }

    @FXML
    public void onOverviewLoanHoverIn() {
        overviewLoan.setUnderline(true);
    }

    @FXML
    public void onOverviewLoanHoverOut() {
        overviewLoan.setUnderline(false);
    }

    @FXML
    public void onApplyPersonalLoanHoverIn() {
        applyPersonalLoan.setUnderline(true);
    }

    @FXML
    public void onApplyPersonalLoanHoverOut() {
        applyPersonalLoan.setUnderline(false);
    }

    @FXML
    public void onApplyHomeLoanHoverIn() {
        applyHomeLoan.setUnderline(true);
    }

    @FXML
    public void onApplyHomeLoanHoverOut() {
        applyHomeLoan.setUnderline(false);
    }

    @FXML
    public void onApplyVehicleLoanHoverIn() {
        applyVehicleLoan.setUnderline(true);
    }

    @FXML
    public void onApplyVehicleLoanOut() {
        applyVehicleLoan.setUnderline(false);
    }


    public void onConfirmClicked(ActionEvent event) throws IOException {
        if (totalMonthlyExpense.getText().trim().isEmpty() || totalMonthlyIncome.getText().trim().isEmpty() ||
                loanAmount.getText().trim().isEmpty() || durationOfLoan.getText().trim().isEmpty() ||
                propertyFloor.getText().trim().isEmpty() || propertySize.getText().trim().isEmpty() ||
                propertyType.getText().trim().isEmpty() || address.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the required fields.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }
        else {
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setContentText("Loan application accepted.");
            Optional<ButtonType> result = alertBox.showAndWait();
            if (result.get() == ButtonType.OK) {
                BankWiserApp app = new BankWiserApp();
                facade.homeLoanApplication(Double.parseDouble(totalMonthlyIncome.getText()),
                        Double.parseDouble(totalMonthlyExpense.getText()),
                        Double.parseDouble(loanAmount.getText()), Integer.parseInt(durationOfLoan.getText()),
                        Double.parseDouble(propertySize.getText()),
                        address.getText(), propertyType.getText(), Integer.parseInt(propertyFloor.getText()));
                app.changeScene("LoansOverview.fxml");
            }
        }
    }

    public void onCancelClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoansHome.fxml");
    }
}

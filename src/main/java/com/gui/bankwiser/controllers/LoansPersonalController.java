package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import com.logic.bankwiser.facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoansPersonalController {

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
    private TextField totalMonthlyIncome;
    @FXML
    private TextField totalMonthlyExpense;
    @FXML
    private TextField loanAmount;
    @FXML
    private TextField durationOfLoan;
    @FXML
    private TextField personalLoanNote;

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
            stg2.setScene(scene);
            stg2.initModality(Modality.APPLICATION_MODAL);
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

    public void onApplyVehicleLoanClicked(MouseEvent mouseEvent) throws Exception {
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
        stg2.showAndWait();
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


    public void onConfirmClicked(ActionEvent event) {

        facade.personalLoanApplication(Double.parseDouble(totalMonthlyIncome.getText()), Double.parseDouble(totalMonthlyExpense.getText()),
        Double.parseDouble(loanAmount.getText()), Integer.parseInt(durationOfLoan.getText()), personalLoanNote.getText());

    }

    public void onCancelClicked(ActionEvent event) throws IOException {
        BankWiserApp app = new BankWiserApp();
        app.changeScene("LoansPersonal.fxml");
    }

    @FXML
    private void confirmHoverIn() {
        confirm.setStyle("-fx-background-color: #4bacf7;");
    }

    @FXML
    private void confirmHoverOut() {
        confirm.setStyle("-fx-background-color: #2d9bf0;");
    }

    @FXML
    private void cancelHoverIn() {
        cancel.setStyle("-fx-background-color: #fc4a7f;");
    }

    @FXML
    private void cancelHoverOut() {
        cancel.setStyle("-fx-background-color: #ed2762;");
    }

}

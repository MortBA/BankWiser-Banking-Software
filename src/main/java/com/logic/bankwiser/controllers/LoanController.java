package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.loans.HomeLoan;
import com.logic.bankwiser.loans.Loan;
import com.logic.bankwiser.loans.PersonalLoan;
import com.logic.bankwiser.loans.VehicleLoan;
import com.logic.bankwiser.storage.Storage;
import com.logic.bankwiser.utils.Input;
import com.logic.bankwiser.utils.MathUtils;
import javafx.util.Pair;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Controller class responsible for loans.
 *
 * @author Burak Askan
 * @author Dragos Florinel Isar
 * @author Mathias Hallander
 */
public class LoanController {

    private final TransactionController transactionController;
    private final Storage storage;

    final int YEAR_MONTHS = 12;
    final double LOAN_MODIFIER = 4.5;

    public LoanController(Storage storage, TransactionController transactionController) {
        this.storage = storage;
        this.transactionController = transactionController;
    }

    /**
     * Application for a personal loan by bank user
     *
     * @param monthlyIncome     the monthly income of the user
     * @param monthlyExpenses   the total monthly expenses of the user
     * @param desiredLoanAmount the requested loan amount in SEK
     * @param loanDuration      the requested loan duration in months
     * @param personalReasons   the user's personal reasons for taking a personal loan
     * @return String confirming refusal or approval of loan. Loan information.
     */
    public String personalLoanApplication(UserAccount userAccount, String bankAccountID, double monthlyIncome, double monthlyExpenses,
                                          double desiredLoanAmount, int loanDuration, String personalReasons) {
        BankAccount bankAccount = storage.getBankAccount(bankAccountID);
        Pair<Boolean, String> loanApplication = verifyPersonalLoan(bankAccount, monthlyIncome, monthlyExpenses, desiredLoanAmount, loanDuration);
        if (loanApplication.getKey()) {
            createPersonalLoan(storage.getBankAccount(bankAccountID), desiredLoanAmount, loanDuration, personalReasons);
            return loanApplication.getValue() + Input.EOL +
                    "Applicant: " + userAccount.getFullName() + Input.EOL +
                    "Loan amount: " + desiredLoanAmount + " SEK" + Input.EOL +
                    "Income: " + monthlyIncome + " SEK/mo" + Input.EOL +
                    "Expenses: " + monthlyExpenses + " SEK/mo" + Input.EOL +
                    "Duration of the loan: " + loanDuration + " months";
        } else {
            return loanApplication.getValue();
        }
    }


    /**
     * Application for a vehicle loan by user
     *
     * @param monthlyIncome     the monthly income of the user
     * @param monthlyExpenses   the total monthly expenses of the user
     * @param desiredLoanAmount the requested loan amount in SEK
     * @param loanDuration      the requested loan duration in months
     * @param typeOfCar         the type of car for which the loan is requested
     * @param typeOfFuel        the fuel of car for which the loan is requested
     * @param mileage           the mileage of car for which the laon is requested
     * @param manufactureYear   the manufacturing year of car for which the loan is requested
     * @return String confirming refusal or approval of loan. Loan information.
     */
    public String vehicleLoanApplication(UserAccount userAccount, String bankAccountID, double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, int loanDuration,
                                         String typeOfCar, String typeOfFuel, double mileage, int manufactureYear) {
        BankAccount bankAccount = storage.getBankAccount(bankAccountID);
        Pair<Boolean, String> loanApplication = verifyVehicleLoan(bankAccount, monthlyIncome, monthlyExpenses, desiredLoanAmount, loanDuration);
        if (loanApplication.getKey()) {
            createVehicleLoan(storage.getBankAccount(bankAccountID), desiredLoanAmount, loanDuration, typeOfFuel, mileage, manufactureYear);
            return loanApplication.getValue() + Input.EOL +
                    "Applicant: " + userAccount.getFullName() + Input.EOL +
                    "Loan amount: " + desiredLoanAmount + Input.EOL +
                    "Income: " + monthlyIncome + " SEK/mo" + Input.EOL +
                    "Expenses: " + monthlyExpenses + " SEK/mo" + Input.EOL +
                    "Manufacturer & Model: " + typeOfCar + Input.EOL +
                    "Millage: " + mileage + " km" + Input.EOL +
                    "Year manufactured: " + manufactureYear + Input.EOL +
                    "Duration of the loan: " + loanDuration + " months";
        } else {
            return loanApplication.getValue();
        }
    }


    /**
     * Application for a home loan by user
     *
     * @param monthlyIncome     the monthly income of the user
     * @param monthlyExpenses   the total monthly expenses of the user
     * @param desiredLoanAmount the requested loan amount in SEK
     * @param loanDuration      the requested loan duration in months
     * @param propertyAddress   the property address
     * @param propertyType      the property type
     * @param propertySize      the size of the property in square meters
     * @param propertyFloor     the floor on which the property is located
     * @return String confirming refusal or approval of loan. Loan information.
     */
    public String homeLoanApplication(UserAccount userAccount, String bankAccountID, double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, int loanDuration,
                                      String propertyAddress, String propertyType, double propertySize, int propertyFloor) {
        BankAccount bankAccount = storage.getBankAccount(bankAccountID);
        Pair<Boolean, String> loanApplication = verifyHomeLoan(bankAccount, monthlyIncome, monthlyExpenses, desiredLoanAmount, loanDuration);

        if (loanApplication.getKey()) {
            createHomeLoan(storage.getBankAccount(bankAccountID), desiredLoanAmount, loanDuration, propertyAddress, propertyType, propertySize, propertyFloor);
            return loanApplication.getValue() + Input.EOL +
                    "Applicant: " + userAccount.getFullName() + Input.EOL +
                    "Loan amount: " + desiredLoanAmount + Input.EOL +
                    "Income: " + monthlyIncome + " SEK/mo" + Input.EOL +
                    "Expenses: " + monthlyExpenses + " SEK/mo" + Input.EOL +
                    "Home address: " + propertyAddress + Input.EOL +
                    "Type of home: " + propertyType + Input.EOL +
                    "Property size: " + propertySize + " m^2" + Input.EOL +
                    "Amount of stories: " + propertySize + Input.EOL +
                    "Duration of the loan: " + loanDuration + " months";
        } else {
            return loanApplication.getValue();
        }
    }


    /**
     * Assesses a basic form of loan eligibility by comparing the amount to be loaned against
     * a couple parameters to ensure a basic amount of guaranteed balance on tha account and some income.
     *
     * @return Boolean success or fail
     */
    public boolean assessLoanEligibility(double balance, double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, double loanDuration) {
        return (balance + ((monthlyIncome - monthlyExpenses) * loanDuration)) >= (desiredLoanAmount / 10 + (desiredLoanAmount * 0.02 * loanDuration));
    }

    /**
     * Accepts several parameters to calculate loan eligibility
     *
     * @return "Pair&#60;Boolean, String&#62;" of success/fail and requisite message.
     */
    public Pair<Boolean, String> verifyPersonalLoan(BankAccount bankAccount, double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, int loanDuration) {
        final double MIN_RANGE_PERSONAL = 416;
        final double MAX_RANGE_PERSONAL = 8333;

        if (assessLoanEligibility(bankAccount.getBalance().doubleValue(), monthlyIncome, monthlyExpenses, desiredLoanAmount, loanDuration)) {
            double maxPersonalLoanAmount = (monthlyIncome * YEAR_MONTHS * LOAN_MODIFIER);
            if (desiredLoanAmount >= MIN_RANGE_PERSONAL * loanDuration) {
                if (desiredLoanAmount <= (MAX_RANGE_PERSONAL * loanDuration) && desiredLoanAmount <= maxPersonalLoanAmount) {
                    return new Pair<>(true, "Your loan request has been accepted."); //all is good?
                } else {
                    return new Pair<>(false, "Your loan request was denied. You are eligible for a loan of maximum " + maxPersonalLoanAmount);//loan above maximum
                }
            } else {
                return new Pair<>(false, "Your loan request was denied. You must borrow at least " + (MIN_RANGE_PERSONAL * loanDuration) + " for this duration.");//loan below minimum
            }
        } else {
            return new Pair<>(false, "Your loan request was denied. Your income cannot support repaying a loan.");//income cannot support loan
        }
    }

    /**
     * Accepts several parameters to calculate loan eligibility
     *
     * @return "Pair&#60;Boolean, String&#62;" of success/fail and requisite message.
     */
    public Pair<Boolean, String> verifyHomeLoan(BankAccount bankAccount, double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, int loanDuration) {
        final double MIN_RANGE_HOME = 8333;
        final double MAX_RANGE_HOME = 500000;

        if (assessLoanEligibility(bankAccount.getBalance().doubleValue(), monthlyIncome, monthlyExpenses, desiredLoanAmount, loanDuration)) {
            double maxPersonalLoanAmount = (monthlyIncome * YEAR_MONTHS * LOAN_MODIFIER);
            if (desiredLoanAmount >= MIN_RANGE_HOME * loanDuration) {
                if (desiredLoanAmount <= (MAX_RANGE_HOME * loanDuration) && desiredLoanAmount <= maxPersonalLoanAmount) {
                    return new Pair<>(true, "Your loan request has been accepted."); //all is good?
                } else {
                    return new Pair<>(false, "Your loan request was denied. You are eligible for a loan of maximum " + maxPersonalLoanAmount);//loan above maximum
                }
            } else {
                return new Pair<>(false, "Your loan request was denied. You must borrow at least " + (MIN_RANGE_HOME * loanDuration) + " for this duration.");//loan below minimum
            }
        } else {
            return new Pair<>(false, "Your loan request was denied. Your income cannot support repaying a loan.");//income cannot support loan
        }
    }

    /**
     * Accepts several parameters to calculate loan eligibility
     *
     * @return "Pair&#60;Boolean, String&#62;" of success/fail and requisite message
     */
    public Pair<Boolean, String> verifyVehicleLoan(BankAccount bankAccount, double monthlyIncome, double monthlyExpenses, double desiredLoanAmount, int loanDuration) {
        final double MIN_RANGE_VEHICLE = 1666;
        final double MAX_RANGE_VEHICLE = 50000;

        if (assessLoanEligibility(bankAccount.getBalance().doubleValue(), monthlyIncome, monthlyExpenses, desiredLoanAmount, loanDuration)) {
            double maxPersonalLoanAmount = (monthlyIncome * YEAR_MONTHS * LOAN_MODIFIER);
            if (desiredLoanAmount >= MIN_RANGE_VEHICLE * loanDuration) {
                if (desiredLoanAmount <= (MAX_RANGE_VEHICLE * loanDuration) && desiredLoanAmount <= maxPersonalLoanAmount) {
                    return new Pair<>(true, "Your loan request has been accepted."); //all is good?
                } else {
                    return new Pair<>(false, "Your loan request was denied. You are eligible for a loan of maximum " + maxPersonalLoanAmount);//loan above maximum
                }
            } else {
                return new Pair<>(false, "Your loan request was denied. You must borrow at least " + (MIN_RANGE_VEHICLE * loanDuration) + " for this duration.");//loan below minimum
            }
        } else {
            return new Pair<>(false, "Your loan request was denied. Your income cannot support repaying a loan.");//income cannot support loan
        }
    }

    /**
     * Loan repayment method that checks the existing loan amounts and calculates the monthly repayment rate,
     * which it uses to create a payment transaction.
     *
     * @return String confirmation of success or failure
     */
    public String loanRepayment(UserAccount activeUser) {
        for (String bankAccountID : activeUser.getBankAccountList()) {
            BankAccount bankAccount = storage.getBankAccount(bankAccountID);

            for (Loan loans : bankAccount.getLoanMap().values()) {
                LocalDateTime yearlyPaymentDate = loans.getLastRepaymentDate();

                if (ChronoUnit.DAYS.between(yearlyPaymentDate.plusMonths(1), yearlyPaymentDate) == 0 ||
                        ChronoUnit.DAYS.between(yearlyPaymentDate.plusYears(1), yearlyPaymentDate) < 0) {

                    double totalSpend = ((loans.getLoanAmount() * (loans.getInterestRate()) * -1) / loans.getLoanDuration());
                    BigDecimal moneyTransferred = BigDecimal.valueOf(totalSpend);

                    if (moneyTransferred.compareTo(bankAccount.getBalance()) > 0) {
                        return "You are unable to pay your loans. The bank will be in contact shortly.";
                    }
                    String paymentNote = "Payment on your loan";
                    transactionController.transferMoney(bankAccount.getBankAccountID(), "0", moneyTransferred, paymentNote, LocalDateTime.now());

                    if ((loans.getCreationDate().getMonthValue() + loans.getLoanDuration()) == LocalDate.now().getMonthValue()) {
                        bankAccount.getLoanMap().remove(loans.getLoanID());
                        return "Your loan has been fully repaid.";
                    }
                    loans.setLastRepaymentDate(LocalDateTime.now());
                }
            }
        }
        return "";
    }

    public void createHomeLoan(BankAccount bankAccount, double loanAmount, int loanDuration, String propertyAddress, String propertyType, double propertySize, int propertyFloors) {
        HomeLoan homeLoan = new HomeLoan(bankAccount, generateLoanID(bankAccount), loanAmount, loanDuration, propertyAddress, propertyType, propertySize, propertyFloors);
        bankAccount.addLoan(homeLoan);
        try {
            storage.storeHomeLoans(homeLoan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPersonalLoan(BankAccount bankAccount, double loanAmount, int loanDuration, String personalReasons) {
        PersonalLoan personalLoan = new PersonalLoan(bankAccount, generateLoanID(bankAccount), loanAmount, loanDuration, personalReasons);
        bankAccount.addLoan(personalLoan);
        try {
            storage.storePersonalLoans(personalLoan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createVehicleLoan(BankAccount bankAccount, double loanAmount, int loanDuration, String typeOfFuel, double mileage, int manufactureYear) {
        VehicleLoan vehicleLoan = new VehicleLoan(bankAccount, generateLoanID(bankAccount), loanAmount, loanDuration, typeOfFuel, mileage, manufactureYear);
        bankAccount.addLoan(vehicleLoan);
        try {
            storage.storeVehicleLoans(vehicleLoan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Acquires BankAccount from method to guarantee unique id generation
     *
     * @return guaranteed unique id
     */
    public String generateLoanID(BankAccount bankAccount) {
        StringBuilder stringBuilder = new StringBuilder();

        bankAccount.getLoanMap().values().forEach((Loan loan) -> stringBuilder.append(loan.getLoanID()).append(','));

        return MathUtils.generateUniqueID(stringBuilder.toString());
    }
}


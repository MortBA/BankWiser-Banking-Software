package com.logic.bankwiser.controllers;

import com.logic.bankwiser.accounts.UserAccount;
import com.logic.bankwiser.bank_accounts.BankAccount;
import com.logic.bankwiser.loans.*;
import com.logic.bankwiser.storage.Storage;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.logic.bankwiser.utils.MathUtils;

public class LoanController {

    private int loanDuration; // value in years. input from the user
    private boolean eligibility;
    private double remainingIncome;
    private double desiredLoanAmount; // value in SEK. input from the user
    private double acceptedLoanAmount = 0.0;
    private final String ln = System.lineSeparator();
    private final double MIN_RANGE_PERSONAL = 5000/12;
    private final double MAX_RANGE_PERSONAL = 100000/12;
    private final double MIN_RANGE_HOME = 100000/12;
    private final double MAX_RANGE_HOME = 6000000/12;
    private final double MIN_RANGE_VEHICLE = 20000/12;
    private final double MAX_RANGE_VEHICLE = 600000/12;
    private double maxPersLoanAmount;
    private double maxHomeLoanAmount;
    private double maxVehicleLoanAmount;
    private String loanEligibility = "";
    private String loanCreationStatus;
    private UserAccount user;

    private final TransactionController transactionController;
    private final Storage storage;

    public LoanController(Storage storage, TransactionController transactionController) {
        this.storage = storage;
        this.transactionController = transactionController;
    }

    /**
     * Application for a personal loan by bank user
     *
     * @param monthlyIncome         the monthly income of the user
     * @param monthlyExpenses       the total monthly expenses of the user
     * @param liabilities           the existing amount of loans of user
     * @param desiredLoanAmount     the requested loan amount
     * @param loanDuration          the requested loan duration
     * @param user
     * @param personalReasons       the user's personal reasons for taking a personal loan
     * @return string confirming refusal or approval of loan. Loan information.
     */

    public String applyPersonalLoan(String bankAccountID, double monthlyIncome, double monthlyExpenses, double liabilities,
                                    double desiredLoanAmount, int loanDuration, UserAccount user, String personalReasons) {
        this.user = user;

        Pair<Boolean, String> validityCheckMonthlyIncome = checkValidDouble(monthlyIncome);
        Pair<Boolean, String> validityCheckMonthlyExpenses = checkValidDouble(monthlyExpenses);
        Pair<Boolean, String> validityCheckMonthlyLiabilities = checkValidDouble(liabilities);
        Pair<Boolean, String> validityCheckDesiredLoanAmount = checkValidDouble(desiredLoanAmount);

        if (!validityCheckMonthlyIncome.getKey() && validityCheckMonthlyExpenses.getKey() && validityCheckMonthlyLiabilities.getKey() && validityCheckDesiredLoanAmount.getKey()) {
            return "Invalid input: Please don’t enter negative numbers.";
        }

        Pair<Boolean, String> validityCheckInt = checkValidInt(loanDuration);
        if (!validityCheckInt.getKey()) {
            return validityCheckInt.getValue();
        }

        Pair<Boolean, String> validityCheckString = checkValidString(personalReasons);
        if (!validityCheckString.getKey()) {
            return validityCheckString.getValue();
        }
        calculatePersonalLoanAmount(monthlyIncome, monthlyExpenses, liabilities, desiredLoanAmount, loanDuration);

        /**
         * Calling the method responsible for the creation of a Personal Loan. However, here we use the
         * acceptedLoanAmount attribute in the constructor in order to create a loan object with the approved
         * amount.
         */

        createPersonalLoan(storage.getBankAccount(bankAccountID), acceptedLoanAmount, loanDuration, personalReasons);

        String resultString = loanCreationStatus + ln +
                " Applicant: " + user.getFullName() + ln +
                " Income: " + monthlyIncome + " SEK/mo" + ln +
                " Expenses: " + monthlyExpenses + " SEK/mo" + ln +
                " Liabilities & dependencies: " + liabilities + " SEK" + ln +
                " Duration of the loan: " + loanDuration + " years";

        return resultString;
    }

    public String applyVehicleLoan(String bankAccountID, double monthlyIncome, double monthlyExpenses, double liabilities, double desiredLoanAmount, int loanDuration,
                                   UserAccount user, String typeOfCar, String typeOfFuel, double mileage, int manufactureYear) {

        this.user = user;
        Pair<Boolean, String> validityCheckMonthlyIncome = checkValidDouble(monthlyIncome);
        Pair<Boolean, String> validityCheckMonthlyExpenses = checkValidDouble(monthlyExpenses);
        Pair<Boolean, String> validityCheckMonthlyLiabilities = checkValidDouble(liabilities);
        Pair<Boolean, String> validityCheckMonthlyMileage = checkValidDouble(mileage);
        Pair<Boolean, String> validityCheckDesiredLoanAmount = checkValidDouble(desiredLoanAmount);

        if (!validityCheckMonthlyIncome.getKey() && validityCheckMonthlyExpenses.getKey() && validityCheckMonthlyLiabilities.getKey()
                && validityCheckMonthlyMileage.getKey() && validityCheckDesiredLoanAmount.getKey()) {
            return "Invalid input: Please don’t enter negative numbers.";
        }

        Pair<Boolean, String> validityCheckLoanDuration = checkValidInt(loanDuration);
        Pair<Boolean, String> validityCheckManufactureYear = checkValidInt(manufactureYear);
        if (!validityCheckLoanDuration.getKey() && !validityCheckManufactureYear.getKey()) {
            return "Invalid input: Please don’t enter negative numbers.";
        }

        Pair<Boolean, String> validityCheckString = checkValidString(typeOfFuel);
        if (!validityCheckString.getKey()) {
            return validityCheckString.getValue();
        }

        calculateVehicleLoanAmount(monthlyIncome, monthlyExpenses, liabilities, desiredLoanAmount, loanDuration);
        createVehicleLoan(storage.getBankAccount(bankAccountID), acceptedLoanAmount, loanDuration, typeOfFuel, mileage, manufactureYear);

        String resultString = loanCreationStatus + ln +
                " Car loan application submitted:" + ln +
                " Applicant: " + user.getFullName() + ln +
                " Income: " + monthlyIncome + " SEK/mo" + ln +
                " Expenses: " + monthlyExpenses + " SEK/mo" + ln +
                " Liabilities & dependencies: " + liabilities + " SEK" + ln +
                " Manufacturer & Model: " + typeOfCar + ln +
                " Millage: " + mileage + " km" + ln +
                " Year manufactured: " + manufactureYear + "";

        return resultString;
    }

    public String applyHomeLoan(String bankAccountID, double monthlyIncome, double monthlyExpenses, double liabilities, double desiredLoanAmount, int loanDuration,
                                UserAccount user, String propertyAddress, String propertyType, double propertySize,
                                int propertyFloor) {

        this.user = user;
        Pair<Boolean, String> validityCheckMonthlyIncome = checkValidDouble(monthlyIncome);
        Pair<Boolean, String> validityCheckMonthlyExpenses = checkValidDouble(monthlyExpenses);
        Pair<Boolean, String> validityCheckMonthlyLiabilities = checkValidDouble(liabilities);
        Pair<Boolean, String> validityCheckPropertySize = checkValidDouble(propertySize);
        Pair<Boolean, String> validityCheckDesiredLoanAmount = checkValidDouble(desiredLoanAmount);

        if (!validityCheckMonthlyIncome.getKey() && validityCheckMonthlyExpenses.getKey() && validityCheckMonthlyLiabilities.getKey()
                && validityCheckPropertySize.getKey() && validityCheckDesiredLoanAmount.getKey()) {

            return "Invalid input: Please don’t enter negative numbers.";
        }

        Pair<Boolean, String> validityCheckLoanDuration = checkValidInt(loanDuration);
        Pair<Boolean, String> validityCheckPropertyFloor = checkValidInt(propertyFloor);
        if (!validityCheckLoanDuration.getKey() && !validityCheckPropertyFloor.getKey()) {
            return "Invalid input: Please don’t enter negative numbers.";
        }

        Pair<Boolean, String> validityCheckPropertyAddress = checkValidString(propertyAddress);
        Pair<Boolean, String> validityCheckPropertyType = checkValidString(propertyType);
        if (!validityCheckPropertyType.getKey()) {
            return validityCheckPropertyType.getValue();
        }
        else if (!validityCheckPropertyAddress.getKey()) {
            return validityCheckPropertyAddress.getValue();
        }

        calculateVehicleLoanAmount(monthlyIncome, monthlyExpenses, liabilities, desiredLoanAmount, loanDuration);
        createHomeLoan(storage.getBankAccount(bankAccountID), acceptedLoanAmount, loanDuration, propertyAddress, propertyType, propertySize, propertyFloor);

        String resultString = loanCreationStatus + ln+
                " Home loan application submitted:" + ln +
                " Applicant: " + user.getFullName() + ln +
                " Income: " + monthlyIncome + " SEK/mo" + ln +
                " Expenses: " + monthlyExpenses + " SEK/mo" + ln +
                " Liabilities & dependencies: " + liabilities + " SEK" + ln +
                " Home address: " + propertyAddress + ln +
                " Type of home: " + propertyType + ln +
                " Property size: " + propertySize + " m^2" + ln +
                " Amount of stories: " + propertySize + ln +
                " Duration of the loan: " + loanDuration + " months";

        return resultString;
    }

    public boolean assessLoanEligibility(double monthlyIncome, double monthlyExpenses, double liabilities) {
        remainingIncome = monthlyIncome - monthlyExpenses - liabilities;
        return remainingIncome >= monthlyIncome / 4.5;
    }

    public double calculateMaxPersonalLoanAmount() {
        return remainingIncome * 4.5 * loanDuration;
    }

    public boolean checkMaxPersLoanAmount() {
        if (maxPersLoanAmount <= MAX_RANGE_PERSONAL * loanDuration) {
            return maxPersLoanAmount >= MIN_RANGE_HOME * loanDuration;
        } else {
            return false;
        }
    }

    public void calculateMaxHomeLoanAmount() {
        maxHomeLoanAmount = remainingIncome * 4.5 * loanDuration;
    }

    public boolean checkMaxHomeLoanAmount() {
        if (maxHomeLoanAmount <= MAX_RANGE_HOME * loanDuration) {
            return maxHomeLoanAmount >= MIN_RANGE_HOME * loanDuration;
        } else {
            return false;
        }
    }

    public void calculateMaxVehicleLoanAmount() {
        maxVehicleLoanAmount = remainingIncome * 4.5 * loanDuration;
    }

    public boolean checkMaxVehicleLoanAmount() {
        if (maxVehicleLoanAmount <= MAX_RANGE_VEHICLE * loanDuration) {
            return maxVehicleLoanAmount >= MIN_RANGE_VEHICLE * loanDuration;
        } else {
            return false;
        }
    }

    /**
     *
     *
     * @param monthlyIncome
     * @param monthlyExpenses
     * @param liabilities
     * @param desiredLoanAmount
     * @param loanDuration
     */
    public void calculatePersonalLoanAmount(double monthlyIncome, double monthlyExpenses, double liabilities, double desiredLoanAmount, int loanDuration) {
        this.loanDuration = loanDuration;
        this.desiredLoanAmount = desiredLoanAmount;
        if (assessLoanEligibility(monthlyIncome, monthlyExpenses, liabilities)) {
            calculateMaxPersonalLoanAmount();
            if (desiredLoanAmount >= MIN_RANGE_PERSONAL * loanDuration) {
                if (desiredLoanAmount <= MAX_RANGE_PERSONAL * loanDuration) {
                    if (checkMaxPersLoanAmount()) {
                        if (desiredLoanAmount <= maxPersLoanAmount) {
                            acceptedLoanAmount = desiredLoanAmount;
                            loanEligibility = "You are eligible for the desired amount.";

                        } else {
                            acceptedLoanAmount = maxPersLoanAmount;
                            loanEligibility = "This is the maximum you can afford to loan.";
                        }
                    } else if (maxPersLoanAmount > MAX_RANGE_PERSONAL * loanDuration) {
                        acceptedLoanAmount = desiredLoanAmount;
                        loanEligibility = "You are eligible for the desired amount.";
                    } else if (maxPersLoanAmount < MIN_RANGE_PERSONAL * loanDuration) {
                        loanEligibility = "You are not eligible for a personal loan.";
                    }
                } else {
                    if (maxPersLoanAmount > MAX_RANGE_PERSONAL * loanDuration) {
                        acceptedLoanAmount = MAX_RANGE_PERSONAL * loanDuration;
                        loanEligibility = "You can only loan up to 100.000 SEK.";
                    } else if (maxPersLoanAmount >= MIN_RANGE_PERSONAL * loanDuration) {
                        acceptedLoanAmount = maxPersLoanAmount;
                        loanEligibility = "This is the maximum you can afford to loan.";

                    } else {
                        loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
                        loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
                    }
                }

            } else if (maxPersLoanAmount >= MIN_RANGE_PERSONAL * loanDuration) {
                acceptedLoanAmount = MIN_RANGE_PERSONAL;
                loanEligibility = "The desired amount is too low. You can get a loan of at least 5000 SEK.";
            } else {
                loanEligibility = "You are ineligible for such a low amount.";
                loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
            }

        } else {
            loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
            loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
        }

    }

    public void calculateHomeLoanAmount(double monthlyIncome, double monthlyExpenses, double liabilities, double desiredLoanAmount, int loanDuration) {
        this.loanDuration = loanDuration;
        this.desiredLoanAmount = desiredLoanAmount;
        if (assessLoanEligibility(monthlyIncome, monthlyExpenses, liabilities)) {
            calculateMaxHomeLoanAmount();
            if (desiredLoanAmount >= MIN_RANGE_HOME * loanDuration) {
                if (desiredLoanAmount <= maxHomeLoanAmount * loanDuration) {
                    if (checkMaxHomeLoanAmount()) {
                        if (desiredLoanAmount <= maxHomeLoanAmount) {
                            acceptedLoanAmount = desiredLoanAmount;
                            loanEligibility = "You are eligible for the desired amount.";

                        } else {
                            acceptedLoanAmount = maxHomeLoanAmount;
                            loanEligibility = "This is the maximum you can afford to loan.";
                        }
                    } else if (maxHomeLoanAmount > MAX_RANGE_HOME * loanDuration) {
                        acceptedLoanAmount = desiredLoanAmount;
                        loanEligibility = "You are eligible for the desired amount.";
                    } else if (maxHomeLoanAmount < MIN_RANGE_HOME * loanDuration) {
                        loanEligibility = "You are not eligible for a home loan.";
                        loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
                    }
                } else {
                    if (maxHomeLoanAmount > MAX_RANGE_HOME * loanDuration) {
                        acceptedLoanAmount = MAX_RANGE_HOME * loanDuration;
                        loanEligibility = "You can only loan up to 6.000.000 SEK.";
                    } else if (maxHomeLoanAmount >= MIN_RANGE_HOME * loanDuration) {
                        acceptedLoanAmount = maxHomeLoanAmount;
                        loanEligibility = "This is the maximum you can afford to loan.";

                    } else {
                        loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
                        loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
                    }
                }

            } else if (maxHomeLoanAmount >= MIN_RANGE_HOME * loanDuration) {
                acceptedLoanAmount = MIN_RANGE_HOME;
                loanEligibility = "The desired amount is too low. You can get a loan of at least 100.000 SEK.";
            } else {
                loanEligibility = "You are ineligible for such a low amount.";
                loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
            }

        } else {
            loanEligibility ="Unfortunately you are automatically ineligible for a loan.";
            loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
        }
    }

    public void calculateVehicleLoanAmount(double monthlyIncome, double monthlyExpenses, double liabilities, double desiredLoanAmount, int loanDuration) {
        this.loanDuration = loanDuration;
        this.desiredLoanAmount = desiredLoanAmount;
        if (assessLoanEligibility(monthlyIncome, monthlyExpenses, liabilities)) {
            calculateMaxVehicleLoanAmount();
            if (desiredLoanAmount >= MIN_RANGE_VEHICLE * loanDuration) {
                if (desiredLoanAmount <= maxVehicleLoanAmount * loanDuration) {
                    if (checkMaxVehicleLoanAmount()) {
                        if (desiredLoanAmount <= maxVehicleLoanAmount) {
                            acceptedLoanAmount = desiredLoanAmount;
                            loanEligibility = "You are eligible for the desired amount.";

                        } else {
                            acceptedLoanAmount = maxVehicleLoanAmount;
                            loanEligibility = "This is the maximum you can afford to loan.";
                        }
                    } else if (maxVehicleLoanAmount > MAX_RANGE_VEHICLE * loanDuration) {
                        acceptedLoanAmount = desiredLoanAmount;
                        loanEligibility = "You are eligible for the desired amount.";
                    } else if (maxVehicleLoanAmount < MIN_RANGE_VEHICLE * loanDuration) {
                        loanEligibility = "You are not eligible for a vehicle loan.";
                        loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
                    }
                } else {
                    if (maxVehicleLoanAmount > MAX_RANGE_VEHICLE * loanDuration) {
                        acceptedLoanAmount = MAX_RANGE_VEHICLE * loanDuration;
                        loanEligibility = "You can only loan up to 600.000 SEK.";
                    } else if (maxVehicleLoanAmount >= MIN_RANGE_VEHICLE * loanDuration) {
                        acceptedLoanAmount = maxVehicleLoanAmount;
                        loanEligibility = "This is the maximum you can afford to loan.";

                    } else {
                        loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
                        loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
                    }
                }

            } else if (maxVehicleLoanAmount >= MIN_RANGE_VEHICLE * loanDuration) {
                acceptedLoanAmount = MIN_RANGE_VEHICLE;
                loanEligibility = "The desired amount is too low. You can get a loan of at least 20.000 SEK.";
            } else {
                loanEligibility = "You are ineligible for such a low amount.";
                loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
            }

        } else {
            loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
            loanCreationStatus = "User’s" + user.getFullName() + "loan for " + desiredLoanAmount + "SEK was denied.";
        }
    }

    public void loanRepayment() {
        for (BankAccount bankAccount : storage.getBankAccountMap().values()) {

            for (Loan loans : bankAccount.getLoanMap().values()) {
                LocalDateTime yearlyPaymentDate = loans.getLastRepaymentDate();

                if (ChronoUnit.DAYS.between(yearlyPaymentDate.plusYears(1), yearlyPaymentDate) == 0||ChronoUnit.DAYS.between(yearlyPaymentDate.plusYears(1), yearlyPaymentDate) < 0) {

                    BigDecimal moneyTransferred = bankAccount.getBalance().multiply(BigDecimal.valueOf(loans.getInterestRate() * -1));
                    String paymentNote = "Payment on your loan";
                    transactionController.transferMoney(bankAccount.getBankAccountID(), "0", moneyTransferred, paymentNote, LocalDateTime.now());
                    if ((loans.getCreationDate().getYear()+loans.getLoanDuration()) == LocalDate.now().getYear()) {
                        bankAccount.getLoanMap().remove(loans.getLoanID());
                        return;
                    }
                    loans.setLastRepaymentDate(LocalDateTime.now());
                }
            }
        }
    }

    public void createHomeLoan(BankAccount bankAccount, double loanAmount, int loanDuration, String propertyAddress, String propertyType, double propertySize, int propertyFloors) {
        bankAccount.addLoan(new HomeLoan(bankAccount, generateLoanID(bankAccount), loanAmount, loanDuration, propertyAddress, propertyType, propertySize, propertyFloors));
        loanCreationStatus = "User’s" + user.getFullName() + "loan for" + acceptedLoanAmount + "SEK was approved.";
    }

    public void createPersonalLoan(BankAccount bankAccount, double loanAmount, int loanDuration, String personalReasons) {
        bankAccount.addLoan(new PersonalLoan(bankAccount, generateLoanID(bankAccount), loanAmount,loanDuration, personalReasons));
        loanCreationStatus = "User’s" + user.getFullName() + "loan for" + acceptedLoanAmount + "SEK was approved.";
    }

    public void createVehicleLoan(BankAccount bankAccount, double loanAmount, int loanDuration, String typeOfFuel, double mileage, int manufactureYear) {
        bankAccount.addLoan(new VehicleLoan(bankAccount, generateLoanID(bankAccount), loanAmount,  loanDuration, typeOfFuel, mileage, manufactureYear));
        loanCreationStatus = "User’s" + user.getFullName() + "loan for" + acceptedLoanAmount + "SEK was approved.";
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

    private Pair<Boolean, String> checkValidDouble(double value) {
        boolean valid = true;
        String reasoning = "";

        if (value<0) {
            valid = false;
            reasoning = "Invalid input: Please don’t enter negative numbers.";
        }

        return new Pair<>(valid, reasoning);
    }


    private Pair<Boolean, String> checkValidInt(int value) {
        boolean valid = true;
        String reasoning = "";

        if (value < 0) {
            valid = false;
            reasoning = "Invalid input: Please don’t enter negative numbers.";
        }

        return new Pair<>(valid, reasoning);
    }

    private Pair<Boolean, String> checkValidString(String value) {
        boolean valid = true;
        String reasoning = "";

        if (value.equals("")) {
            valid = false;
            reasoning = "Invalid input: Please don't leave text field empty.";
        }
        for (int i = 0; i<value.length(); i++) {
            for (int y = 0; y <= 9; y++) {
                if (value.charAt(i) == y) {
                    reasoning = "Invalid input: Please don't enter a number in a text field.";
                }
            }
        }
        return new Pair<>(valid, reasoning);
    }

}


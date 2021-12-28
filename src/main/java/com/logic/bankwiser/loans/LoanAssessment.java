package com.logic.bankwiser.loans;

public class LoanAssessment {

    private int loanDuration; // value in years. input from the user
    private boolean eligibility;
    private double remainingIncome;
    private double desiredLoanAmount; // value in SEK. input from the user
    private double acceptedLoanAmount = 0.0;
    private final double personalLoanInterestRate = 0.07;
    private final double homeLoanInterestRate = 0.02;
    private final double vehicleLoanInterestRate = 0.055;
    private double minRangePersonal = 5000/12;
    private double maxRangePersonal = 100000/12;
    private double minRangeHome = 100000/12;
    private double maxRangeHome = 6000000/12;
    private double minRangeVehicle = 20000/12;
    private double maxRangeVehicle = 600000/12;
    private double maxPersLoanAmount;
    private double maxHomeLoanAmount;
    private double maxVehicleLoanAmount;
    private String loanEligibility = "";

    public boolean assessLoanEligibility(double monthlyIncome, double monthlyExpenses, double liabilities) {
        remainingIncome = monthlyIncome - monthlyExpenses - liabilities;
        if(remainingIncome >= monthlyIncome / 4.5) {
            eligibility = true;
        } else {
            eligibility = false;
        }
        return eligibility;
    }


    private void calculateMaxPersLoanAmount() {
        maxPersLoanAmount = remainingIncome * 4.5 * loanDuration;
    }

    private void calculateMaxHomeLoanAmount() {
        maxHomeLoanAmount = remainingIncome * 4.5 * loanDuration;
    }

    private void calculateMaxVehicleLoanAmount() {
        maxVehicleLoanAmount = remainingIncome * 4.5 * loanDuration;
    }

    public boolean checkMaxPersLoanAmount() {
        calculateMaxPersLoanAmount();
        if(maxPersLoanAmount <= maxRangePersonal * loanDuration) {
            if(maxPersLoanAmount >= minRangePersonal * loanDuration) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean checkMaxHomeLoanAmount() {
        calculateMaxHomeLoanAmount();
        if(maxHomeLoanAmount <= maxRangeHome * loanDuration) {
            if(maxHomeLoanAmount >= minRangeHome * loanDuration) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean checkMaxVehicleLoanAmount() {
        calculateMaxVehicleLoanAmount();
        if(maxVehicleLoanAmount <= maxRangeVehicle * loanDuration) {
            if(maxVehicleLoanAmount >= minRangeVehicle * loanDuration) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public void calculatePersonalLoanAmount(double monthlyIncome, double monthlyExpenses, double liabilities, double desiredLoanAmount, int loanDuration) {
        this.loanDuration = loanDuration;
        this.desiredLoanAmount = desiredLoanAmount;
        calculateMaxPersLoanAmount();
        if(assessLoanEligibility(monthlyIncome, monthlyExpenses, liabilities)) {
            if(desiredLoanAmount >= minRangePersonal * loanDuration) {
                if(desiredLoanAmount <= maxRangePersonal * loanDuration) {
                    if(checkMaxPersLoanAmount()) {
                        if(desiredLoanAmount <= maxPersLoanAmount) {
                            acceptedLoanAmount = desiredLoanAmount;
                            loanEligibility = "You are eligible for the desired amount.";

                        } else {
                            acceptedLoanAmount = maxPersLoanAmount;
                            loanEligibility = "This is the maximum you can afford to loan.";
                        }
                    } else if(maxPersLoanAmount > maxRangePersonal * loanDuration) {
                        acceptedLoanAmount = desiredLoanAmount;
                        loanEligibility = "You are eligible for the desired amount.";
                    } else if(maxPersLoanAmount < minRangePersonal * loanDuration) {
                        loanEligibility = "You are not eligible for a personal loan.";
                    }
                } else {
                    if(maxPersLoanAmount > maxRangePersonal * loanDuration) {
                        acceptedLoanAmount = maxRangePersonal * loanDuration;
                        loanEligibility = "You can only loan up to 100.000 SEK.";
                    }else if(maxPersLoanAmount >= minRangePersonal * loanDuration) {
                        acceptedLoanAmount = maxPersLoanAmount;
                        loanEligibility = "This is the maximum you can afford to loan.";

                    }else {
                        loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
                    }
                }

            } else if(maxPersLoanAmount >= minRangePersonal * loanDuration) {
                acceptedLoanAmount = minRangePersonal;
                loanEligibility = "The desired amount is too low. You can get a loan of at least 5000 SEK.";
            } else {
                loanEligibility = "You are ineligible for such a low amount.";
            }

        } else {
            loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
        }
    }


    public void calculateHomeLoanAmount(double monthlyIncome, double monthlyExpenses, double liabilities, double desiredLoanAmount, int loanDuration) {
        this.loanDuration = loanDuration;
        this.desiredLoanAmount = desiredLoanAmount;
        calculateMaxHomeLoanAmount();
        if(assessLoanEligibility(monthlyIncome, monthlyExpenses, liabilities)) {
            if(desiredLoanAmount >= minRangeHome * loanDuration) {
                if(desiredLoanAmount <= maxHomeLoanAmount * loanDuration) {
                    if(checkMaxHomeLoanAmount()) {
                        if(desiredLoanAmount <= maxHomeLoanAmount) {
                            acceptedLoanAmount = desiredLoanAmount;
                            loanEligibility = "You are eligible for the desired amount.";

                        } else {
                            acceptedLoanAmount = maxHomeLoanAmount;
                            loanEligibility = "This is the maximum you can afford to loan.";
                        }
                    } else if(maxHomeLoanAmount > maxRangeHome * loanDuration) {
                        acceptedLoanAmount = desiredLoanAmount;
                        loanEligibility = "You are eligible for the desired amount.";
                    } else if(maxHomeLoanAmount < minRangeHome * loanDuration) {
                        loanEligibility = "You are not eligible for a home loan.";
                    }
                } else {
                    if(maxHomeLoanAmount > maxRangeHome * loanDuration) {
                        acceptedLoanAmount = maxRangeHome * loanDuration;
                        loanEligibility = "You can only loan up to 6.000.000 SEK.";
                    }else if(maxHomeLoanAmount >= minRangeHome * loanDuration){
                        acceptedLoanAmount = maxHomeLoanAmount;
                        loanEligibility = "This is the maximum you can afford to loan.";

                    }else {
                        loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
                    }
                }

            } else if(maxHomeLoanAmount >= minRangeHome * loanDuration){
                acceptedLoanAmount = minRangeHome;
                loanEligibility = "The desired amount is too low. You can get a loan of at least 100.000 SEK.";
            } else {
                loanEligibility = "You are ineligible for such a low amount.";
            }

        } else {
            loanEligibility ="Unfortunately you are automatically ineligible for a loan.";
        }
    }


    public void calculateVehicleLoanAmount(double monthlyIncome, double monthlyExpenses, double liabilities, double desiredLoanAmount, int loanDuration) {
        this.loanDuration = loanDuration;
        this.desiredLoanAmount = desiredLoanAmount;
        calculateMaxVehicleLoanAmount();
        if(assessLoanEligibility(monthlyIncome, monthlyExpenses, liabilities)) {
            if(desiredLoanAmount >= minRangeVehicle * loanDuration) {
                if(desiredLoanAmount <= maxVehicleLoanAmount * loanDuration) {
                    if(checkMaxVehicleLoanAmount()) {
                        if(desiredLoanAmount <= maxVehicleLoanAmount) {
                            acceptedLoanAmount = desiredLoanAmount;
                            loanEligibility = "You are eligible for the desired amount.";

                        } else {
                            acceptedLoanAmount = maxVehicleLoanAmount;
                            loanEligibility = "This is the maximum you can afford to loan.";
                        }
                    } else if(maxVehicleLoanAmount > maxRangeVehicle * loanDuration) {
                        acceptedLoanAmount = desiredLoanAmount;
                        loanEligibility = "You are eligible for the desired amount.";
                    } else if(maxVehicleLoanAmount < minRangeVehicle * loanDuration) {
                        loanEligibility = "You are not eligible for a vehicle loan.";
                    }
                } else {
                    if(maxVehicleLoanAmount > maxRangeVehicle * loanDuration) {
                        acceptedLoanAmount = maxRangeVehicle * loanDuration;
                        loanEligibility = "You can only loan up to 600.000 SEK.";
                    }else if(maxVehicleLoanAmount >= minRangeVehicle * loanDuration) {
                        acceptedLoanAmount = maxVehicleLoanAmount;
                        loanEligibility = "This is the maximum you can afford to loan.";

                    }else {
                        loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
                    }
                }

            } else if(maxVehicleLoanAmount >= minRangeVehicle * loanDuration){
                acceptedLoanAmount = minRangeVehicle;
                loanEligibility = "The desired amount is too low. You can get a loan of at least 20.000 SEK.";
            } else {
                loanEligibility = "You are ineligible for such a low amount.";
            }

        } else {
            loanEligibility = "Unfortunately you are automatically ineligible for a loan.";
        }
    }



    public double getAcceptedLoanAmount() {
        return acceptedLoanAmount;
    }

    public String getLoanEligibility(){
        return  loanEligibility;
    }

    public double getPersonalLoanInterestRate() {
        return personalLoanInterestRate;
    }

    public double getHomeLoanInterestRate() {
        return homeLoanInterestRate;
    }

    public double getVehicleLoanInterestRate() {
        return vehicleLoanInterestRate;
    }

}

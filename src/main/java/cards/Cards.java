package cards;
import java.util.Random;

public abstract class Cards {

    private final int CARD_NUMBER;
    private final int CCV;
    private final int LOAN_AMOUNT;
    private final String EXPIRATION_DATE;
    private int pin;
    private String status;


    public Cards(int loanAmount,String expirationDate, int pin, String status){

        int cardNumberMin = 100000;
        int cardNumberMax = 999999;
        Random cardNumberRand = new Random();
        int cardNumber = cardNumberRand.nextInt((cardNumberMax-cardNumberMin)+1) + cardNumberMin;

        int CCVMin = 100000;
        int CCVMax = 999999;
        Random CCVRand = new Random();
        int CCV = CCVRand.nextInt((CCVMax-CCVMin)+1) + CCVMin;

        this.CARD_NUMBER=cardNumber;
        this.CCV=CCV;
        this.LOAN_AMOUNT = loanAmount;
        this.EXPIRATION_DATE = expirationDate;
        this.pin = pin;
        this.status = status;

    }

    public int getCardNumber() {
        return CARD_NUMBER;
    }

    public int CCV() {
        return CCV;
    }

    public int getLoanAmount() {
        return LOAN_AMOUNT;
    }

    public String getExpirationDate() {
        return EXPIRATION_DATE;
    }

    public String getStatus() {
        return status;
    }

    public void setPin(int pin) {
        this.pin=pin;
    }

    public void setStatus(String status) {
        this.status=status;
    }

}

package cards;

import java.util.Random;

public class DebitCard extends Cards{

    final private int LINKED_ACCOUNT;

    public DebitCard(int loanAmount,String expirationDate, int pin, String status, int linkedAccount){

       super(loanAmount, expirationDate, pin, status);
       this.LINKED_ACCOUNT=linkedAccount;

    }

    public int getLinkedAccount() {
        return this.LINKED_ACCOUNT;
    }

}

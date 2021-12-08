package com.logic.bankwiser.cards;
import java.util.Date;
import java.math.BigDecimal;

public class DebitCard extends Card {


    public DebitCard(int linkedAccount, String expirationDate, int pin, boolean status, String region, boolean onlineStatus){
        super(linkedAccount, expirationDate, pin, status, region, onlineStatus);
    }

}

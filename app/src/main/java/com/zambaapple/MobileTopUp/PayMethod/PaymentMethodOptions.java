
package com.zambaapple.MobileTopUp.PayMethod;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class PaymentMethodOptions {

    @SerializedName("card")
    @Expose
    private Card__1 card;

    public Card__1 getCard() {
        return card;
    }

    public void setCard(Card__1 card) {
        this.card = card;
    }

}

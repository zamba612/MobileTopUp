
package com.zambaapple.MobileTopUp.PayMethod;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class PaymentMethodDetails {

    @SerializedName("card")
    @Expose
    private Card card;
    @SerializedName("type")
    @Expose
    private String type;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

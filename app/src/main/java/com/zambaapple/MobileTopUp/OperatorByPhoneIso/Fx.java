
package com.zambaapple.MobileTopUp.OperatorByPhoneIso;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Fx {

    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}

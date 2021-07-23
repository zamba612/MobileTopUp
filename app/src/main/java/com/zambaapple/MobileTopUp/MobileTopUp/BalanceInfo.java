
package com.zambaapple.MobileTopUp.MobileTopUp;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class BalanceInfo {

    @SerializedName("oldBalance")
    @Expose
    private Double oldBalance;
    @SerializedName("newBalance")
    @Expose
    private Double newBalance;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public Double getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Double oldBalance) {
        this.oldBalance = oldBalance;
    }

    public Double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Double newBalance) {
        this.newBalance = newBalance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}

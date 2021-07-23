
package com.zambaapple.MobileTopUp.PayMethod;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zambaapple.MobileTopUp.PayMethod.Checks;

@Generated("jsonschema2pojo")
public class Card {

    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("checks")
    @Expose
    private Checks checks;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("exp_month")
    @Expose
    private Integer expMonth;
    @SerializedName("exp_year")
    @Expose
    private Integer expYear;
    @SerializedName("fingerprint")
    @Expose
    private String fingerprint;
    @SerializedName("funding")
    @Expose
    private String funding;
    @SerializedName("installments")
    @Expose
    private Object installments;
    @SerializedName("last4")
    @Expose
    private String last4;
    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("three_d_secure")
    @Expose
    private Object threeDSecure;
    @SerializedName("wallet")
    @Expose
    private Object wallet;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Checks getChecks() {
        return checks;
    }

    public void setChecks(Checks checks) {
        this.checks = checks;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public Object getInstallments() {
        return installments;
    }

    public void setInstallments(Object installments) {
        this.installments = installments;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Object getThreeDSecure() {
        return threeDSecure;
    }

    public void setThreeDSecure(Object threeDSecure) {
        this.threeDSecure = threeDSecure;
    }

    public Object getWallet() {
        return wallet;
    }

    public void setWallet(Object wallet) {
        this.wallet = wallet;
    }

}

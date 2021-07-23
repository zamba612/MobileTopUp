
package com.zambaapple.MobileTopUp.PayMethod;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Card__1 {

    @SerializedName("installments")
    @Expose
    private Object installments;
    @SerializedName("network")
    @Expose
    private Object network;
    @SerializedName("request_three_d_secure")
    @Expose
    private String requestThreeDSecure;

    public Object getInstallments() {
        return installments;
    }

    public void setInstallments(Object installments) {
        this.installments = installments;
    }

    public Object getNetwork() {
        return network;
    }

    public void setNetwork(Object network) {
        this.network = network;
    }

    public String getRequestThreeDSecure() {
        return requestThreeDSecure;
    }

    public void setRequestThreeDSecure(String requestThreeDSecure) {
        this.requestThreeDSecure = requestThreeDSecure;
    }

}

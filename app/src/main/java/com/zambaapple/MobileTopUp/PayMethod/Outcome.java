
package com.zambaapple.MobileTopUp.PayMethod;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Outcome {

    @SerializedName("network_status")
    @Expose
    private String networkStatus;
    @SerializedName("reason")
    @Expose
    private Object reason;
    @SerializedName("risk_level")
    @Expose
    private String riskLevel;
    @SerializedName("risk_score")
    @Expose
    private Integer riskScore;
    @SerializedName("seller_message")
    @Expose
    private String sellerMessage;
    @SerializedName("type")
    @Expose
    private String type;

    public String getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getSellerMessage() {
        return sellerMessage;
    }

    public void setSellerMessage(String sellerMessage) {
        this.sellerMessage = sellerMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

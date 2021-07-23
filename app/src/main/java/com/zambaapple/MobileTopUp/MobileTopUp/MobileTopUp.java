
package com.zambaapple.MobileTopUp.MobileTopUp;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Generated("jsonschema2pojo")
public class MobileTopUp {

    @SerializedName("transactionId")
    @Expose
    private Integer transactionId;
    @SerializedName("operatorTransactionId")
    @Expose
    private String operatorTransactionId;
    @SerializedName("customIdentifier")
    @Expose
    private String customIdentifier;
    @SerializedName("recipientPhone")
    @Expose
    private String recipientPhone;
    @SerializedName("recipientEmail")
    @Expose
    private Object recipientEmail;
    @SerializedName("senderPhone")
    @Expose
    private Object senderPhone;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("operatorId")
    @Expose
    private Integer operatorId;
    @SerializedName("operatorName")
    @Expose
    private String operatorName;
    @SerializedName("discount")
    @Expose
    private Double discount;
    @SerializedName("discountCurrencyCode")
    @Expose
    private String discountCurrencyCode;
    @SerializedName("requestedAmount")
    @Expose
    private Integer requestedAmount;
    @SerializedName("requestedAmountCurrencyCode")
    @Expose
    private String requestedAmountCurrencyCode;
    @SerializedName("deliveredAmount")
    @Expose
    private Double deliveredAmount;
    @SerializedName("deliveredAmountCurrencyCode")
    @Expose
    private String deliveredAmountCurrencyCode;
    @SerializedName("transactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("pinDetail")
    @Expose
    private Object pinDetail;
    @SerializedName("balanceInfo")
    @Expose
    private BalanceInfo balanceInfo;
    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("infoLink")
    @Expose
    private Object infoLink;
    @SerializedName("details")
    @Expose
    private List<Object> details = null;

    public Integer getTransactionId() {
        return transactionId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(Object infoLink) {
        this.infoLink = infoLink;
    }

    public List<Object> getDetails() {
        return details;
    }

    public void setDetails(List<Object> details) {
        this.details = details;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getOperatorTransactionId() {
        return operatorTransactionId;
    }

    public void setOperatorTransactionId(String operatorTransactionId) {
        this.operatorTransactionId = operatorTransactionId;
    }

    public String getCustomIdentifier() {
        return customIdentifier;
    }

    public void setCustomIdentifier(String customIdentifier) {
        this.customIdentifier = customIdentifier;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public Object getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(Object recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public Object getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(Object senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getDiscountCurrencyCode() {
        return discountCurrencyCode;
    }

    public void setDiscountCurrencyCode(String discountCurrencyCode) {
        this.discountCurrencyCode = discountCurrencyCode;
    }

    public Integer getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(Integer requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getRequestedAmountCurrencyCode() {
        return requestedAmountCurrencyCode;
    }

    public void setRequestedAmountCurrencyCode(String requestedAmountCurrencyCode) {
        this.requestedAmountCurrencyCode = requestedAmountCurrencyCode;
    }

    public Double getDeliveredAmount() {
        return deliveredAmount;
    }

    public void setDeliveredAmount(Double deliveredAmount) {
        this.deliveredAmount = deliveredAmount;
    }

    public String getDeliveredAmountCurrencyCode() {
        return deliveredAmountCurrencyCode;
    }

    public void setDeliveredAmountCurrencyCode(String deliveredAmountCurrencyCode) {
        this.deliveredAmountCurrencyCode = deliveredAmountCurrencyCode;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Object getPinDetail() {
        return pinDetail;
    }

    public void setPinDetail(Object pinDetail) {
        this.pinDetail = pinDetail;
    }

    public BalanceInfo getBalanceInfo() {
        return balanceInfo;
    }

    public void setBalanceInfo(BalanceInfo balanceInfo) {
        this.balanceInfo = balanceInfo;
    }

}

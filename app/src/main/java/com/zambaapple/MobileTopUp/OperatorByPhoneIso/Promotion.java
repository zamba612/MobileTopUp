
package com.zambaapple.MobileTopUp.OperatorByPhoneIso;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Promotion {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("promotionId")
    @Expose
    private Integer promotionId;
    @SerializedName("operatorId")
    @Expose
    private Integer operatorId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title2")
    @Expose
    private String title2;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("denominations")
    @Expose
    private String denominations;
    @SerializedName("localDenominations")
    @Expose
    private String localDenominations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDenominations() {
        return denominations;
    }

    public void setDenominations(String denominations) {
        this.denominations = denominations;
    }

    public String getLocalDenominations() {
        return localDenominations;
    }

    public void setLocalDenominations(String localDenominations) {
        this.localDenominations = localDenominations;
    }

}

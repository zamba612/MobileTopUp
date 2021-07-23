
package com.zambaapple.MobileTopUp.OperatorByPhoneIso;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zambaapple.MobileTopUp.OperatorByPhoneIso.Promotion;
import com.zambaapple.MobileTopUp.OperatorByPhoneIso.SuggestedAmountsMap;

@Generated("jsonschema2pojo")
public class OperatorValue {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("operatorId")
    @Expose
    private Integer operatorId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bundle")
    @Expose
    private Boolean bundle;
    @SerializedName("data")
    @Expose
    private Boolean data;
    @SerializedName("pin")
    @Expose
    private Boolean pin;
    @SerializedName("supportsLocalAmounts")
    @Expose
    private Boolean supportsLocalAmounts;
    @SerializedName("supportsGeographicalRechargePlans")
    @Expose
    private Boolean supportsGeographicalRechargePlans;
    @SerializedName("denominationType")
    @Expose
    private String denominationType;
    @SerializedName("senderCurrencyCode")
    @Expose
    private String senderCurrencyCode;
    @SerializedName("senderCurrencySymbol")
    @Expose
    private String senderCurrencySymbol;
    @SerializedName("destinationCurrencyCode")
    @Expose
    private String destinationCurrencyCode;
    @SerializedName("destinationCurrencySymbol")
    @Expose
    private String destinationCurrencySymbol;
    @SerializedName("commission")
    @Expose
    private Double commission;
    @SerializedName("internationalDiscount")
    @Expose
    private Double internationalDiscount;
    @SerializedName("localDiscount")
    @Expose
    private Double localDiscount;
    @SerializedName("mostPopularAmount")
    @Expose
    private Double mostPopularAmount;
    @SerializedName("mostPopularLocalAmount")
    @Expose
    private Object mostPopularLocalAmount;
    @SerializedName("minAmount")
    @Expose
    private Object minAmount;
    @SerializedName("maxAmount")
    @Expose
    private Object maxAmount;
    @SerializedName("localMinAmount")
    @Expose
    private Object localMinAmount;
    @SerializedName("localMaxAmount")
    @Expose
    private Object localMaxAmount;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("fx")
    @Expose
    private Fx fx;
    @SerializedName("logoUrls")
    @Expose
    private List<String> logoUrls = null;
    @SerializedName("fixedAmounts")
    @Expose
    private List<Double> fixedAmounts = null;
    @SerializedName("fixedAmountsDescriptions")
    @Expose
    private FixedAmountsDescriptions fixedAmountsDescriptions;
    @SerializedName("localFixedAmounts")
    @Expose
    private List<Object> localFixedAmounts = null;
    @SerializedName("localFixedAmountsDescriptions")
    @Expose
    private LocalFixedAmountsDescriptions localFixedAmountsDescriptions;
    @SerializedName("suggestedAmounts")
    @Expose
    private List<Object> suggestedAmounts = null;
    @SerializedName("suggestedAmountsMap")
    @Expose
    private SuggestedAmountsMap suggestedAmountsMap;
    @SerializedName("geographicalRechargePlans")
    @Expose
    private List<Object> geographicalRechargePlans = null;
    @SerializedName("promotions")
    @Expose
    private List<Promotion> promotions = null;
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getBundle() {
        return bundle;
    }

    public void setBundle(Boolean bundle) {
        this.bundle = bundle;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }

    public Boolean getPin() {
        return pin;
    }

    public void setPin(Boolean pin) {
        this.pin = pin;
    }

    public Boolean getSupportsLocalAmounts() {
        return supportsLocalAmounts;
    }

    public void setSupportsLocalAmounts(Boolean supportsLocalAmounts) {
        this.supportsLocalAmounts = supportsLocalAmounts;
    }

    public Boolean getSupportsGeographicalRechargePlans() {
        return supportsGeographicalRechargePlans;
    }

    public void setSupportsGeographicalRechargePlans(Boolean supportsGeographicalRechargePlans) {
        this.supportsGeographicalRechargePlans = supportsGeographicalRechargePlans;
    }

    public String getDenominationType() {
        return denominationType;
    }

    public void setDenominationType(String denominationType) {
        this.denominationType = denominationType;
    }

    public String getSenderCurrencyCode() {
        return senderCurrencyCode;
    }

    public void setSenderCurrencyCode(String senderCurrencyCode) {
        this.senderCurrencyCode = senderCurrencyCode;
    }

    public String getSenderCurrencySymbol() {
        return senderCurrencySymbol;
    }

    public void setSenderCurrencySymbol(String senderCurrencySymbol) {
        this.senderCurrencySymbol = senderCurrencySymbol;
    }

    public String getDestinationCurrencyCode() {
        return destinationCurrencyCode;
    }

    public void setDestinationCurrencyCode(String destinationCurrencyCode) {
        this.destinationCurrencyCode = destinationCurrencyCode;
    }

    public String getDestinationCurrencySymbol() {
        return destinationCurrencySymbol;
    }

    public void setDestinationCurrencySymbol(String destinationCurrencySymbol) {
        this.destinationCurrencySymbol = destinationCurrencySymbol;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getInternationalDiscount() {
        return internationalDiscount;
    }

    public void setInternationalDiscount(Double internationalDiscount) {
        this.internationalDiscount = internationalDiscount;
    }

    public Double getLocalDiscount() {
        return localDiscount;
    }

    public void setLocalDiscount(Double localDiscount) {
        this.localDiscount = localDiscount;
    }

    public Double getMostPopularAmount() {
        return mostPopularAmount;
    }

    public void setMostPopularAmount(Double mostPopularAmount) {
        this.mostPopularAmount = mostPopularAmount;
    }

    public Object getMostPopularLocalAmount() {
        return mostPopularLocalAmount;
    }

    public void setMostPopularLocalAmount(Object mostPopularLocalAmount) {
        this.mostPopularLocalAmount = mostPopularLocalAmount;
    }

    public Object getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Object minAmount) {
        this.minAmount = minAmount;
    }

    public Object getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Object maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Object getLocalMinAmount() {
        return localMinAmount;
    }

    public void setLocalMinAmount(Object localMinAmount) {
        this.localMinAmount = localMinAmount;
    }

    public Object getLocalMaxAmount() {
        return localMaxAmount;
    }

    public void setLocalMaxAmount(Object localMaxAmount) {
        this.localMaxAmount = localMaxAmount;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Fx getFx() {
        return fx;
    }

    public void setFx(Fx fx) {
        this.fx = fx;
    }

    public List<String> getLogoUrls() {
        return logoUrls;
    }

    public void setLogoUrls(List<String> logoUrls) {
        this.logoUrls = logoUrls;
    }

    public List<Double> getFixedAmounts() {
        return fixedAmounts;
    }

    public void setFixedAmounts(List<Double> fixedAmounts) {
        this.fixedAmounts = fixedAmounts;
    }

    public FixedAmountsDescriptions getFixedAmountsDescriptions() {
        return fixedAmountsDescriptions;
    }

    public void setFixedAmountsDescriptions(FixedAmountsDescriptions fixedAmountsDescriptions) {
        this.fixedAmountsDescriptions = fixedAmountsDescriptions;
    }

    public List<Object> getLocalFixedAmounts() {
        return localFixedAmounts;
    }

    public void setLocalFixedAmounts(List<Object> localFixedAmounts) {
        this.localFixedAmounts = localFixedAmounts;
    }

    public LocalFixedAmountsDescriptions getLocalFixedAmountsDescriptions() {
        return localFixedAmountsDescriptions;
    }

    public void setLocalFixedAmountsDescriptions(LocalFixedAmountsDescriptions localFixedAmountsDescriptions) {
        this.localFixedAmountsDescriptions = localFixedAmountsDescriptions;
    }

    public List<Object> getSuggestedAmounts() {
        return suggestedAmounts;
    }

    public void setSuggestedAmounts(List<Object> suggestedAmounts) {
        this.suggestedAmounts = suggestedAmounts;
    }

    public SuggestedAmountsMap getSuggestedAmountsMap() {
        return suggestedAmountsMap;
    }

    public void setSuggestedAmountsMap(SuggestedAmountsMap suggestedAmountsMap) {
        this.suggestedAmountsMap = suggestedAmountsMap;
    }

    public List<Object> getGeographicalRechargePlans() {
        return geographicalRechargePlans;
    }

    public void setGeographicalRechargePlans(List<Object> geographicalRechargePlans) {
        this.geographicalRechargePlans = geographicalRechargePlans;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
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


}


package com.zambaapple.MobileTopUp.PayMethod;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Checks {

    @SerializedName("address_line1_check")
    @Expose
    private Object addressLine1Check;
    @SerializedName("address_postal_code_check")
    @Expose
    private Object addressPostalCodeCheck;
    @SerializedName("cvc_check")
    @Expose
    private String cvcCheck;

    public Object getAddressLine1Check() {
        return addressLine1Check;
    }

    public void setAddressLine1Check(Object addressLine1Check) {
        this.addressLine1Check = addressLine1Check;
    }

    public Object getAddressPostalCodeCheck() {
        return addressPostalCodeCheck;
    }

    public void setAddressPostalCodeCheck(Object addressPostalCodeCheck) {
        this.addressPostalCodeCheck = addressPostalCodeCheck;
    }

    public String getCvcCheck() {
        return cvcCheck;
    }

    public void setCvcCheck(String cvcCheck) {
        this.cvcCheck = cvcCheck;
    }

}

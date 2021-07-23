
package com.zambaapple.MobileTopUp.PayMethod;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Address {

    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("line1")
    @Expose
    private Object line1;
    @SerializedName("line2")
    @Expose
    private Object line2;
    @SerializedName("postal_code")
    @Expose
    private Object postalCode;
    @SerializedName("state")
    @Expose
    private Object state;

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getLine1() {
        return line1;
    }

    public void setLine1(Object line1) {
        this.line1 = line1;
    }

    public Object getLine2() {
        return line2;
    }

    public void setLine2(Object line2) {
        this.line2 = line2;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

}

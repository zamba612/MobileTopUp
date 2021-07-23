
package com.zambaapple.MobileTopUp.PayMethod;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class BillingDetails {

    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("phone")
    @Expose
    private Object phone;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

}

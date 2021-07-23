
package com.zambaapple.MobileTopUp.OperatorByPhoneIso;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Country {

    @SerializedName("isoName")
    @Expose
    private String isoName;
    @SerializedName("name")
    @Expose
    private String name;

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

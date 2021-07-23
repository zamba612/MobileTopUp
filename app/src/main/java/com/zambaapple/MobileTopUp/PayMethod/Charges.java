
package com.zambaapple.MobileTopUp.PayMethod;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zambaapple.MobileTopUp.PayMethod.Datum;

@Generated("jsonschema2pojo")
public class Charges {

    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("url")
    @Expose
    private String url;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

package com.zambaapple.MobileTopUp.Sdk;
public class Card{
    public Object installments;
    public Object network;
    public String request_three_d_secure;

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

    public String getRequest_three_d_secure() {
        return request_three_d_secure;
    }

    public void setRequest_three_d_secure(String request_three_d_secure) {
        this.request_three_d_secure = request_three_d_secure;
    }
}

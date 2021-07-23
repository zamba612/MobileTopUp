package com.zambaapple.MobileTopUp.MOdels;

public class Tarif {
    String tarif;
    String curenncy;
    String rate;
    String receivcurrency;

    public Tarif(String tarif, String curenncy, String rate, String receivcurrency) {
        this.tarif = tarif;
        this.curenncy = curenncy;
        this.rate = rate;
        this.receivcurrency = receivcurrency;
    }

    public String getReceivcurrency() {
        return receivcurrency;
    }

    public void setReceivcurrency(String receivcurrency) {
        this.receivcurrency = receivcurrency;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getCurenncy() {
        return curenncy;
    }

    public void setCurenncy(String curenncy) {
        this.curenncy = curenncy;
    }
}

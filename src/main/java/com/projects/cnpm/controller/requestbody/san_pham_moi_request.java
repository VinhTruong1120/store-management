package com.projects.cnpm.controller.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;

public class san_pham_moi_request {

    private String ma_sp;
    @JsonProperty("ten_sp")
    private String Ten_sp;
    private int don_gia;
    private String mota;
    private String ma_loai;
    public String getMa_sp() {
        return ma_sp;
    }
    public void setMa_sp(String ma_sp) {
        this.ma_sp = ma_sp;
    }
    public String getTen_sp() {
        return Ten_sp;
    }
    public void setTen_sp(String ten_sp) {
        Ten_sp = ten_sp;
    }
    public int getDon_gia() {
        return don_gia;
    }
    public void setDon_gia(int don_gia) {
        this.don_gia = don_gia;
    }
    public String getMota() {
        return mota;
    }
    public void setMota(String mota) {
        this.mota = mota;
    }
    public String getMa_loai() {
        return ma_loai;
    }
    public void setMa_loai(String ma_loai) {
        this.ma_loai = ma_loai;
    }
}

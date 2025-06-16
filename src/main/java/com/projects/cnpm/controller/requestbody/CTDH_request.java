package com.projects.cnpm.controller.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTDH_request {
    @JsonProperty("ma_dh")
    private String ma_dh;
    @JsonProperty("ma_sp")
    private String ma_sp;
    @JsonProperty("so_luong")
    private int so_luong;
    public CTDH_request() {
    }
    public CTDH_request(String ma_dh, String ma_sp, int so_luong) {
        this.ma_dh = ma_dh;
        this.ma_sp = ma_sp;
        this.so_luong = so_luong;
    }
    public String getMa_dh() {
        return ma_dh;
    }
    public void setMa_dh(String ma_dh) {
        this.ma_dh = ma_dh;
    }
    public String getMa_sp() {
        return ma_sp;
    }
    public void setMa_sp(String ma_sp) {
        this.ma_sp = ma_sp;
    }
    public int getSo_luong() {
        return so_luong;
    }
    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }
}

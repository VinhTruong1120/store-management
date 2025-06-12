package com.projects.cnpm.controller.requestbody;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class fake_don {
    @JsonProperty("ID")
    private String ID;
    private Timestamp ngay_nhan;
    private String trang_thai;
    private String cuahang;
    private String nv;
    private int thanh_tien;
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public Timestamp getNgay_nhan() {
        return ngay_nhan;
    }
    public void setNgay_nhan(Timestamp ngay_nhan) {
        this.ngay_nhan = ngay_nhan;
    }
    public String getTrang_thai() {
        return trang_thai;
    }
    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }
    public String getCuahang() {
        return cuahang;
    }
    public void setCuahang(String cuahang) {
        this.cuahang = cuahang;
    }
    public String getNv() {
        return nv;
    }
    public void setNv(String nv) {
        this.nv = nv;
    }
    public int getThanh_tien() {
        return thanh_tien;
    }
    public void setThanh_tien(int thanh_tien) {
        this.thanh_tien = thanh_tien;
    }
    public fake_don(String iD, Timestamp ngay_nhan, String trang_thai, String cuahang, String nv, int thanh_tien) {
        ID = iD;
        this.ngay_nhan = ngay_nhan;
        this.trang_thai = trang_thai;
        this.cuahang = cuahang;
        this.nv = nv;
        this.thanh_tien = thanh_tien;
    }
    public fake_don() {
    }
}

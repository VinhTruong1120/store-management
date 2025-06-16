package com.projects.cnpm.controller.requestbody;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Lich_su_don {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp ngay_bd;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp ngay_kt;
    private String store_id;
    public Lich_su_don() {
    }
    public Lich_su_don(Timestamp ngay_bd, Timestamp ngay_kt, String store_id) {
        this.ngay_bd = ngay_bd;
        this.ngay_kt = ngay_kt;
        this.store_id = store_id;
    }
    public Timestamp getNgay_bd() {
        return ngay_bd;
    }
    public void setNgay_bd(Timestamp ngay_bd) {
        this.ngay_bd = ngay_bd;
    }
    public Timestamp getNgay_kt() {
        return ngay_kt;
    }
    public void setNgay_kt(Timestamp ngay_kt) {
        this.ngay_kt = ngay_kt;
    }
    public String getStore_id() {
        return store_id;
    }
    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}

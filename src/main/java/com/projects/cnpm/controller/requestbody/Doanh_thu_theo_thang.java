package com.projects.cnpm.controller.requestbody;


public class Doanh_thu_theo_thang {
    
    private String store_id;
    private int month;
    private int year;
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getStore_id() {
        return store_id;
    }
    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    private String ma_nv;
    public String getMa_nv() {
        return ma_nv;
    }
    public void setMa_nv(String ma_nv) {
        this.ma_nv = ma_nv;
    }

}

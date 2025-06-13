package com.projects.cnpm.controller.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;

public class path_product {
    private String id;
    private String ten;
    private String mota;
    private int price;
    @JsonProperty("loai_id")
    private String loai_id;
    public path_product(String id, String ten, String mota, int price, String loai_id) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
        this.price = price;
        this.loai_id = loai_id;
    }
    public String getLoai_id() {
        return loai_id;
    }
    public void setLoai_id(String loai_id) {
        this.loai_id = loai_id;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getMota() {
        return mota;
    }
    public void setMota(String mota) {
        this.mota = mota;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
    public path_product() {
    }


}

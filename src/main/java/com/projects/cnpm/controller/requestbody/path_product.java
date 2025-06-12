package com.projects.cnpm.controller.requestbody;

public class path_product {
    private String id;
    private String ten;
    private String mota;
    private int price;
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
    public path_product(String id, String ten, String mota, int price) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
        this.price = price;
    }
    public path_product() {
    }


}

package com.projects.cnpm.controller.DTO;

import com.projects.cnpm.DAO.Entity.loai_sp_entity;
import com.projects.cnpm.DAO.Entity.role_entity;

public class all_product_DTO {
    private String id;
    private String name;
    private int price;
    private String mota;
    private String ten_loai;
    public all_product_DTO() {
    }
    public all_product_DTO(String id, String name, int price, String mota, String ten_loai) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mota = mota;
        this.ten_loai = ten_loai;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getMota() {
        return mota;
    }
    public void setMota(String mota) {
        this.mota = mota;
    }
    public String getTen_loai() {
        return ten_loai;
    }
    public void setTen_loai(String ten_loai) {
        this.ten_loai = ten_loai;
    }
    

    
}

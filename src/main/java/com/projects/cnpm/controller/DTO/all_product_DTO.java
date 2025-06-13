package com.projects.cnpm.controller.DTO;

import com.projects.cnpm.DAO.Entity.loai_sp_entity;
import com.projects.cnpm.DAO.Entity.role_entity;

public class all_product_DTO {
    private String id;
    private String name;
    private int price;
    private String mota;
    private String ma_loai;
    private String ten_loai;
    public all_product_DTO(String id, String name, int price, String mota, String ma_loai, String ten_loai) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mota = mota;
        this.ma_loai = ma_loai;
        this.ten_loai = ten_loai;
    }
    public String getMa_loai() {
        return ma_loai;
    }
    public void setMa_loai(String ma_loai) {
        this.ma_loai = ma_loai;
    }
    
    public all_product_DTO() {
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

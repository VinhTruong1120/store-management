package com.projects.cnpm.controller.DTO;

public class CH_DTO {
    private String store_id;
    private String ten_cua_hang;

    public String getStore_id() {
        return store_id;
    }
    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
    public String getTen_cua_hang() {
        return ten_cua_hang;
    }
    public void setTen_cua_hang(String ten_cua_hang) {
        this.ten_cua_hang = ten_cua_hang;
    }
    public CH_DTO() {
    }
    public CH_DTO(String store_id, String ten_cua_hang) {
        this.store_id = store_id;
        this.ten_cua_hang = ten_cua_hang;
    }
}

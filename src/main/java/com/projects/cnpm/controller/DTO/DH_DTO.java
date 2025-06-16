package com.projects.cnpm.controller.DTO;

import java.sql.Timestamp;

public class DH_DTO {
    private String id;
    private String nv_id;
    private Timestamp ngay;
    private String store_name;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNv_id() {
        return nv_id;
    }
    public void setNv_id(String nv_id) {
        this.nv_id = nv_id;
    }
    public Timestamp getNgay() {
        return ngay;
    }
    public void setNgay(Timestamp ngay) {
        this.ngay = ngay;
    }
    public String getStore_name() {
        return store_name;
    }
    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
    public DH_DTO(String id, String nv_id, Timestamp ngay, String store_name) {
        this.id = id;
        this.nv_id = nv_id;
        this.ngay = ngay;
        this.store_name = store_name;
    }
    public DH_DTO() {
    }
}

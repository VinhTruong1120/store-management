package com.projects.cnpm.controller.requestbody;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class tao_CH_request {
    private String store_id;
    @JsonProperty("ten_ch")
    private String ten_cua_hang;
    @JsonProperty("dia_chi")
    private String dia_chi;
    
    public String getDia_chi() {
        return dia_chi;
    }
    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }
    private List<String> contact;
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
    public List<String> getContact() {
        return contact;
    }
    public void setContact(List<String> contact) {
        this.contact = contact;
    }

}

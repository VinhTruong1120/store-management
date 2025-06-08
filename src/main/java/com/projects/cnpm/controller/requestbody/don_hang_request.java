package com.projects.cnpm.controller.requestbody;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class don_hang_request {
    @JsonProperty("ID")
    private String ID;
    @JsonProperty("ID_nv")
    private String ID_nv;
    private List<Item> items;
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getID_nv() {
        return ID_nv;
    }
    public void setID_nv(String iD_nv) {
        ID_nv = iD_nv;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    private String store_id;
    public String getStore_id() {
        return store_id;
    }
    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}

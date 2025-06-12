package com.projects.cnpm.controller.requestbody;

public class patch_Store {
    private String id;
    private String store_name;
    private String address;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStore_name() {
        return store_name;
    }
    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public patch_Store(String id, String store_name, String address) {
        this.id = id;
        this.store_name = store_name;
        this.address = address;
    }
    public patch_Store() {
    }


}

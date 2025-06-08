package com.projects.cnpm.controller.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tao_role_moi {
    @JsonProperty("role_id")
    private String Role_Id;
    private String ten_role;
    public String getRole_Id() {
        return Role_Id;
    }
    public void setRole_Id(String role_Id) {
        Role_Id = role_Id;
    }
    public String getTen_role() {
        return ten_role;
    }
    public void setTen_role(String ten_role) {
        this.ten_role = ten_role;
    }

}

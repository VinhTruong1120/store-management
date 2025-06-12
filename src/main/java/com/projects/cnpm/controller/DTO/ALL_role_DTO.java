package com.projects.cnpm.controller.DTO;

public class ALL_role_DTO {
    private String Role_id;
    private String Role_name;
    public ALL_role_DTO() {
    }
    public ALL_role_DTO(String role_id, String role_name) {
        Role_id = role_id;
        Role_name = role_name;
    }
    public String getRole_id() {
        return Role_id;
    }
    public void setRole_id(String role_id) {
        Role_id = role_id;
    }
    public String getRole_name() {
        return Role_name;
    }
    public void setRole_name(String role_name) {
        Role_name = role_name;
    }

}

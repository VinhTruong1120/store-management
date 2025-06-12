package com.projects.cnpm.controller.DTO;

import java.util.List;



public class NV_DTO {
    private String id;
    private String name;
    private String user_name;
    private String passwords;
    private List<Role_DTO> roles; // chỉ tên role
    private String ten_cua_hang;

    public NV_DTO() {
    }
    public NV_DTO(String id, String name, String user_name, String passwords, List<Role_DTO> roles,
            String ten_cua_hang) {
        this.id = id;
        this.name = name;
        this.user_name = user_name;
        this.passwords = passwords;
        this.roles = roles;
        this.ten_cua_hang = ten_cua_hang;
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
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getPasswords() {
        return passwords;
    }
    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
    public List<Role_DTO> getRoles() {
        return roles;
    }
    public void setRoles(List<Role_DTO> roles) {
        this.roles = roles;
    }
    public String getTen_cua_hang() {
        return ten_cua_hang;
    }
    public void setTen_cua_hang(String ten_cua_hang) {
        this.ten_cua_hang = ten_cua_hang;
    }

    

    
    
}

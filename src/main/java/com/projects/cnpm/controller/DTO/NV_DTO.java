package com.projects.cnpm.controller.DTO;

import java.util.List;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.role_entity;

public class NV_DTO {
    private String id;
    private String name;
    private List<Role_DTO> roles; // chỉ tên role
    private String ten_cua_hang;

    public NV_DTO(String id, String name, List<Role_DTO> roles, String ten_cua_hang) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.ten_cua_hang = ten_cua_hang;
    }

    // Getters và Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Role_DTO> getRoles() {return roles;}
    public void setRoles(List<Role_DTO> roles) { this.roles = roles;}

    public String getTen_cua_hang() { return ten_cua_hang; }
    public void setTen_cua_hang(String ten_cua_hang) { this.ten_cua_hang = ten_cua_hang; }
    
}

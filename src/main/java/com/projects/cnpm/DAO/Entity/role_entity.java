package com.projects.cnpm.DAO.Entity;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "role" )
public class role_entity {

    @Id
    @Column(name = "role_id",columnDefinition = "char(10)",length = 10)
    private String role_id;

    @Column(name = "ten_role")
    private String ten_role;

   @ManyToMany(mappedBy = "roles")
   private List<nhanvien_entity> nhanviens;


    public role_entity() {}


    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getTen_role() {
        return ten_role;
    }

    public void setTen_role(String ten_role) {
        this.ten_role = ten_role;
    }

    public List<nhanvien_entity> getNhanvien() {
        return nhanviens;
    }

    public void setNhanvien(List<nhanvien_entity> nhanvien) {
        this.nhanviens = nhanvien;
    }
}

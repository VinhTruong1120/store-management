package com.projects.cnpm.DAO.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;



@Entity
@Table(name = "nhan_vien")
public class nhanvien_entity {

    @Id
    @Column(name = "ma_nv",length = 10,columnDefinition = "char(10)")
    private String ma_nv;

    @Column(name = "username",nullable =  false)
    private String username;

    @Column(name = "passwords",nullable = false)
    private String passwords;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    @Column(name = "ten",nullable = false)
    private String ten;

    @Column(name = "dia_chi",nullable = false)
    private String dia_chi;

    @Column(name = "luong",nullable = false)
    @Min(0)
    private long luong;

    @Column(name = "birthday",nullable = false)
    private Timestamp birthday;

    @ElementCollection
    @CollectionTable(name = "nhan_vien_lien_he",joinColumns = @JoinColumn(name = "ma_nv"))
    @Column(name = "lien_he")
    private List<String> lien_he = new ArrayList<>();

 
    @ManyToOne
    @JoinColumn(name = "store_id",foreignKey = @ForeignKey(name = "FK_store_nv"),nullable = false)
    private cuahang_entity store;

    @OneToMany(mappedBy = "nv")
    private List<don_hang_entity> don_hang;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "nhanvien_role",
            joinColumns = {@JoinColumn(name = "role_id",foreignKey = @ForeignKey(name = "FK_RNV_Role"))},
            inverseJoinColumns = {@JoinColumn(name = "ma_nv", foreignKey = @ForeignKey(name = "FK_RNV_NV"))}
    )
    private List<role_entity> roles;

    public nhanvien_entity() {}

    public String getMa_nv() {
        return ma_nv;
    }

    public void setMa_nv(String ma_nv) {
        this.ma_nv = ma_nv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public long getLuong() {
        return luong;
    }

    public void setLuong(long luong) {
        this.luong = luong;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public List<String> getLien_he() {
        return lien_he;
    }

    public void setLien_he(List<String> lien_he) {
        this.lien_he = lien_he;
    }

    public cuahang_entity getStore() {
        return store;
    }

    public void setStore(cuahang_entity store) {
        this.store = store;
    }

    public List<don_hang_entity> getDon_hang() {
        return don_hang;
    }

    public void setDon_hang(List<don_hang_entity> don_hang) {
        this.don_hang = don_hang;
    }

    public List<role_entity> getRoles() {
        return roles;
    }

    public void setRoles(List<role_entity> roles) {
        this.roles = roles;
    }
}

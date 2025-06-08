package com.projects.cnpm.controller.requestbody;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projects.cnpm.DAO.Entity.role_entity;

public class tao_moi_NV_request {
    String ma_nv;
    public String getMa_nv() {
        return ma_nv;
    }
    public void setMa_nv(String ma_nv) {
        this.ma_nv = ma_nv;
    }
    String ten;
    String username;
    String passwords;
    String dia_chi;
    int luong;
    Timestamp birthday;
    List<String> lien_he;
    @JsonProperty("IDCH")
    String store_id;
    List<role_entity> roles;
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
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
    public String getDia_chi() {
        return dia_chi;
    }
    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }
    public int getLuong() {
        return luong;
    }
    public void setLuong(int luong) {
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
    public String getIdcua_hang() {
        return store_id;
    }
    public void setIdcua_hang(String idcua_hang) {
        store_id = idcua_hang;
    }
    public List<role_entity> getRoles() {
        return roles;
    }
    public void setRoles(List<role_entity> roles) {
        this.roles = roles;
    }
    
}

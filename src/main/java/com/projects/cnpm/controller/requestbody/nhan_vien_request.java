package com.projects.cnpm.controller.requestbody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class nhan_vien_request {
    
    private String ma_nv;

    private String username;

    private String passwords;

   
    private String ten;


    private String dia_chi;

    private long luong;

    private Timestamp birthday;


    private List<String> lien_he = new ArrayList<>();


    public String getMa_nv() {
        return ma_nv;
    }


    public void setMa_nv(String ma_nv) {
        this.ma_nv = ma_nv;
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
}

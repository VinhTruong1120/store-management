package com.projects.cnpm.controller.DTO;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class all_staff {
     private String id;


    private String hoten;

    private String vitri;
    
    
    private String ch_id;

    private String ten_ch;

    private Timestamp birthday;

    private String dia_chi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getCh_id() {
        return ch_id;
    }

    public void setCh_id(String ch_id) {
        this.ch_id = ch_id;
    }

    public String getTen_ch() {
        return ten_ch;
    }

    public void setTen_ch(String ten_ch) {
        this.ten_ch = ten_ch;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public all_staff(String id, String hoten, String vitri, String ch_id, String ten_ch, Timestamp birthday,
            String dia_chi) {
        this.id = id;
        this.hoten = hoten;
        this.vitri = vitri;
        this.ch_id = ch_id;
        this.ten_ch = ten_ch;
        this.birthday = birthday;
        this.dia_chi = dia_chi;
    }

    public all_staff() {
    }
}

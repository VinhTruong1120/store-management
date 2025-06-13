package com.projects.cnpm.controller.requestbody;

import java.sql.Timestamp;


public class staff {

    private String id;


    private String hoten;

    private String vitri;
    

    private String id_ch;


    

    private Timestamp birthday;

    private String dia_chi;

    public staff() {
    }

    public staff(String id, String hoten, String vitri, String id_ch, Timestamp birthday, String dia_chi) {
        this.id = id;
        this.hoten = hoten;
        this.vitri = vitri;
        this.id_ch = id_ch;
        this.birthday = birthday;
        this.dia_chi = dia_chi;
    }

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
    public String getId_ch() {
        return id_ch;
    }

    public void setId_ch(String id_ch) {
        this.id_ch = id_ch;
    }
}

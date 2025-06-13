package com.projects.cnpm.DAO.Entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Staff")
public class staff_entity {
    
    @Id
    @Column(name = "staff_id",columnDefinition = "char(10)")
    private String id;

    @Column(name = "hoten")
    private String hoten;

    @Column(name = "vitri")
    private String vitri;
    
    @ManyToOne
    @JoinColumn(name = "store_id",foreignKey = @ForeignKey(name = "FK_Staff_store"))
    private cuahang_entity cua_hang;

    @Column(name = "birthday")
    private Timestamp birthday;

    @Column(name = "dia_chi")
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

    public cuahang_entity getCua_hang() {
        return cua_hang;
    }

    public void setCua_hang(cuahang_entity cua_hang) {
        this.cua_hang = cua_hang;
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
}

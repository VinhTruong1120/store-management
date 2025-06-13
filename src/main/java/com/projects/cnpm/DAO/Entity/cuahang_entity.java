package com.projects.cnpm.DAO.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;

import java.util.List;


@Entity
@Table(name = "cua_hang")
public class cuahang_entity {

    @Id
    @Column(name = "Store_ID", columnDefinition = "char(10)", length = 10)
    private String store_id;

    @Column(name = "Ten_cua_hang")
    private String ten_cua_hang;

    @Column(name = "dia_chi")
    private String dia_chi;
    

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    @ElementCollection
    @CollectionTable(name = "cua_hang_contact", joinColumns = @JoinColumn(name = "Store_ID"))
    @Column(name = "contact")
    private List<String> contact = new ArrayList<>();


    @OneToMany(mappedBy = "cua_hang")
    private List<phieu_yeu_cau_nguyen_lieu_entity> phieu_yeu_cau_nguyen_lieu;

    @ManyToOne
    @JoinColumn(name = "Manager",foreignKey = @ForeignKey(name = "FK_Store_manager"),nullable = true)
    private  nhanvien_entity manager;

    @OneToMany(mappedBy = "store")
    private List<nhanvien_entity> nhanvien;

    @OneToMany(mappedBy = "store")
    private List<don_hang_entity> don_hang;

    @OneToMany(mappedBy = "id.cua_hang")
    private List<kho_entity> kho;


    public  cuahang_entity(){}

    public nhanvien_entity getManager() {
        return manager;
    }

    public void setManager(nhanvien_entity manager) {
        this.manager = manager;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getTen_cua_hang() {
        return ten_cua_hang;
    }

    public void setTen_cua_hang(String ten_cua_hang) {
        this.ten_cua_hang = ten_cua_hang;
    }

    public List<String> getContact() {
        return contact;
    }

    public void setContact(List<String> contact) {
        this.contact = contact;
    }

    public List<nhanvien_entity> getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(List<nhanvien_entity> nhanvien) {
        this.nhanvien = nhanvien;
    }

    public List<don_hang_entity> getDon_hang() {
        return don_hang;
    }

    public void setDon_hang(List<don_hang_entity> don_hang) {
        this.don_hang = don_hang;
    }

   
    @OneToMany(mappedBy = "cua_hang")
    List<staff_entity> ds_staff;


}

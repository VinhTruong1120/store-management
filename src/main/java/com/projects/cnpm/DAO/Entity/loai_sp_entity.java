package com.projects.cnpm.DAO.Entity;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "loai_sp")
public class loai_sp_entity {

    @Id
    @Column(name = "ma_loai",length = 10,columnDefinition = "char(10)")
    private String ma_loai;

    @Column(name = "ten_loai")
    private  String ten_loai;

    @OneToMany(mappedBy = "loai")
    private List<san_pham_entity> san_pham;



    public loai_sp_entity() {}


    public String getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(String ma_loai) {
        this.ma_loai = ma_loai;
    }

    public String getTen_loai() {
        return ten_loai;
    }

    public void setTen_loai(String ten_loai) {
        this.ten_loai = ten_loai;
    }

    public List<san_pham_entity> getSan_pham() {
        return san_pham;
    }

    public void setSan_pham(List<san_pham_entity> san_pham) {
        this.san_pham = san_pham;
    }
}

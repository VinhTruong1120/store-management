package com.projects.cnpm.DAO.Entity;

import java.util.List;


import jakarta.persistence.*;


@Entity
@Table(name = "san_pham")
public class san_pham_entity {
    @Id
    @Column(name = "ma_sp",columnDefinition = "char(10)",length = 10)
    private String ma_sp;

    @Column(name = "ten_sp",nullable = false)
    private String ten_sp;

    @Column(name = "don_gia")
    private int don_gia;

    public int getDon_gia() {
        return don_gia;
    }


    public void setDon_gia(int don_gia) {
        this.don_gia = don_gia;
    }

    @Column(name = "mo_ta_sp",columnDefinition = "text")
    private String mo_ta_sp;

    @OneToMany(mappedBy = "id.san_pham")
    private List<chi_tiet_DH_entity> chi_tiet_DH;

    @ManyToOne
    @JoinColumn(name = "ma_loai",foreignKey = @ForeignKey(name = "FK_SP_Loai"))
    private loai_sp_entity loai;


    public san_pham_entity() {}


    public String getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(String ma_sp) {
        this.ma_sp = ma_sp;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    

    public String getMo_ta_sp() {
        return mo_ta_sp;
    }

    public void setMo_ta_sp(String mo_ta_sp) {
        this.mo_ta_sp = mo_ta_sp;
    }

    public List<chi_tiet_DH_entity> getChi_tiet_DH() {
        return chi_tiet_DH;
    }

    public void setChi_tiet_DH(List<chi_tiet_DH_entity> chi_tiet_DH) {
        this.chi_tiet_DH = chi_tiet_DH;
    }



   
    public loai_sp_entity getLoai() {
        return loai;
    }

    public void setLoai(loai_sp_entity loai) {
        this.loai = loai;
    }


}

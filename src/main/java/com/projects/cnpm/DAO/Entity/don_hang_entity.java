package com.projects.cnpm.DAO.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "don_hang")
public class don_hang_entity {
    @Id
    @Column(name = "ma_don",columnDefinition = "char(10)",length = 10)
    private String ma_don;

    @Column(name = "ngay_nhan", nullable = false)
    private Timestamp ngay_nhan;
    @Column(name =  "trang_thai",nullable = false)
    private String trang_thai;

    public String getTrang_thai() {
        return trang_thai;
    }

    @Column(name = "thanhTien",nullable = false)
    private int ThanhTien;

    public int getThanhTien() {
        return ThanhTien;
    }



    public void setThanhTien(int thanhTien) {
        ThanhTien = thanhTien;
    }



    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    @Column(name = "ngay_hoang_thanh")
    private Timestamp ngay_hoang_thanh;
   
    @ManyToOne
    @JoinColumn(name = "store_id",foreignKey = @ForeignKey(name = "FK_store_dh"))
    private cuahang_entity store;

    @ManyToOne
    @JoinColumn(name = "ma_nv",foreignKey = @ForeignKey(name= "FK_nv_dh"))
    private nhanvien_entity nv;

    @OneToMany(mappedBy = "id.don_hang")
    private List<chi_tiet_DH_entity> chi_tiet_DH;

    public don_hang_entity(){}



    public String getMa_don() {
        return ma_don;
    }

    public void setMa_don(String ma_don) {
        this.ma_don = ma_don;
    }

    public Timestamp getNgay_nhan() {
        return ngay_nhan;
    }

    public void setNgay_nhan(Timestamp ngay_nhan) {
        this.ngay_nhan = ngay_nhan;
    }


    public cuahang_entity getStore() {
        return store;
    }

    public void setStore(cuahang_entity store) {
        this.store = store;
    }



    public nhanvien_entity getNv() {
        return nv;
    }

    public void setNv(nhanvien_entity nv) {
        this.nv = nv;
    }

    public List<chi_tiet_DH_entity> getChi_tiet_DH() {
        return chi_tiet_DH;
    }

    public void setChi_tiet_DH(List<chi_tiet_DH_entity> chi_tiet_DH) {
        this.chi_tiet_DH = chi_tiet_DH;
    }
}

package com.projects.cnpm.DAO.Entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "phieu_yeu_cau_nguyen_lieu")
public class phieu_yeu_cau_nguyen_lieu_entity {

    @Id
    @Column(name = "ma_phieu",length = 10,columnDefinition = "char(10)")
    private String ma_phieu;

    @Column(name = "ngay_yeu_cau",nullable = false)
    private Timestamp ngay_yeu_cau;

    @ManyToOne
    @JoinColumn(name = "store_id",foreignKey = @ForeignKey(name = "FK_PhieuNL_CuaHang"))
    private cuahang_entity cua_hang;

    public String getMa_phieu() {
        return ma_phieu;
    }

    public void setMa_phieu(String ma_phieu) {
        this.ma_phieu = ma_phieu;
    }

    public Timestamp getNgay_yeu_cau() {
        return ngay_yeu_cau;
    }

    public void setNgay_yeu_cau(Timestamp ngay_yeu_cau) {
        this.ngay_yeu_cau = ngay_yeu_cau;
    }

    public cuahang_entity getCua_hang() {
        return cua_hang;
    }

    public void setCua_hang(cuahang_entity cua_hang) {
        this.cua_hang = cua_hang;
    }

    @PrePersist
    public void generateID(){
        if (this.ma_phieu == null) {
            LocalDateTime dateTime = ngay_yeu_cau.toLocalDateTime();
            Month month = dateTime.getMonth();
            int day = dateTime.getDayOfMonth();
            int year = dateTime.getYear();
            this.ma_phieu = "P" + day + year + month.getValue() ;
        }
    }
}

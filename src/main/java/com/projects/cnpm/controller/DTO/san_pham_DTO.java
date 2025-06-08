package com.projects.cnpm.controller.DTO;

public class san_pham_DTO {
    private String ma_sp;
    private String ten_sp;
    private double don_gia;
    private String loai_sp;
    private String mo_ta;

    public san_pham_DTO(String ma_sp, String ten_sp, double don_gia, String loai_sp, String mo_ta) {
        this.ma_sp = ma_sp;
        this.ten_sp = ten_sp;
        this.don_gia = don_gia;
        this.loai_sp = loai_sp;
        this.mo_ta = mo_ta;
    }

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

    public double getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(double don_gia) {
        this.don_gia = don_gia;
    }

    public String getLoai_sp() {
        return loai_sp;
    }

    public void setLoai_sp(String loai_sp) {
        this.loai_sp = loai_sp;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }
}

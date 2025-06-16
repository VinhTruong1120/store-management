package com.projects.cnpm.controller.DTO;

public class chi_tiet {
    private String ma_sp;
    private String ten_sp;
    private int don_gia;
    private int so_luong;
    public chi_tiet() {
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
    public int getDon_gia() {
        return don_gia;
    }
    public void setDon_gia(int don_gia) {
        this.don_gia = don_gia;
    }
    public int getSo_luong() {
        return so_luong;
    }
    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }
    public chi_tiet(String ma_sp, String ten_sp, int don_gia, int so_luong) {
        this.ma_sp = ma_sp;
        this.ten_sp = ten_sp;
        this.don_gia = don_gia;
        this.so_luong = so_luong;
    } 
}

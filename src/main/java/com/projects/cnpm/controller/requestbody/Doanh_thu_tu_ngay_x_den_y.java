package com.projects.cnpm.controller.requestbody;

import java.sql.Timestamp;

public class Doanh_thu_tu_ngay_x_den_y {
    private Timestamp ngay_bat_dau;
    private Timestamp ngay_kt;
    public Doanh_thu_tu_ngay_x_den_y() {
    }
    public Doanh_thu_tu_ngay_x_den_y(Timestamp ngay_bat_dau, Timestamp ngay_kt) {
        this.ngay_bat_dau = ngay_bat_dau;
        this.ngay_kt = ngay_kt;
    }
    public Timestamp getNgay_bat_dau() {
        return ngay_bat_dau;
    }
    public void setNgay_bat_dau(Timestamp ngay_bat_dau) {
        this.ngay_bat_dau = ngay_bat_dau;
    }
    public Timestamp getNgay_kt() {
        return ngay_kt;
    }
    public void setNgay_kt(Timestamp ngay_kt) {
        this.ngay_kt = ngay_kt;
    }

}

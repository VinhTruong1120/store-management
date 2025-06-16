package com.projects.cnpm.controller.DTO;

import java.util.List;

public class lay_CTDH {
    private int tong_tien;
    private List<chi_tiet> chi_tiet;
    public int getTong_tien() {
        return tong_tien;
    }
    public void setTong_tien(int tong_tien) {
        this.tong_tien = tong_tien;
    }
    public List<chi_tiet> getChi_tiet() {
        return chi_tiet;
    }
    public void setChi_tiet(List<chi_tiet> chi_tiet) {
        this.chi_tiet = chi_tiet;
    }
    public lay_CTDH(int tong_tien, List<com.projects.cnpm.controller.DTO.chi_tiet> chi_tiet) {
        this.tong_tien = tong_tien;
        this.chi_tiet = chi_tiet;
    }
    public lay_CTDH() {
    }
}

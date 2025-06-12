package com.projects.cnpm.controller.DTO;

import java.sql.Timestamp;

public class doanh_thu_x_y_DTO {
    
    private Timestamp ngayNhan;
    private Long tongTien;

    public Timestamp getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Timestamp ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public Long getTongTien() {
        return tongTien;
    }

    public void setTongTien(Long tongTien) {
        this.tongTien = tongTien;
    }

    public doanh_thu_x_y_DTO(Timestamp ngayNhan, Long tongTien) {
        this.ngayNhan = ngayNhan;
        this.tongTien = tongTien;
    }
}

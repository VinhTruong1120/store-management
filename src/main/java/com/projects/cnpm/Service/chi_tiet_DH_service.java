package com.projects.cnpm.Service;

import com.projects.cnpm.DAO.Entity.Embeddable.CTDH_ID;
import com.projects.cnpm.DAO.Entity.chi_tiet_DH_entity;
import com.projects.cnpm.DAO.Entity.don_hang_entity;
import com.projects.cnpm.Repository.chi_tiet_DH_repository;
import com.projects.cnpm.controller.DTO.doanh_thu_x_y_DTO;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class chi_tiet_DH_service extends BaseSerive<chi_tiet_DH_entity, CTDH_ID, chi_tiet_DH_repository> {

    @Autowired
    public void setRepository(chi_tiet_DH_repository ct_dh_Repository) {
        super.setRepository(ct_dh_Repository);
    }

    @Transactional
    public void them_chi_tiet_DH(CTDH_ID ctdh_id, int so_luong) {
        chi_tiet_DH_entity chi_tiet_DH = new chi_tiet_DH_entity();
        chi_tiet_DH.setSo_luong(so_luong);
        chi_tiet_DH.setId(ctdh_id);
        repositoty.save(chi_tiet_DH);
    }

    public long thanh_tien_theo_don(String ma_don) {
        return repositoty.thanh_tien_theo_don_hoang_thanh(ma_don);
    }

    public List<chi_tiet_DH_entity> chi_tiet_bangma_don(String ma_don) {
        return repositoty.fingby_DHID(ma_don);
    }

    public List<doanh_thu_x_y_DTO> doanh_thu_x_den_y(Timestamp start, Timestamp end) {
       return repositoty.doanhThuTheoNgay(start,end);
    }

    public List<doanh_thu_x_y_DTO> doanh_thu_xx_den_yy() {
       return repositoty.thanh_tien_theo_don_hoang_thanh1();
    }

    public List<chi_tiet_DH_entity> all_chi_tiet_theo_don(don_hang_entity dh){
        return repositoty.tim_chi_tiet_theo_don(dh);
    }
}

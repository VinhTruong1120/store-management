package com.projects.cnpm.Service;

import com.projects.cnpm.DAO.Entity.Embeddable.CTDH_ID;
import com.projects.cnpm.DAO.Entity.chi_tiet_DH_entity;

import com.projects.cnpm.Repository.chi_tiet_DH_repository;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class chi_tiet_DH_service extends BaseSerive<chi_tiet_DH_entity,CTDH_ID,chi_tiet_DH_repository> {

    @Autowired
    public void setRepository(chi_tiet_DH_repository ct_dh_Repository){
        super.setRepository(ct_dh_Repository);
    }

    @Transactional
    public void them_chi_tiet_DH(CTDH_ID ctdh_id,int so_luong) {
        chi_tiet_DH_entity chi_tiet_DH = new chi_tiet_DH_entity();
        chi_tiet_DH.setSo_luong(so_luong);
        chi_tiet_DH.setId(ctdh_id);
        repositoty.save(chi_tiet_DH);
    }

    public long thanh_tien_theo_don(String ma_don){
        return repositoty.thanh_tien_theo_don_hoang_thanh(ma_don);
    }
    

    public List<chi_tiet_DH_entity> chi_tiet_bangma_don(String ma_don){
        return repositoty.fingby_DHID(ma_don);
    }
}

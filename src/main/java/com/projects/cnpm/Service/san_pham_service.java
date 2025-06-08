package com.projects.cnpm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.loai_sp_entity;
import com.projects.cnpm.DAO.Entity.san_pham_entity;
import com.projects.cnpm.Repository.san_pham_repository;

import jakarta.transaction.Transactional;

@Service
public class san_pham_service extends BaseSerive<san_pham_entity,String,san_pham_repository>{

    @Autowired
    public void setRepository(san_pham_repository repository){
        super.setRepository(repository);
    }

    @Transactional
    public void them_san_pham(String id,String ten_sp,int don_gia,String mo_ta,loai_sp_entity loai){
        san_pham_entity moi = new san_pham_entity();
        moi.setMa_sp(id);
        moi.setTen_sp(ten_sp);
        moi.setDon_gia(don_gia);
        moi.setMo_ta_sp(mo_ta);
        moi.setLoai(loai);
        repositoty.save(moi);
    }
}

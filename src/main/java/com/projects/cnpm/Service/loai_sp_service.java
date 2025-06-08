package com.projects.cnpm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.loai_sp_entity;
import com.projects.cnpm.Repository.loai_sp_repository;

import jakarta.transaction.Transactional;

@Service
public class loai_sp_service extends BaseSerive<loai_sp_entity,String,loai_sp_repository> {
    
    
    @Autowired
    public void setRepository(loai_sp_repository repository){
        super.setRepository(repository);
    }

    @Transactional
    public void them_loai_sp(String id,String ten_loai){
        loai_sp_entity LoaiSp = new loai_sp_entity();
        LoaiSp.setMa_loai(id);
        LoaiSp.setTen_loai(ten_loai);
        repositoty.save(LoaiSp);
    }

}

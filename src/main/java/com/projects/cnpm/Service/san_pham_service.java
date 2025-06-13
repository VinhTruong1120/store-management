package com.projects.cnpm.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.loai_sp_entity;
import com.projects.cnpm.DAO.Entity.san_pham_entity;
import com.projects.cnpm.Repository.chi_tiet_DH_repository;
import com.projects.cnpm.Repository.loai_sp_repository;
import com.projects.cnpm.Repository.san_pham_repository;
import com.projects.cnpm.controller.requestbody.path_product;

import jakarta.transaction.Transactional;

@Service
public class san_pham_service extends BaseSerive<san_pham_entity,String,san_pham_repository>{
    @Autowired
    loai_sp_repository Loai_sp_repository;
    @Autowired
    chi_tiet_DH_repository CTDH_repo;
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

    @Transactional
    public int patch_product(path_product patch){
        Optional<san_pham_entity> sp_opt = repositoty.findById(patch.getId());
        if (!sp_opt.isPresent()) {
            return 0;
        }
        Optional <loai_sp_entity> loai_Optional = Loai_sp_repository.findById(patch.getLoai_id());
        if (!loai_Optional.isPresent()) {
            return 0;
        }
        san_pham_entity sp = sp_opt.get();
        sp.setTen_sp(patch.getTen());
        sp.setDon_gia(patch.getPrice());
        sp.setMo_ta_sp(patch.getMota());
        sp.setLoai(loai_Optional.get());
        
        repositoty.save(sp);
        return 1;
    }

    @Transactional
    public int del_product(String id){
        
        Optional<san_pham_entity> sp_opt = repositoty.findById(id);
        if (!sp_opt.isPresent()) {
            return 0;
        }
        san_pham_entity sp = sp_opt.get();
        CTDH_repo.deleteBySanPhamId(id);;
        sp.setLoai(null);
        repositoty.save(sp);
        repositoty.deleteById(id);
        return 1;
    }
}

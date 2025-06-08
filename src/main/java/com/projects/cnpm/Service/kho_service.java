package com.projects.cnpm.Service;

import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.kho_entity;
import com.projects.cnpm.DAO.Entity.Embeddable.kho_id;
import com.projects.cnpm.Repository.kho_repository;

import jakarta.transaction.Transactional;

@Service
public class kho_service extends BaseSerive<kho_entity,kho_id,kho_repository>{

    @Transactional
    public void tao_kho(kho_id id,int so_luong_ton){
        kho_entity kho = new kho_entity();
        kho.setId(id);
        kho.setSo_luong_ton(so_luong_ton);
        repositoty.save(kho);
    }

    @Transactional // 0 là thất bại, 1 là thành công 
    public int cap_nhat_so_luong(kho_id id,int so_luong_ton){
        if (kiemTraTonTai(id)) {
            kho_entity kho = this.timTheoId(id);
            kho.setSo_luong_ton(so_luong_ton);
            repositoty.save(kho);
            return 1;
        }
        else{
            return 0;
        }
    }
}

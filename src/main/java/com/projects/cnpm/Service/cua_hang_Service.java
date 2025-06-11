package com.projects.cnpm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.Repository.cuahang_repository;

import jakarta.transaction.Transactional;

@Service
public class cua_hang_Service extends BaseSerive<cuahang_entity,String,cuahang_repository> {

  

    @Autowired
    public void setRepository(cuahang_repository repository){
        super.setRepository(repository);
    }

    @Transactional
    public void them_cua_hang(String store_id,String ten_cua_hang,String dia_chi, List<String> contact)
    {
        cuahang_entity ch = new cuahang_entity();
        ch.setStore_id(store_id);
        ch.setDia_chi(dia_chi);
        ch.setTen_cua_hang(ten_cua_hang);
        ch.setContact(contact);
        repositoty.save(ch);
    }

    @Transactional  // 1 là cập nhật thành công
    public int cap_nhat_manager(nhanvien_entity nv, cuahang_entity ch){
        if (!this.kiemTraTonTai(ch.getStore_id()) || ch == null) {
            return 0;
        }
        if (nv == null) {
            ch.setManager(null);
            repositoty.save(ch);
            return 1;
        }
        ch.setManager(nv);
        repositoty.save(ch);
        return 1;
    }

     public nhanvien_entity nv_manager_cua_ch(String store_id){
        return repositoty.lay_nv_manager(store_id);
     }

     public List<cuahang_entity> Lay_all_cua_hang(){
        return repositoty.findAll();
     }
}

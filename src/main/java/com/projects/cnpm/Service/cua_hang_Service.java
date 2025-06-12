package com.projects.cnpm.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.Repository.cuahang_repository;
import com.projects.cnpm.controller.requestbody.patch_Store;

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

     @Transactional
     public int xoa_cua_hang(String id){
        Optional<cuahang_entity>  cua_hangObject = repositoty.findById(id);
        if (!cua_hangObject.isPresent()) {
            return 0;
        }
        cuahang_entity ch= cua_hangObject.get();
        ch.getNhanvien().clear();
        ch.setManager(null);
        ch.getDon_hang().clear();
        ch.getContact().clear();
        repositoty.save(ch);
        repositoty.deleteById(id);
        return 1;
     }

     @Transactional
     public int patch_store(patch_Store path){
        Optional<cuahang_entity> ch_obt = repositoty.findById(path.getId());
        if (!ch_obt.isPresent()) {
            return 0;
        }
        cuahang_entity ch = ch_obt.get();
        ch.setTen_cua_hang(path.getStore_name());
        ch.setDia_chi(path.getAddress());
        repositoty.save(ch);
        return 1;
     }
}

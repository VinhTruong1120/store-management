package com.projects.cnpm.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.DAO.Entity.role_entity;
import com.projects.cnpm.Repository.nhanvien_repository;
import com.projects.cnpm.controller.requestbody.nhan_vien_request;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;

@Service
public class nhanvien_service extends BaseSerive<nhanvien_entity,String,nhanvien_repository> {

    @Autowired
    public void setRepository(nhanvien_repository repository){
        super.setRepository(repository);
    }

    @Transactional
    public void them_nhan_vien(String ma_nv,String ten,String username,String passwords,String dia_chi,int luong,Timestamp birthday,List<String> lien_he,cuahang_entity cua_hang,List<role_entity> roles){
        nhanvien_entity moi = new nhanvien_entity();
        moi.setMa_nv(ma_nv);
        moi.setTen(ten);
        moi.setUsername(username);
        moi.setPasswords(passwords);
        moi.setDia_chi(dia_chi);
        moi.setLuong(luong);
        moi.setBirthday(birthday);
        moi.setLien_he(lien_he);
        moi.setStore(cua_hang);
        moi.setRoles(roles);
        repositoty.save(moi);
    };

    public Optional<nhanvien_entity> findbySDT(String str){
        List<nhanvien_entity> tim = repositoty.existsByContact(str);
        if (tim.isEmpty()) {
            return  Optional.empty();
        }
        return Optional.of(tim.get(0));
    }

    public Optional<nhanvien_entity> findbyUsername(String username){
        return Optional.ofNullable(repositoty.findByUsername(username));
    }

    

    @Transactional //trả về 1 -> thêm thành công, trả về 0 -> thêm không thành công
    public int them_Role(nhanvien_entity nv, role_entity new_role){
        //kiểm tra vai  trò mới đã có hay chưa
        if (!nv.getRoles().contains(new_role)) {
            nv.getRoles().add(new_role);
            repositoty.save(nv);
            return 1;
        }
        return 0;
    }

    @Transactional //Trả về 1 nếu nhân viên không có quyền đó để xoá 
    public int xoa_role(nhanvien_entity nv, role_entity del_role){
        if (!nv.getRoles().contains(del_role)) {
            return 1;
        }
        else{
            nv.getRoles().remove(del_role);
            repositoty.save(nv);
            return 0;
        }
    }

    @Transactional //trả về 0 nhân viên không tồn tại, 1 nếu cập nhật thành công 
    public int cap_nhat_thong_tin(nhan_vien_request TT_moi){
        Optional<nhanvien_entity> nvOptional = repositoty.findById(TT_moi.getMa_nv());
        if (!nvOptional.isPresent()) {
            return 0;
        }
        else{
            nhanvien_entity nv = nvOptional.get();
            nv.setLien_he(TT_moi.getLien_he());
            nv.setTen(TT_moi.getTen());
            nv.setDia_chi(TT_moi.getDia_chi());
            nv.setUsername(TT_moi.getUsername());
            nv.setPasswords(TT_moi.getPasswords());
            nv.setLuong(TT_moi.getLuong());
            repositoty.save(nv);
            return 1;
        }
    }

    @Transactional
    public int xoa_nv(String id){
        System.out.println(id);
        Optional<nhanvien_entity> nv = repositoty.findById(id);
        if(!nv.isPresent()){
            return 0;
        }
        nhanvien_entity Del_NV = nv.get();
        Del_NV.getRoles().clear();
        Del_NV.setStore(null);
        repositoty.save(Del_NV);
        repositoty.deleteById(id);;
        return 1;
    }
}


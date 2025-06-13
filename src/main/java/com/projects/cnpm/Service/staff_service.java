package com.projects.cnpm.Service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.staff_entity;
import com.projects.cnpm.Repository.staff_repository;

import jakarta.transaction.Transactional;

@Service
public class staff_service extends BaseSerive<staff_entity,String,staff_repository> {
    
    @Transactional
    public int create_staff(String id, String ten, String vitri, cuahang_entity ch, Timestamp ngaysinh, String dia_chi ){
        Optional<staff_entity> staff_opt = repositoty.findById(id);
        if (staff_opt.isPresent()) {
            return 0;
        }
        staff_entity staff = new staff_entity();
        staff.setId(id);
        staff.setCua_hang(ch);
        staff.setBirthday(ngaysinh);
        staff.setDia_chi(dia_chi);
        staff.setHoten(ten);
        staff.setVitri(vitri);
        repositoty.save(staff);
        return 1;
    }

    @Transactional
    public int update_staff(String id, String ten, String vitri, cuahang_entity ch, Timestamp ngaysinh, String dia_chi ){
        Optional<staff_entity> staff_opt = repositoty.findById(id);
        if (!staff_opt.isPresent()) {
            return 0;
        }
        staff_entity staff = new staff_entity();
        staff.setId(id);
        staff.setCua_hang(ch);
        staff.setBirthday(ngaysinh);
        staff.setDia_chi(dia_chi);
        staff.setHoten(ten);
        staff.setVitri(vitri);
        repositoty.save(staff);
        return 1;
    }

    @Transactional
    public int del_staff(String id){
        Optional<staff_entity> staff_opt = repositoty.findById(id);
        if (!staff_opt.isPresent()) {
            return 0;
        }
        staff_entity staff = staff_opt.get();
        staff.setCua_hang(null);
        repositoty.save(staff);
        repositoty.deleteById(id);
        return 1;
    }

}

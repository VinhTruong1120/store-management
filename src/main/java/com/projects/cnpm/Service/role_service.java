package com.projects.cnpm.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.role_entity;
import com.projects.cnpm.Repository.role_repository;

import jakarta.transaction.Transactional;

@Service
public class role_service extends BaseSerive<role_entity,String,role_repository>{
    
   

    @Autowired
    public void setRepository(role_repository repository){
        super.setRepository(repository);
    }

    @Transactional
    public void them_role(String id, String ten_role){
        role_entity moi = new role_entity();
        moi.setRole_id(id);
        moi.setTen_role(ten_role);
        repositoty.save(moi);
    }

    
}

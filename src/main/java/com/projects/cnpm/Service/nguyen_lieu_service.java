package com.projects.cnpm.Service;

import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.nguyen_lieu_entity;
import com.projects.cnpm.Repository.nguyen_lieu_repository;

import jakarta.transaction.Transactional;

@Service
public class nguyen_lieu_service extends BaseSerive<nguyen_lieu_entity,String,nguyen_lieu_repository>{

    @Transactional
    public void tao_nguyen_lieu_moi(String ma_nguye_lieu,String ten_nguyen_lieu){
        nguyen_lieu_entity nl = new nguyen_lieu_entity();
        nl.setMa_nguyen_lieu(ten_nguyen_lieu);
        nl.setTen_nguyen_lieu(ten_nguyen_lieu);
        repositoty.save(nl);
    }
}

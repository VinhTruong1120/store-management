package com.projects.cnpm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.cnpm.DAO.Entity.staff_entity;

@Repository
public interface staff_repository extends JpaRepository<staff_entity,String>{
    
}

package com.projects.cnpm.Repository;

import com.projects.cnpm.DAO.Entity.role_entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;






@Repository
public interface role_repository extends JpaRepository<role_entity,String> {

    
}

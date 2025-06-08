package com.projects.cnpm.Repository;

import com.projects.cnpm.DAO.Entity.san_pham_entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface san_pham_repository extends JpaRepository<san_pham_entity,String> {
    
}

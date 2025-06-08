package com.projects.cnpm.Repository;

import com.projects.cnpm.DAO.Entity.loai_sp_entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loai_sp_repository extends JpaRepository<loai_sp_entity,String> {
}

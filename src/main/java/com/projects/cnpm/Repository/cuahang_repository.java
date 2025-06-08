package com.projects.cnpm.Repository;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface cuahang_repository  extends JpaRepository<cuahang_entity,String> {

    @Query("Select ch.manager from cuahang_entity ch where ch.store_id = :store_id")
    public nhanvien_entity lay_nv_manager(@Param("store_id")String store_id);
}

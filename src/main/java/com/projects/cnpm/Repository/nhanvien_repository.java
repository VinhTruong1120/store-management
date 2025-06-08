package com.projects.cnpm.Repository;

import com.projects.cnpm.DAO.Entity.nhanvien_entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface nhanvien_repository  extends JpaRepository<nhanvien_entity,String> {

     @Query("SELECT nv FROM nhanvien_entity nv JOIN nv.lien_he lh WHERE lh = :soDienThoai")
    List<nhanvien_entity> existsByContact(@Param("soDienThoai") String soDienThoai);

    nhanvien_entity findByUsername(String username);

}

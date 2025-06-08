package com.projects.cnpm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.cnpm.DAO.Entity.kho_entity;
import com.projects.cnpm.DAO.Entity.Embeddable.kho_id;

public interface kho_repository extends JpaRepository<kho_entity,kho_id>{

}

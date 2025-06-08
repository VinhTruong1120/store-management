package com.projects.cnpm.Repository;

import com.projects.cnpm.DAO.Entity.Embeddable.CTDH_ID;
import com.projects.cnpm.DAO.Entity.chi_tiet_DH_entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface chi_tiet_DH_repository extends JpaRepository<chi_tiet_DH_entity, CTDH_ID> {


    @Query("SELECT SUM(ctdh.so_luong * ctdh.id.san_pham.don_gia) " +
            "FROM chi_tiet_DH_entity ctdh " +
            "WHERE ctdh.id.don_hang.ma_don = :ma_don")
    Long thanh_tien_theo_don_hoang_thanh(@Param("ma_don") String ma_don);

    @Query("SELECT ctdh FROM chi_tiet_DH_entity ctdh WHERE ctdh.id.don_hang.ma_don = :ma_don")
    List<chi_tiet_DH_entity> fingby_DHID(@Param("ma_don") String ma_don);
}



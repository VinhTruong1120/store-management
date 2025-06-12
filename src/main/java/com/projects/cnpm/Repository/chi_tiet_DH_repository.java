package com.projects.cnpm.Repository;

import com.projects.cnpm.DAO.Entity.Embeddable.CTDH_ID;
import com.projects.cnpm.controller.DTO.doanh_thu_x_y_DTO;
import com.projects.cnpm.DAO.Entity.chi_tiet_DH_entity;

import java.sql.Timestamp;
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

    
    @Query("SELECT new com.projects.cnpm.controller.DTO.doanh_thu_x_y_DTO(" +
       "ctdh.id.don_hang.ngay_nhan, " +
       "SUM(ctdh.so_luong * ctdh.id.san_pham.don_gia)) " +
       "FROM chi_tiet_DH_entity ctdh " +
       "WHERE ctdh.id.don_hang.ngay_nhan BETWEEN :ngay_BD AND :ngay_KT " +
       "GROUP BY ctdh.id.don_hang.ngay_nhan")
        List<doanh_thu_x_y_DTO> doanhThuTheoNgay(@Param("ngay_BD") Timestamp ngayBD,
                                           @Param("ngay_KT") Timestamp ngayKT);

        @Query("SELECT new com.projects.cnpm.controller.DTO.doanh_thu_x_y_DTO(" +
       "ctdh.id.don_hang.ngay_nhan, " +
       "SUM(ctdh.so_luong * ctdh.id.san_pham.don_gia)) "  +
        "FROM chi_tiet_DH_entity ctdh " +
        "GROUP BY ctdh.id.don_hang.ma_don")
        List <doanh_thu_x_y_DTO> thanh_tien_theo_don_hoang_thanh1();



}

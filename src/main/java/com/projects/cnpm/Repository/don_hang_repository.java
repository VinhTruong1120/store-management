package com.projects.cnpm.Repository;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.don_hang_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.controller.DTO.doanh_thu_x_y_DTO;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface don_hang_repository extends JpaRepository<don_hang_entity, String> {

        @Query("Select dh from don_hang_entity dh where function('MONTH',dh.ngay_hoang_thanh) = :month and function('YEAR',dh.ngay_hoang_thanh) = :year and dh.store = :store and dh.trang_thai = 'hoàng thành'")
        List<don_hang_entity> Don_thanh_cong_cua_CH_theo_thang(@Param("month") int mont, @Param("year") int year,
                        @Param("store") cuahang_entity store);

        @Query("Select dh.store from don_hang_entity dh where dh.ma_don = :ma_don")
        cuahang_entity lay_ch_tu_don(@Param("ma_don") String ma_don);

        @Query("Select dh.nv from don_hang_entity dh where dh.ma_don = :ma_don")
        nhanvien_entity lay_nv_tu_don(@Param("ma_don") String ma_don);

        @Query("Select dh from don_hang_entity  dh where  function('MONTH',dh.ngay_hoang_thanh) = :month and" +
                        " function('YEAR',dh.ngay_hoang_thanh) = :year and function('DAY',dh.ngay_hoang_thanh) = :day and "
                        +
                        "dh.nv = :nv")
        List<don_hang_entity> lay_don_nv_theo_ngay(@Param("day") int day, @Param("month") int month,
                        @Param("year") int year, @Param("nv") nhanvien_entity nv);

        @Query("SELECT new com.projects.cnpm.controller.DTO.doanh_thu_x_y_DTO( " +
                        "dh.ngay_nhan, SUM(dh.ThanhTien)) " +
                        "FROM don_hang_entity dh " +
                        "WHERE dh.trang_thai = 'Đã hoàn thành' " +
                        "GROUP BY dh.ngay_nhan")
        List<doanh_thu_x_y_DTO> thanh_tien_theo_ngay_nhan();

        @Query("SELECT new com.projects.cnpm.controller.DTO.doanh_thu_x_y_DTO(" +
                        "dh.ngay_nhan, SUM(dh.ThanhTien)) " +
                        "FROM don_hang_entity dh " +
                        "WHERE dh.ngay_nhan BETWEEN :ngay_BD AND :ngay_KT " +
                        "GROUP BY dh.ngay_nhan")
        List<doanh_thu_x_y_DTO> doanhThuTheoNgay(@Param("ngay_BD") Timestamp ngayBD,
                        @Param("ngay_KT") Timestamp ngayKT);

        @Query("Select dh " +
                        "FROM don_hang_entity dh " +
                        "WHERE (dh.ngay_nhan BETWEEN :ngay_BD AND :ngay_KT) and dh.store.store_id = :store_id ")
        List<don_hang_entity> lich_su_don_hang(@Param("ngay_BD") Timestamp ngayBD,
                        @Param("ngay_KT") Timestamp ngayKT,
                        @Param("store_id") String Store_id);

        @Query("SELECT dh " +
                        "FROM don_hang_entity dh " +
                        "WHERE dh.ngay_nhan BETWEEN :ngayBD AND :ngayKT")
        List<don_hang_entity> lich_su_don_hang2(
                        @Param("ngayBD") Timestamp ngayBD,
                        @Param("ngayKT") Timestamp ngayKT);
}

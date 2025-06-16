package com.projects.cnpm.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.don_hang_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.Repository.don_hang_repository;
import com.projects.cnpm.controller.DTO.doanh_thu_x_y_DTO;

import jakarta.transaction.Transactional;

@Service
public class don_hang_service extends BaseSerive<don_hang_entity,String,don_hang_repository> {

   

    @Autowired
    public void setRepository(don_hang_repository repository){
        super.setRepository(repository);
    }

    @Transactional
    public void them_don_hang(String ID,Timestamp ngay_nhan,String trang_thai,cuahang_entity cuahang,nhanvien_entity nv,int thanh_tien){
        don_hang_entity moi = new don_hang_entity();
        moi.setMa_don(ID);
        moi.setNgay_nhan(ngay_nhan);
        moi.setStore(cuahang);
        moi.setNv(nv);
        moi.setTrang_thai(trang_thai);
        moi.setThanhTien(thanh_tien);
        repositoty.save(moi);
    }

    public List<don_hang_entity> findAllbyMonthYearID(int month,int year,cuahang_entity store){
        return repositoty.Don_thanh_cong_cua_CH_theo_thang(month, year,store);
    }

    @Transactional
    public int cap_nhat_trang_thai_don(String id, String trang_thai){
        if (!this.kiemTraTonTai(id)) {
            return 0;
        }
        don_hang_entity dh = this.timTheoId(id);
        dh.setTrang_thai(trang_thai);
        repositoty.save(dh);
        return 1;
    }

    public nhanvien_entity lay_nv_tu_don(String ma_don){
        return repositoty.lay_nv_tu_don(ma_don);
    }

    public cuahang_entity lay_ch_tu_don(String ma_don){
        return repositoty.lay_ch_tu_don(ma_don);
    }

    public List<doanh_thu_x_y_DTO> doanh_thu_x_den_y(Timestamp start, Timestamp end) {
       return repositoty.doanhThuTheoNgay(start,end);
    }

    public List<doanh_thu_x_y_DTO> doanh_thu_xx_den_yy() {
       return repositoty.thanh_tien_theo_ngay_nhan();
    }

    public List<don_hang_entity> lich_su_don_hang(Timestamp ngay_bd,Timestamp ngay_kt,String Store_ID){
        if (Store_ID == "null" || Store_ID == null) {
            System.out.println("hello");
            return repositoty.lich_su_don_hang2(ngay_bd, ngay_kt);
        }
        return repositoty.lich_su_don_hang(ngay_bd, ngay_kt, Store_ID);
    }
}

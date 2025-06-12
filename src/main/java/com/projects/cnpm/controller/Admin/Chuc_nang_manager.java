package com.projects.cnpm.controller.Admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.don_hang_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.Service.chi_tiet_DH_service;
import com.projects.cnpm.Service.cua_hang_Service;
import com.projects.cnpm.Service.don_hang_service;

import com.projects.cnpm.Service.nhanvien_service;

import com.projects.cnpm.controller.requestbody.Doanh_thu_theo_thang;
import com.projects.cnpm.controller.requestbody.fake_don;

@RestController
@RequestMapping("/api/manager")
public class Chuc_nang_manager extends Chuc_nang_ADMIN_Controller{

    @Autowired
    private cua_hang_Service Cua_hang_Service;

    @Autowired
    private nhanvien_service Nhan_vien_service;

    @Autowired
    private don_hang_service Don_hang_service;
    @Autowired
    private chi_tiet_DH_service Chi_tiet_DH_service;

    private Boolean kiem_tra_manager(String ma_nv, String store_id){
        nhanvien_entity mananger = Cua_hang_Service.nv_manager_cua_ch(store_id);
        if(mananger.getMa_nv().equals(ma_nv)) {
            return true;
        }
        else{
            return false;
        }
    }
    
    @PostMapping("/doanh_thu_theo_thang")
    public ResponseEntity<?> doanh_thu_thang(@RequestBody Doanh_thu_theo_thang request) {
        
        if (Cua_hang_Service.kiemTraTonTai(request.getStore_id()) && Nhan_vien_service.kiemTraTonTai(request.getMa_nv())) {
            if (!this.kiem_tra_manager(request.getMa_nv(), request.getStore_id())) {
                return new ResponseEntity<>("Không có quyền truy cập",HttpStatus.NOT_ACCEPTABLE);
            }
            List<don_hang_entity> don_hang = Don_hang_service.findAllbyMonthYearID(request.getMonth(), request.getYear(), Cua_hang_Service.timTheoId(request.getStore_id()));
            Long doanh_thu = 0L;
            for(don_hang_entity don : don_hang){
                doanh_thu =  doanh_thu + Chi_tiet_DH_service.thanh_tien_theo_don(don.getMa_don());
            }
            Map<String,Object> respone = new HashMap<>();
            respone.put("Doanh Thu", doanh_thu);
            respone.put("Số lượng đơn hàng",don_hang.size());
            return ResponseEntity.ok(respone);
        }
        return new ResponseEntity<> ("Không tìm thấy thông tin",HttpStatus.NOT_FOUND);
    }

    
    @PostMapping("/them-nhieu")
    public String nhanDanhSachDonHang(@RequestBody List<fake_don> danhSachDon) {
        // Xử lý logic, ví dụ in ra log
        for (fake_don don : danhSachDon) {
            nhanvien_entity nv = Nhan_vien_service.timTheoId(don.getNv());
            cuahang_entity ch = Cua_hang_Service.timTheoId(don.getCuahang());
           Don_hang_service.them_don_hang(don.getID(), don.getNgay_nhan(), don.getTrang_thai(), ch, nv, don.getThanh_tien());
        }
        return "Nhận thành công " + danhSachDon.size() + " đơn hàng.";
    }
}

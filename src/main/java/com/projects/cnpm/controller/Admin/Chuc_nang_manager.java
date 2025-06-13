package com.projects.cnpm.controller.Admin;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.don_hang_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.DAO.Entity.staff_entity;
import com.projects.cnpm.Service.chi_tiet_DH_service;
import com.projects.cnpm.Service.cua_hang_Service;
import com.projects.cnpm.Service.don_hang_service;
import com.projects.cnpm.Service.loai_sp_service;
import com.projects.cnpm.Service.nhanvien_service;
import com.projects.cnpm.Service.san_pham_service;
import com.projects.cnpm.Service.staff_service;
import com.projects.cnpm.controller.DTO.all_staff;
import com.projects.cnpm.controller.requestbody.Doanh_thu_theo_thang;
import com.projects.cnpm.controller.requestbody.fake_don;
import com.projects.cnpm.controller.requestbody.san_pham_moi_request;
import com.projects.cnpm.controller.requestbody.staff;
import com.projects.cnpm.controller.requestbody.tao_CH_request;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/manager")
public class Chuc_nang_manager extends Chuc_nang_ADMIN_Controller {

    @Autowired
    private loai_sp_service Loai_sp_service;

    @Autowired
    private san_pham_service San_pham_service;

    @Autowired
    private staff_service Staff_service;

    @Autowired
    private cua_hang_Service Cua_hang_Service;

    @Autowired
    private nhanvien_service Nhan_vien_service;

    @Autowired
    private don_hang_service Don_hang_service;
    @Autowired
    private chi_tiet_DH_service Chi_tiet_DH_service;

    private Boolean kiem_tra_manager(String ma_nv, String store_id) {
        nhanvien_entity mananger = Cua_hang_Service.nv_manager_cua_ch(store_id);
        if (mananger.getMa_nv().equals(ma_nv)) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/doanh_thu_theo_thang")
    public ResponseEntity<?> doanh_thu_thang(@RequestBody Doanh_thu_theo_thang request) {

        if (Cua_hang_Service.kiemTraTonTai(request.getStore_id())
                && Nhan_vien_service.kiemTraTonTai(request.getMa_nv())) {
            if (!this.kiem_tra_manager(request.getMa_nv(), request.getStore_id())) {
                return new ResponseEntity<>("Không có quyền truy cập", HttpStatus.NOT_ACCEPTABLE);
            }
            List<don_hang_entity> don_hang = Don_hang_service.findAllbyMonthYearID(request.getMonth(),
                    request.getYear(), Cua_hang_Service.timTheoId(request.getStore_id()));
            Long doanh_thu = 0L;
            for (don_hang_entity don : don_hang) {
                doanh_thu = doanh_thu + Chi_tiet_DH_service.thanh_tien_theo_don(don.getMa_don());
            }
            Map<String, Object> respone = new HashMap<>();
            respone.put("Doanh Thu", doanh_thu);
            respone.put("Số lượng đơn hàng", don_hang.size());
            return ResponseEntity.ok(respone);
        }
        return new ResponseEntity<>("Không tìm thấy thông tin", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/them-nhieu")
    public String nhanDanhSachDonHang(@RequestBody List<fake_don> danhSachDon) {
        // Xử lý logic, ví dụ in ra log
        for (fake_don don : danhSachDon) {
            nhanvien_entity nv = Nhan_vien_service.timTheoId(don.getNv());
            cuahang_entity ch = Cua_hang_Service.timTheoId(don.getCuahang());
            Don_hang_service.them_don_hang(don.getID(), don.getNgay_nhan(), don.getTrang_thai(), ch, nv,
                    don.getThanh_tien());
        }
        return "Nhận thành công " + danhSachDon.size() + " đơn hàng.";
    }

    public String generate_staff_ID() {
        String staff_id = "NV";
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder(staff_id); // Bắt đầu bằng "NV"
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHARACTERS.length());
            builder.append(CHARACTERS.charAt(index));
        }

        staff_id = builder.toString();
        return staff_id;
    }

    @PostMapping("tao_staff")
    public ResponseEntity<?> create_staff(@RequestBody staff request) {
        cuahang_entity ch = Cua_hang_Service.timTheoId(request.getId_ch());
        int kt = Staff_service.create_staff(generate_staff_ID(), request.getHoten(), request.getVitri(), ch,
                request.getBirthday(), request.getDia_chi());
        if (kt == 0) {
            return new ResponseEntity<>("Tạo không thành công", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok("Tạo thành công");
    }

    @DeleteMapping("xoa_staff")
    public ResponseEntity<?> delete_staff(@RequestParam String id) {
        int kt = Staff_service.del_staff(id);
        if (kt == 0) {
            return new ResponseEntity<>("Xoá thất bại", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("xoá thành công");
    }

    @PostMapping("/lay_staff")
    public ResponseEntity<?> create_staff() {
        List<staff_entity> ds_staff = Staff_service.FindAll();

        // String id, String hoten, String vitri, String ch_id, String ten_ch, Timestamp
        // birthday,String dia_chi
        if (ds_staff.isEmpty()) {
            return new ResponseEntity<>("load thất bại", HttpStatus.NOT_FOUND);
        }

        List<all_staff> ds_res = ds_staff.stream()
                .map(staf -> new all_staff(staf.getId(), staf.getHoten(),
                        staf.getVitri(), staf.getCua_hang().getStore_id(), staf.getCua_hang().getTen_cua_hang(),
                        staf.getBirthday(), staf.getDia_chi()))
                .toList();

        return ResponseEntity.ok(ds_res);
    }

    @PutMapping("/update_staff")
    public ResponseEntity<?> update_staff(@RequestBody staff request) {
        cuahang_entity ch = Cua_hang_Service.timTheoId(request.getId_ch());
        int kt = Staff_service.update_staff(request.getId(), request.getHoten(), request.getVitri(), ch,
                request.getBirthday(), request.getDia_chi());
        if (kt == 0) {
            return new ResponseEntity<>("Cập nhật không thành công", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok("Cập nhật thành công");
    }

    @PostMapping("tao_nhieu_staff")
    public ResponseEntity<?> createMultipleStaff(@RequestBody List<staff> staffList) {
        List<String> failedStaff = new ArrayList<>();

        for (staff request : staffList) {
            cuahang_entity ch = Cua_hang_Service.timTheoId(request.getId_ch());

            if (ch == null) {
                failedStaff.add("Không tìm thấy cửa hàng cho nhân viên: " + request.getHoten());
                continue;
            }

            int kt = Staff_service.create_staff(
                    request.getId(),
                    request.getHoten(),
                    request.getVitri(),
                    ch,
                    request.getBirthday(),
                    request.getDia_chi());

            if (kt == 0) {
                failedStaff.add("Tạo không thành công cho nhân viên: " + request.getHoten());
            }
        }

        if (!failedStaff.isEmpty()) {
            return new ResponseEntity<>(failedStaff, HttpStatus.PARTIAL_CONTENT);
        }

        return ResponseEntity.ok("Tạo tất cả nhân viên thành công");
    }

    @PostMapping("/tao_nhieu_cua_hang")
    public ResponseEntity<?> taoNhieuCuaHang(@RequestBody List<tao_CH_request> requests) {
        List<String> thatBai = new ArrayList<>();
        int soLuongThanhCong = 0;

        for (tao_CH_request request : requests) {
            if (Cua_hang_Service.kiemTraTonTai(request.getStore_id())) {
                thatBai.add("Cửa hàng đã tồn tại: " + request.getStore_id());
                continue;
            }
            boolean ok = true;
            Cua_hang_Service.them_cua_hang(
                    request.getStore_id(),
                    request.getTen_cua_hang(),
                    request.getDia_chi(),
                    request.getContact());

            if (ok) {
                soLuongThanhCong++;
            } else {
                thatBai.add("Không thể thêm: " + request.getStore_id());
            }
        }

        if (thatBai.isEmpty()) {
            return new ResponseEntity<>("Thêm thành công " + soLuongThanhCong + " cửa hàng", HttpStatus.OK);
        }

        return new ResponseEntity<>(
                "Một số cửa hàng không được thêm: " + thatBai + " | Thành công: " + soLuongThanhCong,
                HttpStatus.PARTIAL_CONTENT);
    }

    @PostMapping("/them_nhieu_sp")
    public ResponseEntity<?> themNhieuSanPham(@RequestBody List<san_pham_moi_request> requests) {
    List<String> errors = new ArrayList<>();

    for (san_pham_moi_request request : requests) {
        if (San_pham_service.kiemTraTonTai(request.getMa_sp())) {
            errors.add("Sản phẩm với mã " + request.getMa_sp() + " đã tồn tại.");
            continue;
        }

        if (!Loai_sp_service.kiemTraTonTai(request.getMa_loai())) {
            errors.add("Loại sản phẩm với mã " + request.getMa_loai() + " không tồn tại.");
            continue;
        }

        San_pham_service.them_san_pham(
            request.getMa_sp(),
            request.getTen_sp(),
            request.getDon_gia(),
            request.getMota(),
            Loai_sp_service.timTheoId(request.getMa_loai())
        );
    }

    if (!errors.isEmpty()) {
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>("Thêm tất cả sản phẩm thành công", HttpStatus.OK);
}
}

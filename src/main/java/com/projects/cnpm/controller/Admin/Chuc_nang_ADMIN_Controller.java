package com.projects.cnpm.controller.Admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.text.html.HTML;

import com.projects.cnpm.controller.DTO.ALL_role_DTO;
import com.projects.cnpm.controller.DTO.CH_DTO;
import com.projects.cnpm.controller.DTO.DH_DTO;
import com.projects.cnpm.controller.DTO.NV_DTO;
import com.projects.cnpm.controller.DTO.Role_DTO;
import com.projects.cnpm.controller.DTO.all_product_DTO;
import com.projects.cnpm.controller.DTO.chi_tiet;
import com.projects.cnpm.controller.DTO.lay_CTDH;
import com.projects.cnpm.controller.requestbody.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.cnpm.DAO.Entity.chi_tiet_DH_entity;
import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.don_hang_entity;
import com.projects.cnpm.DAO.Entity.loai_sp_entity;
import com.projects.cnpm.DAO.Entity.nguyen_lieu_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.DAO.Entity.role_entity;
import com.projects.cnpm.DAO.Entity.san_pham_entity;
import com.projects.cnpm.DAO.Entity.Embeddable.kho_id;
import com.projects.cnpm.Service.chi_tiet_DH_service;
import com.projects.cnpm.Service.cua_hang_Service;
import com.projects.cnpm.Service.don_hang_service;
import com.projects.cnpm.Service.kho_service;
import com.projects.cnpm.Service.loai_sp_service;
import com.projects.cnpm.Service.nguyen_lieu_service;
import com.projects.cnpm.Service.nhanvien_service;
import com.projects.cnpm.Service.role_service;
import com.projects.cnpm.Service.san_pham_service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/admin")
public class Chuc_nang_ADMIN_Controller {
    @Autowired
    private san_pham_service San_pham_service;
    @Autowired
    private cua_hang_Service Cua_hang_Service;
    @Autowired
    private loai_sp_service Loai_sp_service;
    @Autowired
    private role_service Role_service;
    @Autowired
    private nguyen_lieu_service Nguyen_lieu_service;
    @Autowired
    private nhanvien_service Nhan_vien_service;
    @Autowired
    private kho_service Kho_service;
    @Autowired
    private don_hang_service Don_hang_service;
    @Autowired
    private chi_tiet_DH_service Chi_tiet_DH_service;

    @GetMapping("/all_loaiSP")
    public ResponseEntity<?> getMethodName() {
        List<loai_sp_entity> all_loai = Loai_sp_service.FindAll();
        if (all_loai.isEmpty()) {
            return new ResponseEntity<>("Load sản phẩm thất bại", HttpStatus.NOT_FOUND);
        }
        List<ALL_role_DTO> ls_loai = all_loai.stream()
                .map(loai -> new ALL_role_DTO(loai.getMa_loai(), loai.getTen_loai()))
                .toList();
        return ResponseEntity.ok(ls_loai);
    }

    @GetMapping("/all_product")
    public ResponseEntity<?> all_product() {
        List<san_pham_entity> all_product = San_pham_service.FindAll();
        if (all_product.isEmpty()) {
            return new ResponseEntity<>("Không có sản phẩm ", HttpStatus.NOT_FOUND);
        }

        List<all_product_DTO> ls_product = all_product.stream()
                .map(product -> new all_product_DTO(product.getMa_sp(),
                        product.getTen_sp(),
                        product.getDon_gia(),
                        product.getMo_ta_sp(),
                        product.getLoai().getMa_loai(),
                        product.getLoai().getTen_loai()))
                .toList();
        return ResponseEntity.ok(ls_product);
    }

    @GetMapping("/all_role")
    public ResponseEntity<?> all_role() {
        List<role_entity> all_role = Role_service.FindAll();
        if (all_role.isEmpty()) {
            return new ResponseEntity<>("Không có role nào tồn tịa", HttpStatus.NOT_FOUND);
        }
        List<ALL_role_DTO> ls_role = all_role.stream()
                .map(role -> new ALL_role_DTO(role.getRole_id(), role.getTen_role())).toList();
        return ResponseEntity.ok(ls_role);
    }

    @DeleteMapping("/xoa_nv")
    public ResponseEntity<?> xoa_nv(@RequestParam String id) {
        System.out.println("mã nhân viên: " + id);
        if (Nhan_vien_service.xoa_nv(id) == 0) {
            return new ResponseEntity<>("Xoá thất bại", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Xoá thành công");
    }

    @GetMapping("/all_CH")
    public ResponseEntity<?> lay_all_CH() {
        List<cuahang_entity> all_cua_hang = Cua_hang_Service.Lay_all_cua_hang();
        if (all_cua_hang.isEmpty()) {
            return new ResponseEntity<>("Không có cửa hàng nào", HttpStatus.NOT_FOUND);
        }
        List<CH_DTO> all_cua_hang_dto = all_cua_hang.stream()
                .map(ch -> new CH_DTO(ch.getStore_id(), ch.getTen_cua_hang(), ch.getDia_chi()))
                .toList();
        return ResponseEntity.ok(all_cua_hang_dto);
    }

    @GetMapping("/all_NV")
    public ResponseEntity<?> lay_all_NV() {
        List<nhanvien_entity> all_NV = Nhan_vien_service.FindAll();
        if (all_NV.isEmpty()) {
            return new ResponseEntity<>("Không có nhân viên nào", HttpStatus.NOT_FOUND);
        }

        List<NV_DTO> all_NV_dto = all_NV.stream().map(nv -> {
            String id = nv.getMa_nv();
            String name = nv.getTen();

            // lấy danh sách tên role
            List<Role_DTO> roles = nv.getRoles().stream().map(role -> new Role_DTO(role.getTen_role()))
                    .collect(Collectors.toList());

            // lấy tên cửa hàng (có thể kiểm tra null nếu cần)
            String tenCuaHang = nv.getStore() != null ? nv.getStore().getTen_cua_hang() : null;

            return new NV_DTO(id, name, nv.getUsername(), nv.getPasswords(), roles, tenCuaHang);
        }).toList();
        return ResponseEntity.ok(all_NV_dto);
    }

    @PostMapping("/tao_nhan_vien")
    public ResponseEntity<?> tao_tai_khoan(@RequestBody tao_moi_NV_request request) {
        if (Nhan_vien_service.kiemTraTonTai(request.getMa_nv())) {
            return new ResponseEntity<>("tạo không thành công mã đã được đăng ký", HttpStatus.BAD_REQUEST);
        }
        for (String lien_he : request.getLien_he()) {
            if (base.chuoi_toan_so(lien_he)) {
                if (Nhan_vien_service.findbySDT(lien_he).isPresent()) {
                    return new ResponseEntity<>("tạo không thành công số điện thoại đã được đăng ký",
                            HttpStatus.BAD_REQUEST);
                }
            }
        }
        System.out.println(request.getIdcua_hang());
        if (!Cua_hang_Service.kiemTraTonTai(request.getIdcua_hang())) {
            return new ResponseEntity<>("Tạo thất bại", HttpStatus.UNAUTHORIZED);
        }
        if (!Nhan_vien_service.findbyUsername(request.getUsername()).isPresent()) {
            Nhan_vien_service.them_nhan_vien(request.getMa_nv(), request.getTen(), request.getUsername(),
                    request.getPasswords(), request.getDia_chi(), request.getLuong(), request.getBirthday(),
                    request.getLien_he(),
                    Cua_hang_Service.timTheoId(request.getIdcua_hang()), request.getRoles());
            return new ResponseEntity<>("tạo thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User name đã tồn tại", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/them_Role_NV")
    public ResponseEntity<?> them_role_NV(@RequestBody them_role_NV request) {
        // Kiểm tra NV có tồn tại hay không, nếu không throw HttpStatus Bad Request
        if (!Nhan_vien_service.kiemTraTonTai(request.getMa_nv())) {
            return new ResponseEntity<>("Nhân viên không tồn tại", HttpStatus.BAD_REQUEST);
        }
        nhanvien_entity nv = Nhan_vien_service.timTheoId(request.getMa_nv());
        // Tương tự như NV kiểm tra Role có trong cửa hàng không
        if (Role_service.kiemTraTonTai(request.getRole_id())) {
            role_entity new_role = Role_service.timTheoId(request.getRole_id());

            int KT = Nhan_vien_service.them_Role(nv, new_role); // Phương thức thêm role cho nhân viên trả về 1 nếu
                                                                // thành công và 0 nếu thất bại
            if (KT == 1) {
                return new ResponseEntity<>("Đã thêm thành công", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Thêm thất bại", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Role bạn cần thêm không tồn tại", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/Tao_role") // Phương thức dùng để tạo 1 role mới và lưu vào cơ sở dữ liệu
    public ResponseEntity<?> tao_role(@RequestBody Tao_role_moi request) {
        if (Role_service.kiemTraTonTai(request.getRole_Id())) {
            return new ResponseEntity<>("Role đã tồn tại", HttpStatus.BAD_REQUEST);
        }
        Role_service.them_role(request.getRole_Id(), request.getTen_role());
        return new ResponseEntity<>("Đã thêm thành công", HttpStatus.OK);
    }

    @DeleteMapping("/Xoa_Role_khoi_NV")
    public ResponseEntity<?> xoa_role_khoi_nhan_vien(@RequestBody them_role_NV request) {
        if (!Nhan_vien_service.kiemTraTonTai(request.getMa_nv())) { // kiểm tra nhân viên có tồn tại hay không
            return new ResponseEntity<>("Nhân viên không tồn tại", HttpStatus.BAD_REQUEST);
        }
        nhanvien_entity nv = Nhan_vien_service.timTheoId(request.getMa_nv());

        if (!Role_service.kiemTraTonTai(request.getRole_id())) { // kiểm tra sự tồn tại của role
            return new ResponseEntity<>("Role không tồn tại", HttpStatus.BAD_REQUEST);
        }
        role_entity del_role = Role_service.timTheoId(request.getRole_id());
        int KT = Nhan_vien_service.xoa_role(nv, del_role); // Phương thức này trả về 0 nếu xoá thành công, 1 nếu trong
                                                           // các quyền của nhân viên không có quyền cần xoá
        if (KT == 0) {
            return new ResponseEntity<>("Xoá thành công", HttpStatus.OK);
        }
        return new ResponseEntity<>("Xoá thất bại, nhân viên không có quyền", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/doanh_thu_theo_thang")
    public ResponseEntity<?> doanh_thu_thang(@RequestBody Doanh_thu_theo_thang request) {
        if (Cua_hang_Service.kiemTraTonTai(request.getStore_id())) {

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
        return new ResponseEntity<>("Không tìm thấy cửa hàng", HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping("/chinh_sua_thong_tin_nv")
    public ResponseEntity<?> chinh_sua_thong_tin(@RequestBody nhan_vien_request request) {
        int kt = Nhan_vien_service.cap_nhat_thong_tin(request);
        if (kt == 1) {
            return new ResponseEntity<>("đã cập nhật thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("không tìm thấy nhân viên", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/tao_cua_hang_moi")
    public ResponseEntity<?> tao_cua_hang_moi(@RequestBody tao_CH_request request) {
        if (Cua_hang_Service.kiemTraTonTai(request.getStore_id())) {
            return new ResponseEntity<>("Cửa hàng đã tồn tại", HttpStatus.BAD_REQUEST);
        } else {
            Cua_hang_Service.them_cua_hang(request.getStore_id(),
                    request.getTen_cua_hang(), request.getDia_chi(), request.getContact());
            return new ResponseEntity<>("Đã thêm thành công", HttpStatus.OK);
        }
    }

    @PostMapping("/them_loai_san_pham")
    public ResponseEntity<?> them_loai_sp(@RequestBody loai_sp_request request) {
        if (Loai_sp_service.kiemTraTonTai(request.getMa_loai())) {
            return new ResponseEntity<>("Loại SP đã tồn tại", HttpStatus.BAD_REQUEST);
        }
        Loai_sp_service.them_loai_sp(request.getMa_loai(), request.getTen_loai());
        return ResponseEntity.ok("thêm thành công");
    }

    @PostMapping("/tao_nguyen_lieu_moi")
    public ResponseEntity<?> them_nguyen_lieu_moi(@RequestBody them_nguyen_lieu_request request) {
        if (Nguyen_lieu_service.kiemTraTonTai(request.getMa_nguyen_lieu())) {
            return new ResponseEntity<>("Chỉ được cập nhật số lượng cho nguyên liệu này", HttpStatus.BAD_REQUEST);
        }
        Nguyen_lieu_service.tao_nguyen_lieu_moi(request.getMa_nguyen_lieu(), request.getTen_nguyen_lieu());
        nguyen_lieu_entity nl = Nguyen_lieu_service.timTheoId(request.getMa_nguyen_lieu());
        List<cuahang_entity> cuahangs = Cua_hang_Service.FindAll();
        for (cuahang_entity ch : cuahangs) {
            kho_id id = new kho_id();
            id.setCua_hang(ch);
            id.setNguyen_lieu(nl);
            Kho_service.tao_kho(id, 0);
        }
        return new ResponseEntity<>("Đã thêm thành công", HttpStatus.OK);
    }

    @PostMapping("/them_san_pham")
    public ResponseEntity<?> postMethodName(@RequestBody san_pham_moi_request request) {
        if (San_pham_service.kiemTraTonTai(request.getMa_sp())) {
            return new ResponseEntity<>("sản phẩm đã tồn tại", HttpStatus.BAD_REQUEST);
        }
        if (!Loai_sp_service.kiemTraTonTai(request.getMa_loai())) {
            return new ResponseEntity<>("sản phẩm đã tồn tại", HttpStatus.BAD_REQUEST);
        }
        San_pham_service.them_san_pham(request.getMa_sp(), request.getTen_sp(),
                request.getDon_gia(), request.getMota(), Loai_sp_service.timTheoId(request.getMa_loai()));
        return new ResponseEntity<>("thêm thành công", HttpStatus.OK);
    }

    @PutMapping("/bo_nhiem_store_manager")
    public ResponseEntity<?> bo_nhiem_store_manager(@RequestBody bo_nhiem_huy_manager request) {
        cuahang_entity ch = Cua_hang_Service.timTheoId(request.getStore_id());
        nhanvien_entity nv = Nhan_vien_service.timTheoId(request.getMa_nv());
        if (nv == null || ch == null) {
            return new ResponseEntity<>("Huỷ thất bại", HttpStatus.NOT_FOUND);
        }
        int kt = Cua_hang_Service.cap_nhat_manager(nv, ch);
        if (kt == 1) {
            them_role_NV moi = new them_role_NV();
            moi.setMa_nv(request.getMa_nv());
            moi.setRole_id("manager");
            this.them_role_NV(moi);
            return new ResponseEntity<>("bổ nhiệm thành công", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bộ nhiệm không thành công", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/huy_store_manager")
    public ResponseEntity<?> huy_store_manager(@RequestBody bo_nhiem_huy_manager request) {
        cuahang_entity ch = Cua_hang_Service.timTheoId(request.getStore_id());
        nhanvien_entity nv = Nhan_vien_service.timTheoId(request.getMa_nv());
        if (nv == null || ch == null) {
            return new ResponseEntity<>("Huỷ thất bại", HttpStatus.NOT_FOUND);
        }
        int kt = Cua_hang_Service.cap_nhat_manager(null, ch);
        if (kt == 1) {
            them_role_NV moi = new them_role_NV();
            moi.setMa_nv(request.getMa_nv());
            moi.setRole_id("manager");
            this.xoa_role_khoi_nhan_vien(moi);
            return new ResponseEntity<>("Huỷ thành công", HttpStatus.OK);
        }
        return new ResponseEntity<>("Huỷ thất bại", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/doanh_thu_x_den_y")
    public ResponseEntity<?> doanh_thu_x_den_y(@RequestBody Doanh_thu_tu_ngay_x_den_y request) {
        if (request.getNgay_bat_dau() == null || request.getNgay_kt() == null) {
            return new ResponseEntity<>("Lỗi ngày tháng", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity
                .ok(Don_hang_service.doanh_thu_x_den_y(request.getNgay_bat_dau(), request.getNgay_kt()));
    }

    @DeleteMapping("/xoa_cua_hang")
    public ResponseEntity<?> xoa_cua_hang(@RequestParam String Store_id) {
        int kt = Cua_hang_Service.xoa_cua_hang(Store_id);
        if (kt == 0) {
            return new ResponseEntity<>("xoá cái nịt", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok("Xoá cửa hàng thành công");
    }

    @PatchMapping("/chinh_sua_store")
    public ResponseEntity<?> chinh_sua_Store(@RequestBody patch_Store request) {
        int kt = Cua_hang_Service.patch_store(request);
        if (kt == 0) {
            return new ResponseEntity<>("Chỉnh sửa cái nịt", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @PatchMapping("/path_product")
    public ResponseEntity<?> path_product(@RequestBody path_product request) {
        int kt = San_pham_service.patch_product(request);
        if (kt == 0) {
            return new ResponseEntity<>("cập nhật thất bại", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @DeleteMapping("/del_product")
    public ResponseEntity<?> del_product(@RequestParam String id) {
        int kt = San_pham_service.del_product(id);
        if (kt == 0) {
            return new ResponseEntity<>("cập nhật thất bại", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Xoá thành công");
    }

    @PostMapping("/Lich_su_cac_don")
    public ResponseEntity<?> lich_su_cac_don(@RequestBody Lich_su_don request) {
        List<DH_DTO> ds_don;
        
            List<don_hang_entity> ls = Don_hang_service.lich_su_don_hang(request.getNgay_bd(), request.getNgay_kt(),
                    request.getStore_id());
            ds_don = ls.stream().map(
                    don -> new DH_DTO(don.getMa_don(),"nhân viên mặc định", don.getNgay_nhan(),
                            "cửa hàng mặc định"))
                    .toList();
        
        if (ds_don.isEmpty()) {
            return new ResponseEntity<>("Không tìm thấy lịch sử đơn", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ds_don);
    }

    @PostMapping("/lay_ctdh")
    public ResponseEntity<?> postMethodName(@RequestBody DH_DTO request) {
        
        if(request.getId() == null){
            System.out.println("mã đơn bị null");
            return new ResponseEntity<>("Không có đơn hàng bạn cần",HttpStatus.NOT_FOUND);
        }
        System.out.println(request.getId());
        don_hang_entity dh = Don_hang_service.timTheoId(request.getId());
        List<chi_tiet_DH_entity> ds_ct = Chi_tiet_DH_service.all_chi_tiet_theo_don(dh);
        if (ds_ct.isEmpty()) {
            
            return new ResponseEntity<>("đơn bạn cần là đơn khống",HttpStatus.NOT_FOUND);
        }
        
        lay_CTDH tra_ve = new lay_CTDH();
        tra_ve.setTong_tien(dh.getThanhTien());
        List<chi_tiet> ds_Chi_tiet = new ArrayList<>();
        for (chi_tiet_DH_entity ct : ds_ct) {
            chi_tiet moi = new chi_tiet(ct.getId().getSan_pham().getMa_sp(),
                                        ct.getId().getSan_pham().getTen_sp(),
                                        ct.getId().getSan_pham().getDon_gia(),
                                        ct.getSo_luong());
            ds_Chi_tiet.add(moi);
        }
        tra_ve.setChi_tiet(ds_Chi_tiet);
        return ResponseEntity.ok(tra_ve);
    }
    

}

package com.projects.cnpm.controller.Admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.cnpm.DAO.Entity.chi_tiet_DH_entity;
import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.don_hang_entity;
import com.projects.cnpm.DAO.Entity.loai_sp_entity;
import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.DAO.Entity.san_pham_entity;
import com.projects.cnpm.DAO.Entity.Embeddable.CTDH_ID;
import com.projects.cnpm.Service.chi_tiet_DH_service;
import com.projects.cnpm.Service.cua_hang_Service;
import com.projects.cnpm.Service.don_hang_service;
import com.projects.cnpm.Service.loai_sp_service;
import com.projects.cnpm.Service.nhanvien_service;
import com.projects.cnpm.Service.san_pham_service;
import com.projects.cnpm.controller.requestbody.Item;
import com.projects.cnpm.controller.requestbody.cap_nhat_don_request;
import com.projects.cnpm.controller.requestbody.don_hang_request;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/sale")
public class chuc_nang_sale {

    @Autowired
    private loai_sp_service Loai_sp_service;
    @Autowired
    private nhanvien_service Nhan_vien_service;
    @Autowired
    private cua_hang_Service Cua_hang_Service;
    @Autowired
    private don_hang_service Don_hang_service;
    @Autowired
    private san_pham_service San_pham_service;

    @Autowired
    private chi_tiet_DH_service Chi_tiet_DH_service;

    public String dinhDangHoaDonText(don_hang_entity dh, int pager) {
        StringBuilder html = new StringBuilder();
        cuahang_entity ch = Don_hang_service.lay_ch_tu_don(dh.getMa_don());
        html.append("<!DOCTYPE html>");
        html.append("<html><head><title>Hóa đơn</title><style>");
        html.append("body { font-family: Arial, sans-serif; font-size: 12px; }");
        html.append(".container { width: 300px; margin: 0 auto; border: 1px solid #ccc; padding: 10px; }");
        html.append(".header { text-align: center; margin-bottom: 10px; }");
        html.append(".info { margin-bottom: 5px; }");
        html.append(".items { width: 100%; border-collapse: collapse; margin-top: 10px; }");
        html.append(".items th, .items td { border-bottom: 1px solid #eee; padding: 5px; text-align: left; }");
        html.append(".total { text-align: right; margin-top: 10px; font-weight: bold; }");
        html.append("</style></head><body><div class='container'>");
        html.append("<div class='header'><h2>HÓA ĐƠN BÁN HÀNG</h2></div>");
        html.append("<div class='info'>Cửa hàng: ").append(ch.getTen_cua_hang()).append("</div>");
        html.append("<div class='info'>Địa chỉ: ").append(ch.getDia_chi()).append("</div>");
        html.append("<div class='info'>Mã đơn hàng: ").append(dh.getMa_don()).append("</div>");
        html.append("<div class='info'>Thời gian: ").append(dh.getNgay_nhan()).append("</div>");
        html.append("<div class='info'>Pager: ").append(pager).append("</div>");
        nhanvien_entity nv = Don_hang_service.lay_nv_tu_don(dh.getMa_don());
        System.out.println(nv);
        if (nv != null) {
            html.append("<div class='info'>Nhân viên: ").append(nv.getTen()).append("</div>");
        }
        html.append("<table class='items'><thead><tr><th>Sản phẩm</th><th>SL</th><th>ĐG</th><th>TT</th></tr></thead><tbody>");
        Long tong = 0L;
        List<chi_tiet_DH_entity> dsitem = Chi_tiet_DH_service.chi_tiet_bangma_don(dh.getMa_don());
        for (chi_tiet_DH_entity item : dsitem) {
            san_pham_entity sp = item.getId().getSan_pham();
            long thanhTien = item.getSo_luong() * sp.getDon_gia();
            tong += thanhTien;
            html.append("<tr><td>").append(sp.getTen_sp()).append("</td><td>").append(item.getSo_luong()).append("</td><td>").append(sp.getDon_gia()).append("</td><td>").append(thanhTien).append("</td></tr>");
        }
        html.append("</tbody></table>");
        html.append("<div class='total'>TỔNG TIỀN: ").append(tong).append("</div>");
        html.append("<div style='text-align: center; margin-top: 20px;'>Cảm ơn quý khách và hẹn gặp lại!</div>");
        html.append("</div></body></html>");
        return html.toString();
    }

    @PostMapping("/mua_hang")
    public ResponseEntity<?> mua_hang(@RequestBody don_hang_request request,@RequestParam int pager) {
        if (Don_hang_service.kiemTraTonTai(request.getID())) {
            return new ResponseEntity<>("Không thành công",HttpStatus.NOT_FOUND);
        }
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp time  = new Timestamp(currentTimeMillis);
        Don_hang_service.them_don_hang(request.getID(), time,"Tiếp nhận",Cua_hang_Service.timTheoId(request.getStore_id()),Nhan_vien_service.timTheoId( request.getID_nv()));
        
        don_hang_entity dh = Don_hang_service.timTheoId(request.getID());
        for(Item item : request.getItems()){
            CTDH_ID id = new CTDH_ID();
            id.setDon_hang(dh);
            id.setSan_pham(San_pham_service.timTheoId(item.getProduct_id()));
            Chi_tiet_DH_service.them_chi_tiet_DH(id, item.getQuantity());
        }
        String hoaDonText = this.dinhDangHoaDonText(dh,pager);
        return new ResponseEntity<>(hoaDonText,HttpStatus.OK);
    }
    
@PostMapping("/lay_toan_bo_sp")
public ResponseEntity<?> lay_toan_bo_sp() {
    List<san_pham_entity> sps = San_pham_service.FindAll();
    List<loai_sp_entity> lsp = Loai_sp_service.FindAll();

    if (sps.isEmpty()) {
        return new ResponseEntity<>("Không tìm thấy sản phẩm", HttpStatus.NOT_FOUND);
    }

    List<Map<String, String>> categories = lsp.stream()
            .map(loai -> {
                Map<String, String> category = new HashMap<>();
                category.put("id", loai.getMa_loai());
                category.put("name", loai.getTen_loai());
                return category;
            })
            .collect(Collectors.toList());

    Map<String, List<Map<String, Object>>> productsByCategory = sps.stream()
            .collect(Collectors.groupingBy(
                    sp -> sp.getLoai().getMa_loai(),
                    Collectors.mapping(sp -> {
                        Map<String, Object> product = new HashMap<>();
                        product.put("ma_sp", sp.getMa_sp());
                        product.put("name", sp.getTen_sp());
                        product.put("price", sp.getDon_gia());
                        product.put("ma_loai", sp.getLoai().getMa_loai());
                        return product;
                    }, Collectors.toList())
            ));

    Map<String, Object> response = new HashMap<>();
    response.put("categories", categories);
    response.put("products", new ArrayList<>(productsByCategory.values())); // Chuyển values thành List

    return ResponseEntity.ok(response);
}

    @PutMapping("/hoang_thanh")
    public ResponseEntity<?> hoang_thanh_don(@RequestBody cap_nhat_don_request request ) {
        int kt = Don_hang_service.cap_nhat_trang_thai_don(request.getID(), "hoàng thành");
        if (kt == 0) {
            return new ResponseEntity<>("Cập nhật thất bại",HttpStatus.UNAUTHORIZED);
        }
        else{
            return new ResponseEntity<>("Đơn hàng đã hoàng thành",HttpStatus.OK);
        }
    }
    
    @PutMapping("/that_bai")
    public ResponseEntity<?> don_hang_fail(@RequestBody cap_nhat_don_request request) {
        int kt = Don_hang_service.cap_nhat_trang_thai_don(request.getID(), "không hoàng thành");
        if (kt == 0) {
            return new ResponseEntity<>("Cập nhật thất bại",HttpStatus.UNAUTHORIZED);
        }
        else{
            return new ResponseEntity<>("Đơn hàng đã hoàng thành",HttpStatus.OK);
        }
    }
    
}

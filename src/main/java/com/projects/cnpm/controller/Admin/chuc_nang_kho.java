package com.projects.cnpm.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.cnpm.DAO.Entity.Embeddable.kho_id;
import com.projects.cnpm.Service.cua_hang_Service;
import com.projects.cnpm.Service.kho_service;
import com.projects.cnpm.Service.nguyen_lieu_service;
import com.projects.cnpm.controller.requestbody.cap_nhat_kho_request;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/kho")
public class chuc_nang_kho {

    @Autowired
    private kho_service Kho_service;

    @Autowired
    private nguyen_lieu_service Nguyen_lieu_service;

    @Autowired
    private cua_hang_Service Cua_hang_Service;

    @PutMapping("cap_nhat_ton")
    public ResponseEntity<?> cap_nhat_ton_kho(@RequestBody cap_nhat_kho_request request) {
        if (Nguyen_lieu_service.kiemTraTonTai(request.getMa_nl())) {
            kho_id id = new kho_id();
            id.setCua_hang(Cua_hang_Service.timTheoId(request.getStore_id()));
            id.setNguyen_lieu(Nguyen_lieu_service.timTheoId(request.getMa_nl()));
            int kt = Kho_service.cap_nhat_so_luong(id, request.getSo_luong_ton());
            if (kt == 1) {
                return new ResponseEntity<>("cập nhật thành công",HttpStatus.OK);
            }
            return new ResponseEntity<>("Cập nhật thất bại",HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Cập nhật thất bại",HttpStatus.UNAUTHORIZED);
    }
    
}

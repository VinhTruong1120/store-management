package com.projects.cnpm.controller.Admin;


import com.projects.cnpm.DAO.Entity.nhanvien_entity;
import com.projects.cnpm.Service.nhanvien_service;
import com.projects.cnpm.controller.requestbody.base;
import com.projects.cnpm.controller.requestbody.dang_nhap_request;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/nhan_vien")
public class Chuc_nang_chung_controller {

    @Autowired
    private nhanvien_service Nhan_vien_service;
    
    
   @PostMapping("/dang_nhap")
    public ResponseEntity<?> dang_nhap(@RequestBody dang_nhap_request request) {
        //Sử lý user name theo số điện thoại
        if (request.getUsername().length() == 10 && base.chuoi_toan_so(request.getUsername())) {
            Optional<nhanvien_entity> nvOptional = Nhan_vien_service.findbySDT(request.getUsername());
            if (!nvOptional.isPresent()) {
                return new ResponseEntity<>("sai thông tin",HttpStatus.UNAUTHORIZED);
            }
            nhanvien_entity nv = nvOptional.get();
            if (!nv.getPasswords().equals(request.getPasswords())) { 
                return new ResponseEntity<>("sai thông tin",HttpStatus.UNAUTHORIZED);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("id", nv.getMa_nv());
                response.put("username", nv.getUsername());
                response.put("hoTen", nv.getTen());
                response.put("Lien_he", nv.getLien_he());
                response.put("Cua_hang", nv.getStore().getStore_id());
                return ResponseEntity.ok(response); 
            }
        }

        //sử lý username là tên đăng nhập bình thường 
        Optional<nhanvien_entity> nvOptional = Nhan_vien_service.findbyUsername(request.getUsername());
        if (!nvOptional.isPresent() || nvOptional.isEmpty()) {
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("sai thông tin");
        }
        nhanvien_entity nv = nvOptional.get();
        if (nv.getPasswords().equals(request.getPasswords())) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", nv.getMa_nv());
            response.put("username", nv.getUsername());
            response.put("hoTen", nv.getTen());
            response.put("Lien_he", nv.getLien_he());
            response.put("Cua_hang", nv.getStore().getStore_id());
            return ResponseEntity.ok(response); 
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin");
    }
    
    
    
    
}

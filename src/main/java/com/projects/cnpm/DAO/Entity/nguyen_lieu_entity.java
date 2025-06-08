package com.projects.cnpm.DAO.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "nguyen_lieu")
public class nguyen_lieu_entity {

    @Id
    @Column(name = "ma_nguyen_lieu", length = 10,columnDefinition = "char(10)")
    private String ma_nguyen_lieu;

    @Column(name = "ten_nguyen_lieu")
    private String ten_nguyen_lieu;

  

    @OneToMany(mappedBy = "id.nguyen_lieu")
    private List<kho_entity> kho;
    
    public List<kho_entity> getKho() {
        return kho;
    }

    public void setKho(List<kho_entity> kho) {
        this.kho = kho;
    }


    public String getMa_nguyen_lieu() {
        return ma_nguyen_lieu;
    }

    public void setMa_nguyen_lieu(String ma_nguyen_lieu) {
        this.ma_nguyen_lieu = ma_nguyen_lieu;
    }

    public String getTen_nguyen_lieu() {
        return ten_nguyen_lieu;
    }

    public void setTen_nguyen_lieu(String ten_nguyen_lieu) {
        this.ten_nguyen_lieu = ten_nguyen_lieu;
    }
}

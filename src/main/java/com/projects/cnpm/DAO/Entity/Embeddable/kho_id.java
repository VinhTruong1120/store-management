package com.projects.cnpm.DAO.Entity.Embeddable;

import java.io.Serializable;

import com.projects.cnpm.DAO.Entity.cuahang_entity;
import com.projects.cnpm.DAO.Entity.nguyen_lieu_entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class kho_id implements Serializable {
    @ManyToOne
    @JoinColumn(name =  "store_id",foreignKey = @ForeignKey(name= "FK_Store_Kho"))
    private cuahang_entity cua_hang;

    @ManyToOne
    @JoinColumn(name = "ma_nguyen_lieu",foreignKey = @ForeignKey(name = "FK_NL_KHO"))
    private nguyen_lieu_entity nguyen_lieu;

    public cuahang_entity getCua_hang() {
        return cua_hang;
    }

    public void setCua_hang(cuahang_entity cua_hang) {
        this.cua_hang = cua_hang;
    }

    public nguyen_lieu_entity getNguyen_lieu() {
        return nguyen_lieu;
    }

    public void setNguyen_lieu(nguyen_lieu_entity nguyen_lieu) {
        this.nguyen_lieu = nguyen_lieu;
    }
}

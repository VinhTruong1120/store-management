package com.projects.cnpm.DAO.Entity;

import com.projects.cnpm.DAO.Entity.Embeddable.CTDH_ID;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "chi_tiet_don_hang")
public class chi_tiet_DH_entity {
    
    @EmbeddedId
    private CTDH_ID id;

    @Column(name = "so_luong",nullable = false)
    @Min(0)
    private int so_luong;



    public chi_tiet_DH_entity (){}


    public CTDH_ID getId() {
        return id;
    }

    public void setId(CTDH_ID id) {
        this.id = id;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

}

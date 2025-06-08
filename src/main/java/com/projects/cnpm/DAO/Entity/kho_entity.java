package com.projects.cnpm.DAO.Entity;

import com.projects.cnpm.DAO.Entity.Embeddable.kho_id;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "kho")
public class kho_entity {
    @EmbeddedId
    kho_id id;

    @Column(name = "so_luong_ton")
    private int so_luong_ton;

    public kho_id getId() {
        return id;
    }

    public void setId(kho_id id) {
        this.id = id;
    }

    public int getSo_luong_ton() {
        return so_luong_ton;
    }

    public void setSo_luong_ton(int so_luong_ton) {
        this.so_luong_ton = so_luong_ton;
    }
}

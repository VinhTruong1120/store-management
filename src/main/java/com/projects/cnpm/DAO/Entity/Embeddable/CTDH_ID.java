package com.projects.cnpm.DAO.Entity.Embeddable;

import java.io.Serializable;

import com.projects.cnpm.DAO.Entity.don_hang_entity;
import com.projects.cnpm.DAO.Entity.san_pham_entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CTDH_ID  implements Serializable{

    @ManyToOne
    @JoinColumn(name = "ma_don",foreignKey = @ForeignKey(name = "FK_dh_CT"))
    private don_hang_entity don_hang;

    @ManyToOne
    @JoinColumn(name = "ma_sp",foreignKey = @ForeignKey(name = "FK_sp_CTDH"))
    private san_pham_entity san_pham;

    public CTDH_ID(don_hang_entity don_hang, san_pham_entity san_pham) {
        this.don_hang = don_hang;
        this.san_pham = san_pham;
    }
    public CTDH_ID() {}
    public CTDH_ID(CTDH_ID ctdh_id) {
        this.don_hang = ctdh_id.don_hang;
        this.san_pham = ctdh_id.san_pham;
    }

    public don_hang_entity getDon_hang() {
        return don_hang;
    }

    public void setDon_hang(don_hang_entity don_hang) {
        this.don_hang = don_hang;
    }

    public san_pham_entity getSan_pham() {
        return san_pham;
    }

    public void setSan_pham(san_pham_entity san_pham) {
        this.san_pham = san_pham;
    }
}

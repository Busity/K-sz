package com.example.Buska.Entity.EntityDTO;

import com.example.Buska.Entity.Projekt;

public class ProjektDTO {
    private Integer id;
    private String megrendelo;
    private String indulasiDatum;
    private String leiras;
    private boolean deleted;
    public ProjektDTO() {
    }
    public ProjektDTO(Projekt projekt) {
        this.id = projekt.getId();
        this.megrendelo = projekt.getMegrendelo();
        this.indulasiDatum = projekt.getIndulasiDatum();
        this.leiras = projekt.getLeiras();
        this.deleted = projekt.isDeleted();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMegrendelo() {
        return megrendelo;
    }
    public void setMegrendelo(String megrendelo) {
        this.megrendelo = megrendelo;
    }
    public String getIndulasiDatum() {
        return indulasiDatum;
    }
    public void setIndulasiDatum(String indulasiDatum) {
        this.indulasiDatum = indulasiDatum;
    }
    public String getLeiras() {
        return leiras;
    }
    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
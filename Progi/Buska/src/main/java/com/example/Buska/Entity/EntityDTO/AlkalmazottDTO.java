package com.example.Buska.Entity.EntityDTO;

import com.example.Buska.Entity.Alkalmazott;

public class AlkalmazottDTO {
    private Integer id;
    private String nev;
    private String lakcim;
    private String szuletesiDatum;
    private String telefonszam;
    private String email;
    private boolean deleted;

    public AlkalmazottDTO() {
    }

    public AlkalmazottDTO(Alkalmazott alkalmazott) {
        this.id = alkalmazott.getId();
        this.nev = alkalmazott.getNev();
        this.lakcim = alkalmazott.getLakcim();
        this.szuletesiDatum = alkalmazott.getSzuletesiDatum();
        this.telefonszam = alkalmazott.getTelefonszam();
        this.email = alkalmazott.getEmail();
        this.deleted = alkalmazott.isDeleted();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getLakcim() {
        return lakcim;
    }

    public void setLakcim(String lakcim) {
        this.lakcim = lakcim;
    }

    public String getSzuletesiDatum() {
        return szuletesiDatum;
    }

    public void setSzuletesiDatum(String szuletesiDatum) {
        this.szuletesiDatum = szuletesiDatum;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
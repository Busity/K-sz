package com.example.Buska.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class ProjektMenedzser extends Alkalmazott {
    public ProjektMenedzser() {
    }
    public ProjektMenedzser(Integer id, String nev, String lakcim, String szuletesiDatum, String telefonszam, String email, boolean deleted) {
        super(id, nev, lakcim, szuletesiDatum, telefonszam, email, deleted);
    }
    public ProjektMenedzser(Integer id, String nev, String lakcim, String szuletesiDatum, String telefonszam, String email, boolean deleted, List<Projekt> projektek, List<Alkalmazott> beosztottak) {
        super(id, nev, lakcim, szuletesiDatum, telefonszam, email, deleted);
        this.projektek = projektek;
        this.beosztottak = beosztottak;
    }

    @OneToMany(fetch = FetchType.EAGER)
    private List<Projekt> projektek;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Alkalmazott> beosztottak;

    public List<Projekt> getProjektek() {
        return projektek;
    }
    public void setProjektek(List<Projekt> projektek) {
        this.projektek = projektek;
    }
    public List<Alkalmazott> getBeosztottak() {
        return beosztottak;
    }
    public void setBeosztottak(List<Alkalmazott> beosztottak) {
        this.beosztottak = beosztottak;
    }

}


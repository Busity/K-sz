package com.example.Buska.Entity;

import com.example.Buska.Enum.Feladatkor;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Programozo extends Alkalmazott {

    @Enumerated(EnumType.STRING)
    @Column(name = "Feladatkor")
    private Feladatkor feladatkor;

    private boolean gyakornok;
    @JoinColumn(name = "Projektek")
    @OneToMany(fetch = FetchType.EAGER)
    private List<Projekt> projektek;
    @JoinColumn(name = "ProjektMenedzser")
    @ManyToOne(fetch = FetchType.EAGER)
    private ProjektMenedzser projektmenedzser;
    @JoinColumn(name = "Beosztottak")
    @OneToMany(fetch = FetchType.EAGER)
    private List<Alkalmazott> beosztottak;


    public Programozo(Integer id, String nev, String lakcim, String szuletesiDatum, String telefonszam, String email, boolean deleted, Feladatkor feladatkor, boolean gyakornok, List<Projekt> projektek, ProjektMenedzser projektmenedzser, List<Alkalmazott> beosztottak) {
        super(id, nev, lakcim, szuletesiDatum, telefonszam, email, deleted);
        this.feladatkor = feladatkor;
        this.gyakornok = gyakornok;
        this.projektek = projektek;
        this.projektmenedzser = projektmenedzser;
        this.beosztottak = beosztottak;
    }
   public Programozo() {
        this.gyakornok = isGyakornok();
        this.projektek = new ArrayList<>();
        this.beosztottak = new ArrayList<>();
    }

    public Programozo(Integer id, String nev, String lakcim, String szuletesiDatum,String telefonszam, String email, boolean deleted, boolean gyakornok, List<Projekt> projektek, ProjektMenedzser projektmenedzser, List<Alkalmazott> beosztottak) {
        super(id, nev, lakcim, szuletesiDatum, telefonszam, email, deleted);
        this.gyakornok = gyakornok;
        this.projektek = projektek;
        this.projektmenedzser = projektmenedzser;
        this.beosztottak = beosztottak;
    }

    public Feladatkor getFeladatkor() {
        return feladatkor;
    }
    public void setFeladatkor(Feladatkor feladatkor) {
        this.feladatkor = feladatkor;
    }
    public boolean isGyakornok() {
        return gyakornok;
    }
    public void setGyakornok(boolean gyakornok) {
        this.gyakornok = gyakornok;
    }
    public List<Projekt> getProjektek() {
        return projektek;
    }
    public void setProjektek(List<Projekt> projektek) {
        this.projektek = projektek;
    }
    public ProjektMenedzser getProjektmenedzser() {
        return projektmenedzser;
    }
    public void setProjektmenedzser(ProjektMenedzser projektmenedzser) {
        this.projektmenedzser = projektmenedzser;
    }
    public List<Alkalmazott> getBeosztottak() {
        return beosztottak;
    }
    public void setBeosztottak(List<Alkalmazott> beosztottak) {
        this.beosztottak = beosztottak;
    }
}


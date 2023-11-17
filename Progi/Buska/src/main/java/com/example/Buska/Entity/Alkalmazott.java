package com.example.Buska.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
 // Legeneráltam a gettereket Settereket,csak gyakorlás végett.
@Table
@Entity
@Data

public class Alkalmazott  {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
     @NotBlank(message = "A név mező nem lehet üres")
     @Size(min = 2, max = 50, message = "A név hossza 2 és 50 karakter között kell legyen")
     @Column(name = "Nev")
     private String nev;
     @NotBlank(message = "A lakcim mező nem lehet üres")
     @Size(max = 50, message = "A lakcim mező maximális hossza 50 karakter lehet")
     @Column(name = "Lakcim")
     private String lakcim;
     @NotNull(message = "A születési dátum nem lehet üres")
     @Past(message = "A születési dátumnak a múltban kell lennie")
     @Column(name = "SzuletesiDatum")
     private String szuletesiDatum;
     @Size(min = 8, max = 20, message = "Az Alkalmazott telefonszáma 4 és 20 karakter között kell legyen")
     @Pattern(regexp = "^[0-9]{0,9}$", message = "A telefonszám csak számokat tartalmazhat 0 és 9 között")
     @Column(name = "Telefonszam")
     private String telefonszam;
    @NotBlank(message = "Az e-mail cím nem lehet üres")
    @Email(message = "Érvénytelen e-mail cím formátum")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @Column(name = "email", unique=true)
    private String email;

    private boolean deleted;

    public Alkalmazott() {
    }

    public Alkalmazott(Integer id, String nev, String lakcim, String szuletesiDatum, String telefonszam, String email, boolean deleted) {
        this.id = id;
        this.nev = nev;
        this.lakcim = lakcim;
        this.szuletesiDatum = szuletesiDatum;
        this.telefonszam = telefonszam;
        this.email = email;
        this.deleted = deleted;
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


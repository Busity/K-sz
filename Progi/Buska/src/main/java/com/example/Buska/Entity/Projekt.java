package com.example.Buska.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

//@Data - Ezzel az egyszerű használattal a Lombok generálja az összes szükséges gettert, settert, toString(), equals() és hashCode() metódusokat az Alkalmazott osztályhoz, anélkül, hogy manuálisan kellene megírni őket. Ezenkívül további Lombok annotációkat is használhat a kód további leegyszerűsítésére és olvashatóságának javítására.
//Getter Setter le lett kérve,mivel kezdésre megszokni,utánna lombok.
@Table(name = "Projekt")
@Entity
@Data
public class Projekt {
    @Column(name = "ID")
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "Megrendelo")
    private String megrendelo;

    @NotNull(message = "Az indulásidő mező nem lehet üres")
    @Future(message = "Az indulásidőnek a jövőben kell lennie")
    //Past-ha már előbb elindult.
    @Column(name = "IndulasiDatum")
    private String indulasiDatum;
    @NotBlank(message = "A leírás mező nem lehet üres")
    @Size(max = 500, message = "A leírás mező maximális hossza 500 karakter lehet")
    @Column(name = "Leiras")
    private String leiras;
    private boolean deleted;

    public Projekt() {
    }

    public Projekt(Integer id, String megrendelo, String indulasiDatum, String leiras, boolean deleted) {
        this.id = id;
        this.megrendelo = megrendelo;
        this.indulasiDatum = indulasiDatum;
        this.leiras = leiras;
        this.deleted = deleted;
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


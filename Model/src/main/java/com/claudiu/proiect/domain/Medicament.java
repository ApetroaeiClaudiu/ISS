package com.claudiu.proiect.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medicamente")
public class Medicament{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nume")
    private String nume;
    @Column(name = "pret")
    private float pret;
    @Column(name = "detalii")
    private String detalii;

    public Medicament(int id, String nume, float pret, String detalii) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.detalii = detalii;
    }

    public Medicament(String nume, float pret, String detalii) {
        this(0, nume, pret, detalii);
    }

    public Medicament() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getDetalii() {
        return detalii;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament that = (Medicament) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                ", detalii='" + detalii + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

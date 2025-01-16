package com.NICOLA.LAB9ex3.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="masini3")
public class Masina {
    @Id
    private String nrinmatriculare;
    private String marca;
    private int anulfabricatiei;
    private String culoare;
    private long nrkm;

    public Masina() {}
    public Masina(String nrinmatriculare, String marca, int anulfabricatiei, String culoare, long nrkm) {
        this.nrinmatriculare = nrinmatriculare;
        this.marca = marca;
        this.anulfabricatiei = anulfabricatiei;
        this.culoare = culoare;
        this.nrkm = nrkm;
    }

    public String getNrInmatriculare() {
        return nrinmatriculare;
    }

    public void setNrinmatriculare(String nrinmatriculare) {
        this.nrinmatriculare = nrinmatriculare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public int getAnulFabricatiei() {
        return anulfabricatiei;
    }

    public void setAnulfabricatiei(int anulfabricatiei) {
        this.anulfabricatiei = anulfabricatiei;
    }

    public long getNrKm() {
        return nrkm;
    }

    public void setNrkm(long nrkm) {
        this.nrkm = nrkm;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "nrinmatriculare='" + nrinmatriculare + '\'' +
                ", marca='" + marca + '\'' +
                ", anulfabricatiei=" + anulfabricatiei +
                ", culoare='" + culoare + '\'' +
                ", nrkm=" + nrkm +
                '}';
    }

}

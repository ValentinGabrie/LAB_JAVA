package com.NICOLA.LAB10.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carti")
public class Carte {
    @PersistenceContext
    @Id
    @Column(name="isbn")
    private String isbn;
    private String titlu;
    private String autor;

    public Carte() {}

    public Carte(String isbn, String titlu, String autor) {
        this.isbn = isbn;
        this.titlu = titlu;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "ISBN=" + isbn +
                ", Titlul='" + titlu + '\'' +
                ", Autorul='" + autor + '\'' +
                '}';
    }
}

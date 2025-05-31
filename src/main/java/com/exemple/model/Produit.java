package com.exemple.model;

import javax.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idP")
    private Long id;  // 🔄 Changed from Integer to Long

    @Column(name = "nomP")
    private String nomProduit;

    @Column(name = "prix")
    private double prix;

    // Constructors
    public Produit() {}

    public Produit(String nomProduit, double prix) {
        this.nomProduit = nomProduit;
        this.prix = prix;
    }

    public Produit(Long id, String nomProduit, double prix) {  // 🔄 Use Long
        this.id = id;
        this.nomProduit = nomProduit;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {  // 🔄 Use Long
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}

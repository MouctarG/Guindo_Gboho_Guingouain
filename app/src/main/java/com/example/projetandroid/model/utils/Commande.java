package com.example.projetandroid.model.utils;

/**
 *
 */
public class Commande {
    private String prenom;
    private String nom;
    private String telephone;
    private String adresse;
    private String date;
    private String montant;
    private String nomArticles;


    public Commande(String prenom, String nom, String telephone, String adresse,
                    String date, String montant, String nomArticles) {
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.date = date;
        this.montant = montant;
        this.nomArticles = nomArticles;
    }

    public String getNomArticles() {
        return nomArticles;
    }

    public void setNomArticles(String nomArticles) {
        this.nomArticles = nomArticles;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
}

package com.example.projetandroid.model;


public class ItemPanierProduct {
    private String name;
    private String articleId;
    private double montant;
    private int quantite;

    private String image_url;
    private String description;


    public ItemPanierProduct(String name, String articleId, double montant, int quantite, String image_url, String description) {
        this.name = name;
        this.articleId = articleId;
        this.montant = montant;
        this.quantite = quantite;
        this.image_url = image_url;
        this.description = description;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getQuantite() {

        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

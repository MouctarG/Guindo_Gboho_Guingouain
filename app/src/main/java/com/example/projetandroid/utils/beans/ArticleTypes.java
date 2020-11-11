package com.example.projetandroid.utils.beans;

import java.util.List;

public class ArticleTypes {
    private Marque brand;
    private String id;
    private List<LinksTypes> links;
    private double maximumPrice;
    private String name;
    private String referralUrl;
    private String shortDescription;
    private Image image;

    public Marque getBrand() {
        return brand;
    }

    public void setBrand(Marque brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LinksTypes> getLinks() {
        return links;
    }

    public void setLinks(List<LinksTypes> links) {
        this.links = links;
    }

    public double getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(double maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferralUrl() {
        return referralUrl;
    }

    public void setReferralUrl(String referralUrl) {
        this.referralUrl = referralUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

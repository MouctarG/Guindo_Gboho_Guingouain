package com.example.projetandroid.model.utils.beans;

import java.util.List;

/**
 * Pour faire le binding le json de type product detail retoune par le serveur
 */
public class ArticleDetails {
    private Marque brand;
    private String catalogName;
    private CatagoryArticle category;
    private double id;
    private Image image;
    private String name;
    private boolean onSale;
    private List<OptionArticle> options;
    private String referralPageUrl;
    private String shortDescription;
    private String description;
    private List<LinksTypes> links;

    public Marque getBrand() {
        return brand;
    }

    public void setBrand(Marque brand) {
        this.brand = brand;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public CatagoryArticle getCategory() {
        return category;
    }

    public void setCategory(CatagoryArticle category) {
        this.category = category;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public List<OptionArticle> getOptions() {
        return options;
    }

    public void setOptions(List<OptionArticle> options) {
        this.options = options;
    }

    public String getReferralPageUrl() {
        return referralPageUrl;
    }

    public void setReferralPageUrl(String referralPageUrl) {
        this.referralPageUrl = referralPageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LinksTypes> getLinks() {
        return links;
    }

    public void setLinks(List<LinksTypes> links) {
        this.links = links;
    }

    public class OptionArticle {
        private String name;
        private double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    public class CatagoryArticle {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

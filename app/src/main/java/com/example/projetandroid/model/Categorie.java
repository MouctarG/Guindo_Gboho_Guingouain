package com.example.projetandroid.model;

import java.util.List;

public class Categorie {
    private String id;
    private String name;
    private int productCount;
    List<LinksTypes> links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public List<LinksTypes> getLinks() {
        return links;
    }

    public void setLinks(List<LinksTypes> links) {
        this.links = links;
    }

    public class LinksTypes {
        private String href;
        private String rel;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }
    }
}


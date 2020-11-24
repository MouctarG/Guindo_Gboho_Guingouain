package com.example.projetandroid.model.utils.beans;

/**
 * Pour faire le binding avec le json Links du serveur (Api)
 */
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
package com.example.projetandroid.model.utils.beans;

import java.util.List;

/**
 * Pour faire le binding avec le json (liste de categorie) du serveur (Api)
 */
public class Categories {

    private List<CategoriesTypes> categories;

    public List<CategoriesTypes> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesTypes> categories) {
        this.categories = categories;
    }
}

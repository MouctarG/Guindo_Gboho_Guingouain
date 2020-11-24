package com.example.projetandroid.model.utils.webservice;

import com.example.projetandroid.model.utils.beans.ArticleTypes;
import com.example.projetandroid.model.utils.beans.Articles;
import com.example.projetandroid.model.utils.beans.Categories;
import com.example.projetandroid.model.utils.beans.CategoriesTypes;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * C'est le webservice de l'application
 */
public class GetDataWebService {
    private static final String Api_url_Categories
            = "https://api2.shop.com/AffiliatePublisherNetwork/v2/categories?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&onlyMaProducts=false";
    private static final String Api_url_Articles
            = "https://api2.shop.com/AffiliatePublisherNetwork/v2/products?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&perPage=15&onlyMaProducts=false";


    /**
     * @return la liste des categories disponible depuis notre Api et le
     * transforme en une liste d'objet de type CategoriesTypes
     * @throws Exception
     */
    public static ArrayList<CategoriesTypes> getAllCategoriesByServer() throws Exception {
        String jsonResponse = OkHttpUtils.sendGetOkHttpRequest(Api_url_Categories);
        Gson gson = new Gson();
        Categories categories = gson.fromJson(jsonResponse, Categories.class);
        return (ArrayList<CategoriesTypes>) categories.getCategories();
    }


    /**
     * @return la liste des articles fournit depuis notre api
     * @throws Exception
     */
    public static ArrayList<ArticleTypes> getAllArticlesByServer() throws Exception {

        String jsonResponse = OkHttpUtils.sendGetOkHttpRequest("https://api2.shop.com/AffiliatePublisherNetwork/v2/products?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&perPage=400&onlyMaProducts=false");
        Gson gson = new Gson();
        Articles articles = gson.fromJson(jsonResponse, Articles.class);
        return (ArrayList<ArticleTypes>) articles.getProducts();
    }

    /**
     * @param categoryId
     * @return la liste des articles fournit depuis notre api selon une categorie de recherche
     * @throws Exception
     */
    public static ArrayList<ArticleTypes> getArticleByCategory(String categoryId) throws Exception {
        String url = "https://api2.shop.com/AffiliatePublisherNetwork/v2/products?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&perPage=400&categoryId=";
        url += categoryId + "&onlyMaProducts=false";
        String jsonResponse = OkHttpUtils.sendGetOkHttpRequest(url);
        Gson gson = new Gson();
        Articles articles = gson.fromJson(jsonResponse, Articles.class);
        return (ArrayList<ArticleTypes>) articles.getProducts();
    }

    /**
     * @param motif
     * @return la liste des articles fournit depuis notre api selon un motif de recherche
     * (nom ou categorie de l'article)
     * @throws Exception
     */
    public static ArrayList<ArticleTypes> getAllArticlesBySearch(String motif) throws Exception {
        String url = "https://api2.shop.com/AffiliatePublisherNetwork/v2/products?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&term=" + motif + "&perPage=15&onlyMaProducts=false";
        String jsonResponse = OkHttpUtils.sendGetOkHttpRequest(url);
        Gson gson = new Gson();
        Articles articles = gson.fromJson(jsonResponse, Articles.class);
        return (ArrayList<ArticleTypes>) articles.getProducts();
    }
}

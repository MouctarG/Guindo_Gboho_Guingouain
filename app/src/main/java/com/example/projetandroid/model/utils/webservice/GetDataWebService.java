package com.example.projetandroid.model.utils.webservice;

import android.util.Log;

import com.example.projetandroid.model.utils.beans.ArticleTypes;
import com.example.projetandroid.model.utils.beans.Articles;
import com.example.projetandroid.model.utils.beans.Categories;
import com.example.projetandroid.model.utils.beans.CategoriesTypes;
import com.google.gson.Gson;

import java.util.ArrayList;


public class GetDataWebService {
    private static final String Api_url_Categories
            = "https://api2.shop.com/AffiliatePublisherNetwork/v2/categories?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&onlyMaProducts=false";
    private static final String Api_url_Articles
            = "https://api2.shop.com/AffiliatePublisherNetwork/v2/products?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&perPage=15&onlyMaProducts=false";


    public static ArrayList<CategoriesTypes> getAllCategoriesByServer() throws Exception {

        String jsonResponse = OkHttpUtils.sendGetOkHttpRequest(Api_url_Categories);
        Log.v("Tag", String.valueOf(jsonResponse.length()));
        Gson gson = new Gson();
        Categories categories = gson.fromJson(jsonResponse, Categories.class);

        Log.v("Tag", String.valueOf(categories.getCategories().size()));

        return (ArrayList<CategoriesTypes>) categories.getCategories();
    }


    public static ArrayList<ArticleTypes> getAllArticlesByServer() throws Exception {

        String jsonResponse = OkHttpUtils.sendGetOkHttpRequest("https://api2.shop.com/AffiliatePublisherNetwork/v2/products?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&perPage=400&onlyMaProducts=false");
        Log.v("Tag", String.valueOf(jsonResponse.length()));
        Gson gson = new Gson();
        Articles articles = gson.fromJson(jsonResponse, Articles.class);

        Log.v("Tag Article", String.valueOf(articles.getProducts().size()));

        return (ArrayList<ArticleTypes>) articles.getProducts();
    }

    public static ArrayList<ArticleTypes> getArticleByCategory(String categoryId) throws Exception {
        String url = "https://api2.shop.com/AffiliatePublisherNetwork/v2/products?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&perPage=400&categoryId=";
        url += categoryId + "&onlyMaProducts=false";
        String jsonResponse = OkHttpUtils.sendGetOkHttpRequest(url);
        Log.v("Tag", String.valueOf(jsonResponse.length()));
        Gson gson = new Gson();
        Articles articles = gson.fromJson(jsonResponse, Articles.class);

        Log.v("Tag Article", String.valueOf(articles.getProducts().size()));

        return (ArrayList<ArticleTypes>) articles.getProducts();

    }

    public static ArrayList<ArticleTypes> getAllArticlesBySearch(String motif) throws Exception {
        String url = "https://api2.shop.com/AffiliatePublisherNetwork/v2/products?" +
                "publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&term=" + motif;
        url += "&perPage=15&onlyMaProducts=false";
        String jsonResponse = OkHttpUtils.sendGetOkHttpRequest(url);
        Log.v("Tag", String.valueOf(jsonResponse.length()));
        Gson gson = new Gson();
        Articles articles = gson.fromJson(jsonResponse, Articles.class);

        Log.v("Tag Article", String.valueOf(articles.getProducts().size()));

        return (ArrayList<ArticleTypes>) articles.getProducts();
    }
}

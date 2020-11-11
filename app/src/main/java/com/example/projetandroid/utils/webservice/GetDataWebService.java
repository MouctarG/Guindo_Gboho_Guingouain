package com.example.projetandroid.utils.webservice;

import android.util.Log;

import com.example.projetandroid.utils.beans.Categories;
import com.example.projetandroid.utils.beans.CategoriesTypes;
import com.google.gson.Gson;

import java.util.ArrayList;


public class GetDataWebService {
    private static final String Api_url_Categories = "https://api2.shop.com/AffiliatePublisherNetwork/v2/categories?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&onlyMaProducts=false";

    private static final String url_Test = "https://etablissements-publics.api.gouv.fr/v3/departements/14/accompagnement_personnes_agees";

    private static final String Api_url_Articles = "https://api2.shop.com/AffiliatePublisherNetwork/v2/products?publisherId=TEST&locale=fr_CA&site=shop&shipCountry=CA&perPage=15&onlyMaProducts=false";


    public static ArrayList<CategoriesTypes> getAllCategoriesByServer() throws Exception {

        String jsonResponse = OkHttpUtils.sendGetOkHttpRequest(Api_url_Categories);
        Log.v("Tag", String.valueOf(jsonResponse.length()));
        Gson gson = new Gson();
        Categories categories = gson.fromJson(jsonResponse, Categories.class);

        Log.v("Tag", String.valueOf(categories.getCategories().size()));

        return (ArrayList<CategoriesTypes>) categories.getCategories();
    }
}

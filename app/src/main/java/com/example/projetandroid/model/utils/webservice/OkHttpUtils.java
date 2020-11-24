package com.example.projetandroid.model.utils.webservice;

import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Permet d'effectuer des requetes Http avec notre Api.
 */
public class OkHttpUtils {

    public static String sendGetOkHttpRequest(String url) throws Exception {

        OkHttpClient client = new OkHttpClient();
        //Création de la requête
        Request request = new Request.Builder().url(url)
                .addHeader("api_Key", "3c1316b53f06427c887340021d6511e3")
                .build();


        //Exécution de la requête;
        Response response = client.newCall(request).execute();

        //Analyse du code retour
        if (response.code() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Réponse du serveur incorrecte : " + response.code());
        } else {
            //Résultat de la requête
            return response.body().string();
        }
    }
}

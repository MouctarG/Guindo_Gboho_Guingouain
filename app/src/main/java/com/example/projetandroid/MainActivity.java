package com.example.projetandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projetandroid.model.Categorie;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {
    private List<Categorie> lesCategories;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private ActionBarDrawerToggle barDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lesCategories = new ArrayList<>();
        getCategories();


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navSide);
        barDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(barDrawerToggle);
        barDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getCategories();
        String s=getString(R.string.url_Part_2_Article_Detail_Api);
    }


    public void getCategories() {
        Gson gson = new Gson();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    HttpsURLConnection connection = null;
                    URL url = new URL(getString(R.string.url_Categories_Api));

                    try {
                        connection = (HttpsURLConnection) url.openConnection();
                        connection.addRequestProperty("api_Key", getString(R.string.api_Key));


                        int response = connection.getResponseCode();


                        if (response == HttpURLConnection.HTTP_OK) {
                            StringBuilder builder = new StringBuilder();

                            try (BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(connection.getInputStream()))) {

                                String line = "";


                                while ((line = reader.readLine()) != null) {
                                    builder.append(line);

                                }


                            } catch (IOException e) {
                                runOnUiThread(() -> {
                                    Toast.makeText(MainActivity.this, R.string.read_error, Toast.LENGTH_LONG).show();
                                });
                                e.printStackTrace();
                            }

                            JSONObject jsonObject = new JSONObject(builder.toString());
                            JSONArray categories = jsonObject.getJSONArray("categories");
                            Categorie categorieClass = null;
                            Type CategorieListType = new TypeToken<List<Categorie>>() {
                            }.getType();
                            lesCategories = gson.fromJson(jsonObject.get("categories").toString(), CategorieListType);
                            String str = "";
                            for (Categorie c : lesCategories) {

                                str += c.getName() + "\n";
                            }
                            System.out.println(str);

                            System.out.println(builder.toString());

                            // final String description = categories.getJSONObject(2).getString("name");

                            // Log.i("YAG", String.valueOf(lesCategories.size()));

                            /// System.out.println(categories.getJSONObject(0).getString("name"));

                        } else {
                            runOnUiThread(() -> {
                                Toast.makeText(MainActivity.this, R.string.connect_error, Toast.LENGTH_LONG).show();
                            });
                        }
                    } catch (Exception e) {
                        runOnUiThread(() -> {
                            Toast.makeText(MainActivity.this, R.string.connect_error, Toast.LENGTH_LONG).show();
                        });
                        e.printStackTrace();
                    } finally {
                        connection.disconnect(); // fermeture HttpURLConnection
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.i("TAG M", "EXCEPTION 2 ");
                }
            }
        }).start();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (barDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);

    }
}





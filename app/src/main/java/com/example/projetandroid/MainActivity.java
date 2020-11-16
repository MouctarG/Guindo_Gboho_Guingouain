package com.example.projetandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.Interface.CategoryClickListener;
import com.example.projetandroid.model.utils.beans.ArticleTypes;
import com.example.projetandroid.model.utils.beans.CategoriesTypes;
import com.example.projetandroid.model.utils.webservice.GetDataWebService;
import com.example.projetandroid.view.CategorieAdapter;
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


public class MainActivity extends AppCompatActivity implements CategoryClickListener, NavigationView.OnNavigationItemSelectedListener {
    private List<CategoriesTypes> lesCategories;
    private List<ArticleTypes> lesArticles;
    CategorieAdapter categorieAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView rv_category;
    EditText edit_recherche;

    private ActionBarDrawerToggle barDrawerToggle;
    public static final String CLE_CATEGORY_CHOICE = "CLE_CATEGORY_CHOICE";
    public static final String MOTIF_SEARCH = "MOTIF_SEARCH";


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lesCategories = new ArrayList<>();
        lesArticles = new ArrayList<>();
        //    articleTypesList = new ArrayList<>();


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navSide);
        rv_category = findViewById(R.id.rv_Category);
        edit_recherche = findViewById(R.id.edit_recherche);
        barDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(barDrawerToggle);
        barDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GetAllCategories d = new GetAllCategories();
        d.execute();
        categorieAdapter = new CategorieAdapter(this);
        rv_category.setAdapter(categorieAdapter);
        rv_category.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        navigationView.setNavigationItemSelectedListener(this);


        Log.v("TAb", String.valueOf(lesCategories.size()));
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
                            CategoriesTypes categoriesTypesClass = null;
                            Type CategorieListType = new TypeToken<List<CategoriesTypes>>() {
                            }.getType();
                            lesCategories = gson.fromJson(jsonObject.get("categories").toString(), CategorieListType);
                            String str = "";
                            for (CategoriesTypes c : lesCategories) {

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


    public void rechercherArticle(View view) {
        Intent intent = new Intent(MainActivity.this, SearchArticleActivity.class);
        intent.putExtra(MOTIF_SEARCH, edit_recherche.getText().toString());
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mes_commandes) {
            Toast.makeText(MainActivity.this, "OUI", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, CommandeActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }


    public class GetAllCategories extends AsyncTask {

        private ArrayList<CategoriesTypes> res = null;
        private ArrayList<ArticleTypes> resArticleType = null;
        private Exception exception = null;

        /**
         * Appel asynchrone : exécuté sur un thread à part
         * On ne peut pas toucher aux éléments graphiques mais on peut y faire des traitements longs
         */
        @Override
        protected Object doInBackground(Object[] params) {

            try {
                res = GetDataWebService.getAllCategoriesByServer();
                resArticleType = GetDataWebService.getAllArticlesByServer();
            } catch (Exception e) {
                exception = e;
            }

            return null;
        }

        /**
         * Appelée sur le thread principal, on peut toucher aux éléments graphiques
         * mais on ne peut pas y faire de traitements longs
         */
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (exception != null) {
                //Échec
                exception.printStackTrace();

            } else {

                lesCategories.clear();
                lesCategories.addAll(res);
                lesArticles.clear();
                lesArticles.addAll(resArticleType);

                categorieAdapter.setCategoriesTypes((ArrayList<CategoriesTypes>) lesCategories);
                categorieAdapter.notifyDataSetChanged();
                for (CategoriesTypes c : lesCategories)
                    Log.i("TAG", c.getName() + " : ");


            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(CategoriesTypes categoriesTypes) {
        Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
        intent.putExtra(CLE_CATEGORY_CHOICE, categoriesTypes.getId());
        setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (barDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

}





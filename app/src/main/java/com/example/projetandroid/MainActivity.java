package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static final String CLE_CATEGORY_CHOICE = "CLE_CATEGORY_CHOICE";
    public static final String MOTIF_SEARCH = "MOTIF_SEARCH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
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


        homeFragment = new HomeFragment();

         */

    }
/*

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



 */


    public void goToLoginActivity(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToInscription(View view) {
        Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
        startActivity(intent);
    }
}





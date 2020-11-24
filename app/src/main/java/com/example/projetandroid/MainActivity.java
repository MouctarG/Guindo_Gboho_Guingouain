package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Rediriger vers LoginActivity ou vers InscriptionActivity
 */
public class MainActivity extends AppCompatActivity {

    public static final String CLE_CATEGORY_CHOICE = "CLE_CATEGORY_CHOICE";
    public static final String MOTIF_SEARCH = "MOTIF_SEARCH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * Redirection vers LoginActivity
     *
     * @param view
     */
    public void goToLoginActivity(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Redirection vers InscriptionActivity
     *
     * @param view
     */
    public void goToInscription(View view) {
        Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
        startActivity(intent);
    }
}





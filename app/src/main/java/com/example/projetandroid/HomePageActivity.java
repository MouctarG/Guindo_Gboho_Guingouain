package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void goToLoginActivity(View view) {
        Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToInscription(View view) {
        Intent intent = new Intent(HomePageActivity.this, InscriptionActivity.class);
        startActivity(intent);
    }

}
package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetandroid.model.ItemPanierProduct;
import com.example.projetandroid.model.utils.DatabaseHandler;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ConfirmationCommandeActivity extends AppCompatActivity {

    EditText edit_cmd_prenom;
    EditText edit_cmd_nom;
    EditText edit_adresse;
    EditText edit_phone;
    DatabaseHandler databaseHandler;
    private String valTotal = "0";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_commande);
        edit_cmd_prenom = findViewById(R.id.edit_cmd_prenom);
        edit_cmd_nom = findViewById(R.id.edit_cmd_nom);
        edit_adresse = findViewById(R.id.edit_adresse);
        edit_phone = findViewById(R.id.edit_phone);
        databaseHandler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);
        if (getIntent().getExtras() != null) {
            valTotal = getIntent().getExtras().getString("valeurTotal");
        }

    }

    public void confirmation(View view) {
        String nom = edit_cmd_nom.getText().toString();
        String prenom = edit_cmd_prenom.getText().toString();
        String adresse = edit_adresse.getText().toString();
        String phone = edit_phone.getText().toString();
        String nomArticles = getToStringArticle();
        if (nom.trim().length() > 0 && prenom.trim().length() > 0 && adresse.trim().length() > 0) {

            databaseHandler.deletePanier(LoginActivity.LOGIN_USER);
            Format formatter = new SimpleDateFormat("dd-MM-yyyy");
            String date = formatter.format(new Date());
            databaseHandler.addCommande(nomArticles, prenom, nom, adresse, phone, date, valTotal,
                    LoginActivity.LOGIN_USER);
            Toast.makeText(this, getString(R.string.txt_cmd_passer), Toast.LENGTH_LONG)
                    .show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);


        } else Toast.makeText(this, getString(R.string.txt_champ_req), Toast.LENGTH_LONG)
                .show();
    }

    private String getToStringArticle() {
        List<ItemPanierProduct> panierProductList =
                databaseHandler.getItemsPanier(LoginActivity.LOGIN_USER);
        String res = "";
        for (ItemPanierProduct item : panierProductList)
            res += item.getName() + "- ";

        return res;
    }
}
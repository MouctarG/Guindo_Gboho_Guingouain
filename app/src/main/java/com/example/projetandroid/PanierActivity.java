package com.example.projetandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.Interface.ItemPanierClickListener;
import com.example.projetandroid.model.ItemPanierProduct;
import com.example.projetandroid.model.utils.DatabaseHandler;
import com.example.projetandroid.view.PanierItemsAdapter;

import java.util.ArrayList;
import java.util.List;

public class PanierActivity extends AppCompatActivity implements ItemPanierClickListener {
    private List<ItemPanierProduct> panierProductList;
    RecyclerView rv_panier;
    PanierItemsAdapter itemsAdapter;
    ArticleDetailFragment detailFragment;
    Button btn_commander;
    DatabaseHandler databaseHandler;
    TextView val_prix_total_commande;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
        panierProductList = new ArrayList<>();
        databaseHandler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);

        if (getIntent().getExtras() != null) {
            String id = getIntent().getExtras().getString("id");
            double prix = Double.parseDouble(getIntent().getExtras().getString("prix"));
            int qte = Integer.parseInt(getIntent().getExtras().getString("qte"));
            String name = getIntent().getExtras().getString("name");
            double montant = prix * qte;
            String img_url = getIntent().getExtras().getString("image_url");
            String description = getIntent().getExtras().getString("description");


            ItemPanierProduct panierProduct = new ItemPanierProduct(name, id, montant, qte, img_url, description);
            if (!databaseHandler.checkArticle(panierProduct.getName())) {
                databaseHandler.addInPanier(panierProduct, LoginActivity.LOGIN_USER);
            } else databaseHandler.updateItemPanier(panierProduct);

        }
        val_prix_total_commande = findViewById(R.id.val_prix_total_commande);
        panierProductList = databaseHandler.getItemsPanier(LoginActivity.LOGIN_USER);
        double mTotal = calculMontantTotal(panierProductList);
        val_prix_total_commande.setText(String.valueOf(mTotal));
        Log.v("TAG DATABASE", String.valueOf(databaseHandler.getItemsPanier(LoginActivity.LOGIN_USER).size()));
        //panierProductList.add(panierProduct);
        itemsAdapter = new PanierItemsAdapter(this);
        rv_panier = findViewById(R.id.rv_panier);
        btn_commander = findViewById(R.id.btn_commander);

        itemsAdapter.setArticles_panier(panierProductList);
        rv_panier.setAdapter(itemsAdapter);
        rv_panier.setLayoutManager(new LinearLayoutManager(PanierActivity.this));

        detailFragment = new ArticleDetailFragment();


    }

    @Override
    public void onClick(ItemPanierProduct itemPanierProduct) {

        CharSequence options[] = new CharSequence[]{getString(R.string.txt_edit_Item),
                getString(R.string.txt_remove_Item)};
        ;
        AlertDialog.Builder builder = new AlertDialog.Builder(PanierActivity.this);
        builder.setTitle(getString(R.string.txt_optionPanier));
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    Bundle b = new Bundle();
                    b.putString("image_url", itemPanierProduct.getImage_url());

                    b.putString("description", itemPanierProduct.getDescription());
                    b.putString("name", itemPanierProduct.getName());
                    b.putString("prix", String.valueOf(itemPanierProduct.getMontant()));
                    b.putString("id", itemPanierProduct.getArticleId());
                    detailFragment.setArguments(b);
                    btn_commander.setVisibility(View.INVISIBLE);
                    rv_panier.setVisibility(View.INVISIBLE);
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.lienearLayoutIdPanier, detailFragment).commit();
                } else if (which == 1) {

                    databaseHandler.deleteItemPanier(itemPanierProduct.getName());
                    panierProductList.remove(itemPanierProduct);
                    itemsAdapter.notifyDataSetChanged();
                    double mTotal = calculMontantTotal(panierProductList);
                    val_prix_total_commande.setText(String.valueOf(mTotal));
                }
            }
        });
        builder.show();


    }

    private double calculMontantTotal(List<ItemPanierProduct> list) {
        double res = 0;

        for (ItemPanierProduct product : list)
            res += product.getMontant();

        return res;
    }

    public void goToConfirmation(View view) {
        Intent intent = new Intent(PanierActivity.this, ConfirmationCommandeActivity.class);
        startActivity(intent);
    }
}
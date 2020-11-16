package com.example.projetandroid;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetandroid.Interface.ArticleTypeClickListener;
import com.example.projetandroid.model.utils.beans.ArticleTypes;
import com.example.projetandroid.model.utils.webservice.GetDataWebService;
import com.example.projetandroid.view.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity implements ArticleTypeClickListener {

    ListArticleFragment articleFragment;
    ArticleDetailFragment detailFragment;
    String categoryId = "";
    List<ArticleTypes> articleTypesList;
    ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        articleAdapter = new ArticleAdapter(this);
        articleFragment = new ListArticleFragment(articleAdapter, this);
        articleTypesList = new ArrayList<>();
        categoryId = getIntent().getExtras().getString(MainActivity.CLE_CATEGORY_CHOICE);

        detailFragment = new ArticleDetailFragment();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.prod_list_fragment, articleFragment).commit();

        new GetArticlesByCategory().execute();


    }


    public class GetArticlesByCategory extends AsyncTask {

        private ArrayList<ArticleTypes> resArticleType = null;
        private Exception exception = null;

        /**
         * Appel asynchrone : exécuté sur un thread à part
         * On ne peut pas toucher aux éléments graphiques mais on peut y faire des traitements longs
         */
        @Override
        protected Object doInBackground(Object[] params) {

            try {
                resArticleType = GetDataWebService.getArticleByCategory(categoryId);

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

                articleTypesList.clear();
                articleTypesList.addAll(resArticleType);
                articleFragment.getArticleAdapter().setArticleTypes(articleTypesList);
                articleFragment.getArticleAdapter().notifyDataSetChanged();


            }
        }
    }

    @Override
    public void onClick(ArticleTypes articleTypes) {
        Bundle b = new Bundle();
        b.putString("image_url", articleTypes.getImage().getSizes().get(0).getUrl());

        b.putString("description", articleTypes.getShortDescription());
        b.putString("name", articleTypes.getName());
        b.putString("prix", String.valueOf(articleTypes.getMaximumPrice()));
        b.putString("id", articleTypes.getId());
        detailFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.prod_list_fragment, detailFragment).commit();
    }


}
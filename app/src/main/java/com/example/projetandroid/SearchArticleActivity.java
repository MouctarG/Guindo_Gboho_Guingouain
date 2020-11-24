package com.example.projetandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetandroid.Interface.ArticleTypeClickListener;
import com.example.projetandroid.model.utils.beans.ArticleTypes;
import com.example.projetandroid.model.utils.webservice.GetDataWebService;
import com.example.projetandroid.view.ArticleAdapter;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Pour la recherche d'un article soit par son nom ou soit par sa categorie
 */
public class SearchArticleActivity extends AppCompatActivity implements ArticleTypeClickListener {
    ListArticleFragment articlesFragment;
    List<ArticleTypes> articleTypesList;
    ArticleAdapter articleAdapter;
    private String motif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_article);
        articleAdapter = new ArticleAdapter(this);
        articlesFragment = new ListArticleFragment(articleAdapter, this);
        articleTypesList = new ArrayList<>();
        motif = getIntent().getExtras().getString(MainActivity.MOTIF_SEARCH);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_search_layout, articlesFragment).commit();
        ;

        new GetArticleBysearch().execute();
    }

    @Override
    public void onClick(ArticleTypes articleTypes) {

    }

    public class GetArticleBysearch extends AsyncTask<URL, Void, JSONObject> {
        private ArrayList<ArticleTypes> resArticleType = null;
        private Exception exception = null;


        @Override
        protected JSONObject doInBackground(URL... urls) {

            try {

                resArticleType = GetDataWebService.getAllArticlesBySearch(motif);

            } catch (Exception e) {

            }


            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            if (!resArticleType.isEmpty()) {

                articleTypesList.clear();
                articleTypesList.addAll(resArticleType);
                articlesFragment.getArticleAdapter().setArticleTypes(articleTypesList);
                articleAdapter.notifyDataSetChanged();


            } else {
                Toast.makeText(SearchArticleActivity.this, getText(R.string.msg_aucun_article), Toast.LENGTH_LONG).show();
            }

        }
    }
}
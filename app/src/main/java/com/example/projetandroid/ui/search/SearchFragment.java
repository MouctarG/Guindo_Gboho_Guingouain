package com.example.projetandroid.ui.search;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.ArticleDetailFragment;
import com.example.projetandroid.Interface.ArticleTypeClickListener;
import com.example.projetandroid.R;
import com.example.projetandroid.model.utils.beans.ArticleTypes;
import com.example.projetandroid.model.utils.webservice.GetDataWebService;
import com.example.projetandroid.view.ArticleAdapter;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilisé par SearchArticleActivity pour le listing des articles selon un motif de recherche
 */
public class SearchFragment extends Fragment implements ArticleTypeClickListener {

    EditText edit_recherche;
    Button btn_search;
    RecyclerView rv_list_article;
    ArticleAdapter articleAdapter;
    List<ArticleTypes> articleTypesList;
    ArticleDetailFragment detailFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.search_articles, container, false);
        edit_recherche = root.findViewById(R.id.edit_recherche);
        btn_search = root.findViewById(R.id.btn_search);
        rv_list_article = root.findViewById(R.id.rv_article);
        articleAdapter = new ArticleAdapter(this);
        rv_list_article.setAdapter(articleAdapter);
        rv_list_article.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        detailFragment = new ArticleDetailFragment();

        articleTypesList = new ArrayList<>();
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetArticleBysearch().execute();
            }
        });
        return root;
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
        rv_list_article.setVisibility(View.INVISIBLE);
        FragmentTransaction transaction = getActivity()
                .getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fram_id_search, detailFragment);
        transaction.replace(R.id.fram_id_search, detailFragment);
        transaction.addToBackStack(null);
        transaction.replace(R.id.fram_id_search, detailFragment).commit();
    }


    public class GetArticleBysearch extends AsyncTask<URL, Void, JSONObject> {
        private ArrayList<ArticleTypes> resArticleType = new ArrayList<>();
        private Exception exception = null;


        @Override
        protected JSONObject doInBackground(URL... urls) {

            try {
                String motif = edit_recherche.getText().toString();

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
                articleAdapter.setArticleTypes(articleTypesList);
                articleAdapter.notifyDataSetChanged();


            } else {
                Toast.makeText(getContext(), getText(R.string.msg_aucun_article), Toast.LENGTH_LONG).show();
            }

        }
    }
}
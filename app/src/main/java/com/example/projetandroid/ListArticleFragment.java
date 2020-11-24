package com.example.projetandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.Interface.ArticleTypeClickListener;
import com.example.projetandroid.model.utils.beans.ArticleTypes;
import com.example.projetandroid.view.ArticleAdapter;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 * <p>
 * Affichage de la liste des artiles en utilisant un RecyclerView
 */
public class ListArticleFragment extends Fragment implements ArticleTypeClickListener {
    RecyclerView rv_list_article;
    ArticleAdapter articleAdapter;
    ArticleTypeClickListener clickListener;

    public ListArticleFragment() {
        // Required empty public constructor
    }

    public ListArticleFragment(ArticleAdapter articleAdapter, ArticleTypeClickListener clickListener) {
        this.articleAdapter = articleAdapter;
        this.clickListener = clickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_article, container, false);
        rv_list_article = view.findViewById(R.id.rv_list_article);
        articleAdapter = new ArticleAdapter(this);
        rv_list_article.setAdapter(articleAdapter);
        rv_list_article.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

    public ArticleAdapter getArticleAdapter() {
        return articleAdapter;
    }

    @Override
    public void onClick(ArticleTypes articleTypes) {
        clickListener.onClick(articleTypes);
    }
}
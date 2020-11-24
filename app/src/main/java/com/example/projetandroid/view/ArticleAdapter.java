package com.example.projetandroid.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projetandroid.Interface.ArticleTypeClickListener;
import com.example.projetandroid.R;
import com.example.projetandroid.model.utils.beans.ArticleTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter d'un article permettant son affichage avec un RecyclerView
 * (permet d'indiquer au RecyclerView à quoi va ressembler une ligne )
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<ArticleTypes> articleTypes;
    ArticleTypeClickListener clickListener;

    public ArticleAdapter(ArticleTypeClickListener clickListener) {
        this.clickListener = clickListener;
        articleTypes = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.article_items_layout, parent, false);
        return new ArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleTypes article = articleTypes.get(position);

        holder.article_name.setText(article.getName());
        holder.article_prix.setText(String.valueOf(article.getMaximumPrice()) + " €");
        Glide.with(holder.itemView.getContext())
                .load(article.getImage().getSizes().get(1).getUrl()).into(holder.image_article);
        holder.view_root_articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onClick(article);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleTypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView article_name;
        public TextView article_prix;
        public ImageView image_article;
        public View view_root_articles;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            article_name = itemView.findViewById(R.id.article_name);
            article_prix = itemView.findViewById(R.id.prix_article);
            image_article = itemView.findViewById(R.id.article_image);
            view_root_articles = itemView.findViewById(R.id.view_root_articles);


        }
    }

    public void setArticleTypes(List<ArticleTypes> articleTypes) {
        this.articleTypes = articleTypes;
    }
}

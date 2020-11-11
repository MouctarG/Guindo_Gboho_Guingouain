package com.example.projetandroid.model.ViewItems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.R;
import com.example.projetandroid.utils.beans.CategoriesTypes;

import java.util.ArrayList;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.ViewHolder> {


    public TextView txtCategoryName;
    public ImageView imageView;
    private ArrayList<CategoriesTypes> categoriesTypes;

    public CategorieAdapter(ArrayList<CategoriesTypes> categoriesTypes) {
        this.categoriesTypes = categoriesTypes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.categorie_items_layout, parent, false);
        return new CategorieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoriesTypes cat = categoriesTypes.get(position);

        txtCategoryName.setText(cat.getName());
        switch (cat.getName()) {
            case "Santé et nutrition":
                imageView.setImageResource(R.drawable.fruit_legume);
                break;
            case "Beauté":
                imageView.setImageResource(R.drawable.beaute_img);
                break;
            case "Bébé":
                imageView.setImageResource(R.drawable.baby_img);
                break;
            case "Bijoux":
                imageView.setImageResource(R.drawable.bijoux_img);
                break;
            case "Accessoires pour animaux":
                imageView.setImageResource(R.drawable.animal_img);
                break;
            case "Électronique":
                imageView.setImageResource(R.drawable.electronic_img);
                break;
            case "Articles de fête":
                imageView.setImageResource(R.drawable.fete_img);
                break;
            case "Accueil magasin":
                imageView.setImageResource(R.drawable.acceuil_img);
                break;


        }


    }

    @Override
    public int getItemCount() {
        return categoriesTypes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoryName = itemView.findViewById(R.id.category_name);
            imageView = itemView.findViewById(R.id.category_image);

        }
    }
}

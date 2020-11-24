package com.example.projetandroid.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.Interface.CategoryClickListener;
import com.example.projetandroid.R;
import com.example.projetandroid.model.utils.beans.CategoriesTypes;

import java.util.ArrayList;

/**
 * Adapter d'une  Categorie permettant son affichage avec un RecyclerView
 * (permet d'indiquer au RecyclerView à quoi va ressembler une ligne )
 */
public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.ViewHolder> {

    private ArrayList<CategoriesTypes> categoriesTypes;
    CategoryClickListener clickListener;

    public CategorieAdapter(CategoryClickListener clickListener) {
        this.categoriesTypes = new ArrayList<>();
        this.clickListener = clickListener;
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

        holder.txtCategoryName.setText(cat.getName());
        switch (cat.getName()) {
            case "Santé et nutrition":
                holder.imageView.setImageResource(R.drawable.fruit_legume);
                break;
            case "Beauté":
                holder.imageView.setImageResource(R.drawable.beaute_img);
                break;
            case "Bébé":
                holder.imageView.setImageResource(R.drawable.baby_img);
                break;
            case "Bijoux":
                holder.imageView.setImageResource(R.drawable.bijoux_img);
                break;
            case "Accessoires pour animaux":
                holder.imageView.setImageResource(R.drawable.animal_img);
                break;
            case "Électronique":
                holder.imageView.setImageResource(R.drawable.electronic_img);
                break;
            case "Articles de fête":
                holder.imageView.setImageResource(R.drawable.fete_img);
                break;
            case "Accueil magasin":
                holder.imageView.setImageResource(R.drawable.acceuil_img);
                break;
            case "Auto":
                holder.imageView.setImageResource(R.drawable.auto_img);
                break;
            default:
                break;

        }
        holder.view_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onClick(cat);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoriesTypes.size();
    }

    public void setCategoriesTypes(ArrayList<CategoriesTypes> categoriesTypes) {
        this.categoriesTypes = categoriesTypes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCategoryName;
        public ImageView imageView;
        public View view_root;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoryName = itemView.findViewById(R.id.category_name);
            imageView = itemView.findViewById(R.id.category_image);
            view_root = itemView.findViewById(R.id.category_items_root);

        }
    }
}

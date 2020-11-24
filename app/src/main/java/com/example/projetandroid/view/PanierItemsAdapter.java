package com.example.projetandroid.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.Interface.ItemPanierClickListener;
import com.example.projetandroid.R;
import com.example.projetandroid.model.ItemPanierProduct;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Adapter du panier  permettant son affichage avec un RecyclerView
 * (permet d'indiquer au RecyclerView à quoi va ressembler une ligne )
 */
public class PanierItemsAdapter extends RecyclerView.Adapter<PanierItemsAdapter.ViewHolder> {
    private List<ItemPanierProduct> articles_panier;
    ItemPanierClickListener clickListener;

    public PanierItemsAdapter(ItemPanierClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.panier_items_layout, parent, false);
        return new PanierItemsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemPanierProduct product = articles_panier.get(position);
        holder.panier_product_name.setText(product.getName());
        String strQte = String.valueOf(product.getQuantite());
        double montant = product.getMontant() * product.getQuantite();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formatted = decimalFormat.format(montant);
        holder.panier_product_price.setText(formatted);
        holder.panier_product_qte_id.setText(strQte);

        holder.panier_items_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(product);
            }
        });


    }

    @Override
    public int getItemCount() {
        return articles_panier.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView panier_product_price;
        TextView panier_product_name;
        TextView panier_product_qte_id;
        View panier_items_root;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            panier_product_price = itemView.findViewById(R.id.panier_product_price);
            panier_product_name = itemView.findViewById(R.id.panier_product_name);
            panier_product_qte_id = itemView.findViewById(R.id.panier_product_qte_id);
            panier_items_root = itemView.findViewById(R.id.panier_items_root);


        }

    }

    public List<ItemPanierProduct> getArticles_panier() {
        return articles_panier;
    }

    public void setArticles_panier(List<ItemPanierProduct> articles_panier) {
        this.articles_panier = articles_panier;
    }
}

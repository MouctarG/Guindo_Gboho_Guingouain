package com.example.projetandroid.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.R;
import com.example.projetandroid.model.utils.Commande;

import java.util.List;

public class CommandeAdapter extends RecyclerView.Adapter<CommandeAdapter.ViewHolder> {
    List<Commande> commandes;

    public CommandeAdapter(List<Commande> commandes) {
        this.commandes = commandes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.commande_list_layout, parent, false);
        return new CommandeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Commande commande = commandes.get(position);
        holder.panier_cmd_date.setText(commande.getDate());
        holder.panier_cmd_price.setText(commande.getMontant());
        holder.panier_product_name_cmd.setText(commande.getNomArticles());
    }

    @Override
    public int getItemCount() {
        return commandes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView panier_cmd_price;
        TextView panier_cmd_date;
        TextView panier_product_name_cmd;

        View commande_root;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            panier_cmd_price = itemView.findViewById(R.id.panier_cmd_price);
            panier_cmd_date = itemView.findViewById(R.id.panier_cmd_date);
            panier_product_name_cmd = itemView.findViewById(R.id.panier_product_name_cmd);
            commande_root = itemView.findViewById(R.id.commande_root);


        }
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}

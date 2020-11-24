package com.example.projetandroid.ui.commandeshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.LoginActivity;
import com.example.projetandroid.R;
import com.example.projetandroid.model.utils.Commande;
import com.example.projetandroid.model.utils.DatabaseHandler;
import com.example.projetandroid.view.CommandeAdapter;

import java.util.List;

/**
 * Listing de tous les commandes deja effectuer
 */
public class SlideshowFragment extends Fragment {

    CommandeAdapter commandeAdapter;
    RecyclerView rv_commande;
    DatabaseHandler databaseHandler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        rv_commande = root.findViewById(R.id.rv_commande);
        databaseHandler = new DatabaseHandler(getContext(), DatabaseHandler.DATABASE_NAME,
                null, DatabaseHandler.DATABASE_VERSION);
        List<Commande> commandes = databaseHandler.getAllCommande(LoginActivity.LOGIN_USER);
        commandeAdapter = new CommandeAdapter(commandes);
        rv_commande.setAdapter(commandeAdapter);
        rv_commande.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;
    }
}
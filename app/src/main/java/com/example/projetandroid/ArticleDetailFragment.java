package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 * Pour afficher le detail d'un article
 */
public class ArticleDetailFragment extends Fragment {

    NumberPicker product_detail_numberPicker;
    TextView product_detail_name;
    TextView product_detail_price;
    ImageView product_details_img;
    TextView product_detail_description;
    Button btn_add_panier;

    private String description;
    private String url_image_string;
    private String name;
    private String prix;
    private String articleId;
    private int quantite;

    public ArticleDetailFragment() {
        // Required empty public constructor
    }


    public static ArticleDetailFragment newInstance(String param1, String param2) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);
        product_detail_numberPicker = view.findViewById(R.id.product_detail_numberPicker);
        product_detail_name = view.findViewById(R.id.product_detail_name);
        product_detail_price = view.findViewById(R.id.product_detail_price);
        product_details_img = view.findViewById(R.id.product_details_img);
        product_detail_description = view.findViewById(R.id.product_detail_description);
        btn_add_panier = view.findViewById(R.id.btn_add_panier);

        btn_add_panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToPanier();
            }
        });

        product_detail_numberPicker.setMaxValue(5);
        product_detail_numberPicker.setMinValue(1);
        Bundle bundle = getArguments();
        url_image_string = bundle.getString("image_url");
        name = bundle.getString("name");
        prix = bundle.getString("prix");
        description = bundle.getString("description");
        articleId = bundle.getString("id");

        product_detail_name.setText(name);
        product_detail_price.setText(prix);
        product_detail_description.setText(description);
        Glide.with(view.getContext())
                .load(url_image_string).into(product_details_img);

        return view;
    }

    /**
     * Ajoute dans le panier un article
     */
    public void addToPanier() {
        // name = product_detail_name.getText().toString();
        //   prix = product_detail_price.getText().toString();
        quantite = product_detail_numberPicker.getValue();
        Intent intent = new Intent(getContext(), PanierActivity.class);

        intent.putExtra("id", articleId);
        intent.putExtra("prix", String.valueOf(prix));
        intent.putExtra("qte", String.valueOf(quantite));
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        intent.putExtra("image_url", url_image_string);

        startActivity(intent);


    }


}
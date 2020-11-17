package com.example.projetandroid.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.Interface.CategoryClickListener;
import com.example.projetandroid.ProductsActivity;
import com.example.projetandroid.R;
import com.example.projetandroid.model.utils.beans.ArticleTypes;
import com.example.projetandroid.model.utils.beans.CategoriesTypes;
import com.example.projetandroid.model.utils.webservice.GetDataWebService;
import com.example.projetandroid.view.CategorieAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.projetandroid.MainActivity.CLE_CATEGORY_CHOICE;

public class HomeFragment extends Fragment implements CategoryClickListener {
    CategorieAdapter categorieAdapter;
    RecyclerView rv_category;
    private List<CategoriesTypes> lesCategories;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rv_category = root.findViewById(R.id.rv_Category);
        lesCategories = new ArrayList<>();
        GetAllCategories d = new GetAllCategories();
        d.execute();
        categorieAdapter = new CategorieAdapter(this);
        rv_category.setAdapter(categorieAdapter);
        rv_category.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return root;
    }

    @Override
    public void onClick(CategoriesTypes categoriesTypes) {
        Intent intent = new Intent(getContext(), ProductsActivity.class);
        intent.putExtra(CLE_CATEGORY_CHOICE, categoriesTypes.getId());
        getActivity().setResult(Activity.RESULT_OK, intent);
        startActivity(intent);
    }

    public class GetAllCategories extends AsyncTask {

        private ArrayList<CategoriesTypes> res = null;
        private ArrayList<ArticleTypes> resArticleType = null;
        private Exception exception = null;

        /**
         * Appel asynchrone : exécuté sur un thread à part
         * On ne peut pas toucher aux éléments graphiques mais on peut y faire des traitements longs
         */
        @Override
        protected Object doInBackground(Object[] params) {

            try {
                res = GetDataWebService.getAllCategoriesByServer();
                resArticleType = GetDataWebService.getAllArticlesByServer();
            } catch (Exception e) {
                exception = e;
            }

            return null;
        }

        /**
         * Appelée sur le thread principal, on peut toucher aux éléments graphiques
         * mais on ne peut pas y faire de traitements longs
         */
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (exception != null) {
                //Échec
                exception.printStackTrace();

            } else {

                lesCategories.clear();
                lesCategories.addAll(res);


                categorieAdapter.setCategoriesTypes((ArrayList<CategoriesTypes>) lesCategories);
                categorieAdapter.notifyDataSetChanged();
                for (CategoriesTypes c : lesCategories)
                    Log.i("TAG", c.getName() + " : ");


            }
        }
    }
}
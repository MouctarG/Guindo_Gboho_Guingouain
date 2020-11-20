package com.example.projetandroid.model.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.projetandroid.model.ItemPanierProduct;
import com.example.projetandroid.model.User;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String TABLE_USER_DROP = "DROP TABLE IF EXISTS " + TABLE_USER + ";";


    //TABLE PANIER

    public static final String TABLE_PANIER = "panier";
    public static final String COLUMN_ID_PANIER = "id";
    public static final String COLUMN_LOGIN_USER = "login_id";
    public static final String COLUMN_QUANTITE = "quantite";
    public static final String COLUMN_MONTANT = "montant";
    public static final String COLUMN_NAME_ARTICLE = "name_article";
    public static final String COLUMN_IMG_URL = "image_url";
    public static final String COLUMN_ARTICLE_DESCRIPTION = "description";
    public static final String COLUMN_ARTICLE_ID = "article_id";
    public static final String TABLE_PANIER_DROP = "DROP TABLE IF EXISTS " + TABLE_PANIER + ";";


    //TABLE COMMANDE

    public static final String TABLE_COMMANDE = "commande";
    public static final String COLUMN_ID_COMMANDE = "id_commande";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_ADRESSE = "adresse";
    public static final String COLUMN_TELEPHONE = "telephone";
    public static final String COLUMN_DATE_COMMANDE = "date_commande";


    public static final String DATABASE_NAME = "users.db";
    public static final int DATABASE_VERSION = 1;


    String requeteCreateTablePanier =
            "CREATE TABLE " + TABLE_PANIER + " ( " +
                    COLUMN_ID_PANIER + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    COLUMN_LOGIN_USER + " TEXT NOT NULL ," +
                    COLUMN_QUANTITE + " INTERGER NOT NULL , " +
                    COLUMN_MONTANT + " DOUBLE NOT NULL , " +
                    COLUMN_NAME_ARTICLE + " TEXT NOT NULL , " +
                    COLUMN_ARTICLE_DESCRIPTION + " TEXT NOT NULL , " +
                    COLUMN_ARTICLE_ID + " TEXT NOT NULL ," +
                    COLUMN_IMG_URL + " TEXT NOT NULL " +
                    ");";

    String requeteCreateTableUser =
            "CREATE TABLE " + TABLE_USER + " ( " +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    COLUMN_LOGIN + " INTEGER NOT NULL ," +
                    COLUMN_PASSWORD + " TEXT NOT NULL " +
                    ");";


    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DatabaseHandler(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(requeteCreateTableUser);
        db.execSQL(requeteCreateTablePanier);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_USER_DROP);
        db.execSQL(TABLE_PANIER_DROP);
        onCreate(db);
    }

    public void addUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOGIN, user.getLogin());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USER, null, contentValues);
        db.close();

    }

    public void deleteUser(String pseudo) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER + " WHERE " + COLUMN_LOGIN + "=\"" + pseudo + "\"");
    }

    public User checkUser(String pseudo, String password) {
        User user = null;

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_LOGIN + "=\"" + pseudo +
                "\"" +
                " AND " + COLUMN_PASSWORD + "=\"" + password + "\"";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            user = new User(cursor.getString(cursor.getColumnIndex(COLUMN_LOGIN)));
        }
        return user;
    }

    public void updatePassword(String login, String newPassword) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_USER + "SET " + COLUMN_PASSWORD + "=\"" + newPassword
                + "=\"" + "WHERE " + COLUMN_LOGIN + "=\"" + login +
                "\"";
        db.execSQL(query);

    }


    public User checkUser(String login) {
        User user = null;

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_LOGIN + "=\"" + login +
                "\"";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            user = new User(cursor.getString(cursor.getColumnIndex(COLUMN_LOGIN)));
        }
        return user;
    }


    public void addInPanier(ItemPanierProduct product, String loginUser) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOGIN_USER, loginUser);
        contentValues.put(COLUMN_NAME_ARTICLE, product.getName());
        contentValues.put(COLUMN_MONTANT, product.getMontant());
        contentValues.put(COLUMN_QUANTITE, product.getQuantite());
        contentValues.put(COLUMN_ARTICLE_ID, product.getArticleId());
        contentValues.put(COLUMN_ARTICLE_DESCRIPTION, product.getDescription());
        contentValues.put(COLUMN_IMG_URL, product.getImage_url());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PANIER, null, contentValues);
        db.close();
    }

    public void deleteItemPanier(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PANIER + " WHERE " + COLUMN_NAME_ARTICLE + "=\"" + name + "\"");
    }

    public void updateItemPanier(ItemPanierProduct product) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_PANIER + " SET " + COLUMN_QUANTITE + "=\"" + product.getQuantite()
                + "\"" + ", " + COLUMN_MONTANT + "=\"" + product.getMontant() +
                "\"" + " WHERE " + COLUMN_NAME_ARTICLE + "=\"" + product.getName() +
                "\"";
        db.execSQL(query);
    }


    public List<ItemPanierProduct> getItemsPanier(String loginUser) {
        User user = null;
        List<ItemPanierProduct> panierProductList = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PANIER + " WHERE " + COLUMN_LOGIN_USER + "=\"" + loginUser +
                "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ARTICLE));
                String montant = cursor.getString(cursor.getColumnIndex(COLUMN_MONTANT));
                String qte = cursor.getString(cursor.getColumnIndex(COLUMN_QUANTITE));
                String img_url = cursor.getString(cursor.getColumnIndex(COLUMN_IMG_URL));
                String idArticle = cursor.getString(cursor.getColumnIndex(COLUMN_ARTICLE_ID));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_ARTICLE_DESCRIPTION));

                ItemPanierProduct itemPanierProduct = new ItemPanierProduct(name, idArticle,
                        Double.parseDouble(montant), Integer.parseInt(qte), img_url, description);
                panierProductList.add(itemPanierProduct);

            } while (cursor.moveToNext());


        }
        if (!cursor.isClosed())
            cursor.close();
        return panierProductList;
    }

    public boolean checkArticle(String name) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PANIER + " WHERE " + COLUMN_NAME_ARTICLE + "=\"" + name +
                "\"";

        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount() > 0;
    }


}

package com.example.projetandroid.utils;

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

import com.example.projetandroid.model.User;


public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String TABLE_USER_DROP = "DROP TABLE IF EXISTS " + TABLE_USER + ";";

    public static final String DATABASE_NAME = "users.db";


    String requeteCreateTableUser =
            "CREATE TABLE " + TABLE_USER + " ( " +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    COLUMN_LOGIN + " TEXT NOT NULL ," +
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_USER_DROP);
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

    public String showDatabase() {
        StringBuilder dbString = new StringBuilder();
        SQLiteDatabase database = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE 1";

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex("pseudo")) != null) {
                dbString.append(cursor.getString(cursor.getColumnIndex("pseudo")))
                        .append("\n");

            }
        }
        database.close();
        return String.valueOf(dbString);
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
}

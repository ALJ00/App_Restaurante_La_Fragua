package com.example.proyectoapplafragua;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSqliteOpenHelper extends SQLiteOpenHelper {

    // metodo para crear, abrir o gestionar la bd
    public AdminSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {

        // creo la base de datos articulos
        BaseDeDatos.execSQL("create table productos(codigo string primary key , nombre text, descripcion text, int imagen, precio real)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

package com.example.entrenamientosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


// clase AdminSqliteOpenHelper que hereda de SQLiteOpenHelper para gestionar la base de datos
public class AdminSqliteOpenHelper extends SQLiteOpenHelper {

    // metodo para crear, abrir o gestionar la bd
    public AdminSqliteOpenHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {

        // creo la base de datos articulos
        BaseDeDatos.execSQL("create table articulos(codigo int primary key , nombre text, precio real)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


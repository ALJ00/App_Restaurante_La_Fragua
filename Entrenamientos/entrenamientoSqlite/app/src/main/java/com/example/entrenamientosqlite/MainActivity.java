package com.example.entrenamientosqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.EventObject;

public class MainActivity extends AppCompatActivity {

    // Variables de acceso a los textView
    private EditText et_codigo, et_descripcion, et_precio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        limpiarTexfield();


    }

    // Metodo para dar de alta los productos
    public void registrar(View view) {

        // creo objeto
        AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper
                (this, "administracion", null, 1);

        //indico que se va a abrir la bd en modo lectura y escritura
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //recojo los datos del usuario
        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        //compruebo que los editText contengan datos
        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            BaseDeDatos.insert("articulos", null, registro);

            BaseDeDatos.close();
            limpiarTexfield();

        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();

            //Limpio los campos de texto
            limpiarTexfield();

        }

    }

    // Metodo para consultar un articulo o producto
    public void Buscar(View view) {



        AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper
                (this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select descripcion, precio from articulos where codigo = " + codigo, null);

            if (fila.moveToFirst()) {
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));

                BaseDeDatabase.close();

            } else {
                Toast.makeText(this, "no existe el artículo", Toast.LENGTH_SHORT).show();

                limpiarTexfield();

                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el código del artículo", Toast.LENGTH_SHORT).show();
            limpiarTexfield();
        }

    }

    // Metodo para eliminar un producto o articulo
    public void Eliminar(View view) {

        AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {

            int cantidad = bd.delete("articulos", "codigo=" + codigo, null);

            limpiarTexfield();

            if (cantidad == 1) {
                Toast.makeText(this, "artículo eliminado correctamente", Toast.LENGTH_SHORT).show();
                limpiarTexfield();
            } else {
                Toast.makeText(this, "artículo no existe", Toast.LENGTH_SHORT).show();
                limpiarTexfield();
            }

        } else {
            Toast.makeText(this, "Debes introducir el código del artículo", Toast.LENGTH_SHORT).show();

            limpiarTexfield();

        }

    }

    // Metodo para modificar un articulo o producto
    public void Modificar(View view) {

        AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String precio = et_precio.getText().toString();
        String descripcion = et_descripcion.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("precio", precio);
            registro.put("descripcion", descripcion);

            int cantidad = bd.update
                    ("articulos", registro, "codigo = "+ codigo, null);

            bd.close();

            if(cantidad == 1){
                Toast.makeText(this, "artículo modificado correctamente", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "el artículo no existe", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "debes rellenar todos los campos", Toast.LENGTH_SHORT).show();

        }

    }

    // metodo para limpiar textFields
    private void limpiarTexfield() {
        //Limpio los campos de texto e indico lo que debe introducir el usuario
        et_codigo = (EditText) findViewById(R.id.txt_codigo);
        et_descripcion = (EditText) findViewById(R.id.txt_descripcion);
        et_precio = (EditText) findViewById(R.id.txt_precio);
    }

}

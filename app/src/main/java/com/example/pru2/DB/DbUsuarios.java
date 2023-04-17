package com.example.pru2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.sql.SQLData;

public class DbUsuarios extends DbHelper{

    Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String nombre, String usuario, String contrasena, String correo, String telefono){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("usuario", usuario);
            values.put("contrasena", contrasena);
            values.put("correo", correo);
            values.put("telefono", telefono);

            id = db.insert(TABLE_USUARIOS, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;

    }
}

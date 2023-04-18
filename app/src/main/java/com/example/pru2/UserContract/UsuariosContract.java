package com.example.pru2.UserContract;

import static com.example.pru2.DB.DbHelper.TABLE_CONTRASEÑAS;
import static com.example.pru2.DB.DbHelper.TABLE_USUARIOS;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.example.pru2.MyData.MyData;
import com.example.pru2.MyData.MyInfo;

import java.io.Serializable;

public class UsuariosContract implements Serializable {

    public static abstract class UsuarioEntry implements BaseColumns {
        public static final String USUARIO = "usuario";

        public static final String getCreateTable() {
            String table = "CREATE TABLE " + TABLE_USUARIOS + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "usuario TEXT NOT NULL UNIQUE," +
                    "paswd TEXT NOT NULL," +
                    "correo TEXT NOT NULL," +
                    "num TEXT," +
                    "edad TEXT" +
                    ")";
            return table;
        }

        public static ContentValues toContentValues(MyInfo info) {
            ContentValues values = new ContentValues();
            values.put("nombre", info.getNombre());
            values.put("usuario", info.getUser());
            values.put("paswd", info.getContrasena());
            values.put("correo", info.getCorreo());
            values.put("num", info.getNumero());
            values.put("edad", info.getEdad());
            return values;
        }
    }

    public abstract static class MyDataEntry implements BaseColumns {
        public static final String getCreateTable() {
            String table = "CREATE TABLE " + TABLE_CONTRASEÑAS + "(" +
                    "id_contra INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "contra TEXT NOT NULL," +
                    "user_c TEXT NOT NULL," +
                    "id INTEGER NOT NULL)";
            return table;
        }

        public static ContentValues toContentValues(MyData myData) {
            ContentValues values = new ContentValues();
            values.put("contra", myData.getContra());
            values.put("user_c", myData.getUsuario());
            values.put("id", myData.getId_usr());
            return values;
        }
    }
}


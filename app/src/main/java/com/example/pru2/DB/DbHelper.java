package com.example.pru2.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pru2.UserContract.UsuariosContract;

public class DbHelper extends SQLiteOpenHelper {

    public static final String TAG = "BDUSCON";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NOMBRE = "registro.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_CONTRASEÑAS = "t_contras";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UsuariosContract.UsuarioEntry.getCreateTable());
        sqLiteDatabase.execSQL(UsuariosContract.MyDataEntry.getCreateTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTRASEÑAS);
        onCreate(sqLiteDatabase);
    }
}

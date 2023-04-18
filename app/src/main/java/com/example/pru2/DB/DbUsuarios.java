package com.example.pru2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pru2.MyData.MyInfo;
import com.example.pru2.UserContract.UsuariosContract;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class DbUsuarios extends DbHelper{

    Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long save(MyInfo info) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            id = db.insert(TABLE_USUARIOS, null, UsuariosContract.UsuarioEntry.toContentValues(info));

        } catch (Exception ex) {
            ex.toString();
        } finally {
            return id;
        }
    }
    public List<MyInfo> getUsuarios( )
    {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<MyInfo>usuarios = null;
        MyInfo usuario = null;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT*FROM "+TABLE_USUARIOS,null);
        if( cursor == null )
        {
            return null;
        }
        if( cursor.getCount() < 1)
        {
            return null;
        }
        if( !cursor.moveToFirst() )
        {
            return null;
        }
        Log.d(TAG, "" + cursor.getCount());
        usuarios = new ArrayList<MyInfo>( );
        for( int i = 0; i < cursor.getCount(); i++)
        {
            usuario = new MyInfo( );
            usuario.setNombre(cursor.getString(0));
            usuario.setUser( cursor.getString( 1 ) );
            usuario.setContrasena(cursor.getString(2));
            usuario.setCorreo(cursor.getString(3));
            usuario.setNumero(cursor.getString(4));
            usuario.setEdad(cursor.getString(5));
            usuarios.add( usuario );
            cursor.moveToNext( );
        }
        return usuarios;
    }

    public MyInfo GetUsuario(String user){
        MyInfo info = new MyInfo();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=null;
        String query = "SELECT * FROM t_usuarios WHERE usuario = ?";
        String[] args = {user};

        cursor = db.rawQuery(query,args);
        if (cursor.moveToFirst()) {
            info.setId_usr(cursor.getInt(0));
            info.setNombre(cursor.getString(1));
            info.setUser( cursor.getString( 2 ) );
            info.setContrasena(cursor.getString(3));
            info.setCorreo(cursor.getString(4));
            info.setNumero(cursor.getString(5));
            info.setEdad(cursor.getString(6));
            return info;
        }
        cursor.close();
        return null;
    }

    public boolean AlterUser(String user,String contrasena){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        try{
            db.execSQL("UPDATE " + TABLE_USUARIOS + " SET paswd = '" + contrasena + "' WHERE usuario='" + user + "'");
            correcto = true;
        }catch(Exception ex){
            ex.toString();
        }
        return correcto;
    }
}

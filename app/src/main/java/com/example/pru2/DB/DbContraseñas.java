package com.example.pru2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pru2.MyData.MyData;
import com.example.pru2.UserContract.UsuariosContract;

import java.util.ArrayList;
import java.util.List;

public class DbContraseñas extends DbHelper{

    Context context;
    public DbContraseñas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long saveContraseña(MyData myData){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values= new ContentValues();
            id = db.insert(TABLE_CONTRASEÑAS,null, UsuariosContract.MyDataEntry.toContentValues(myData));

        }catch(Exception ex){
            ex.toString();
        }
        finally{
            return id;
        }
    }

    public List<MyData> getContras(int id ) {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<MyData> contras = null;
        MyData myData = null;
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT*FROM " + TABLE_CONTRASEÑAS + " WHERE id = " + id, null);
        if (cursor == null) {
            return new ArrayList<MyData>();
        }
        if (cursor.getCount() < 1) {
            return new ArrayList<MyData>();
        }
        if (!cursor.moveToFirst()) {
            return new ArrayList<MyData>();
        }
        Log.d(TAG, "" + cursor.getCount());
        contras = new ArrayList<MyData>();
        for (int i = 0; i < cursor.getCount(); i++) {
            myData = new MyData();
            myData.setId_contra(cursor.getInt(0));
            myData.setContra(cursor.getString(1));
            myData.setUsuario(cursor.getString(2));
            myData.setId_usr(cursor.getInt(3));
            contras.add(myData);
            cursor.moveToNext();
        }
        Log.d("Contraseña guardada", contras.toString());
        return contras;
    }

    public boolean AlterContra(String sitio,String contra,int id,int id_contra){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("contra",contra);
        values.put("user_c",sitio);
        try{
            db.execSQL("UPDATE " + TABLE_CONTRASEÑAS + " SET contra = '" + contra + "', user_c = '" +sitio+ "' WHERE id = '" + id + "' AND id_contra = '" +id_contra+ "'");
            correcto = true;
        }catch(Exception ex){
            ex.toString();
            correcto=false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarContacto(int id,String sitio,String contra) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CONTRASEÑAS+ " WHERE id = '" + id + "' AND contra ='" +contra+ "' AND user_c = '" +sitio+ "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public void AlterContraS(String sitio,String contra,int id,int id_contra){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("contra",contra);
        values.put("user_c",sitio);
    }
}

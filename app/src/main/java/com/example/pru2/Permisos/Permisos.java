package com.example.pru2.Permisos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.Serializable;

public class Permisos implements Serializable {

    public boolean tPC = false, tPI= false;
    public static final int PERMISOS_CAMARA = 1, PERMISOS_INTERNET=2;

    public void Perm1 (Context context, Activity activity) {
        int estadoDePermiso = ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            intPerm(context);
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.INTERNET},
                    PERMISOS_INTERNET);
        }
    }
    public void Perm2 (Context context, Activity activity) {
        int estadoDePermiso = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            camPerm(context);
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISOS_CAMARA);
        }
    }
    public void camPerm (Context context) {
        Toast.makeText(context, "Cámara permitida", Toast.LENGTH_SHORT).show();
        tPC = true;
    }

    public void camnoPerm (Context context) {
        Toast.makeText(context, "Cámara no permitida", Toast.LENGTH_SHORT).show();
    }
    public void intPerm (Context context) {
        Toast.makeText(context, "Internet permitido", Toast.LENGTH_SHORT).show();
        tPI = true;
    }

    public void intnoPerm (Context context) {
        Toast.makeText(context, "Internet no permitido", Toast.LENGTH_SHORT).show();
    }
}


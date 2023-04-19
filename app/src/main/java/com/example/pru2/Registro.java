package com.example.pru2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pru2.DB.DbUsuarios;
import com.example.pru2.MyData.MyInfo;
import com.example.pru2.PATD.PATD;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {

    private Button guardarButton, iniciarButton;
    private EditText nombreTxt, usuarioTxt, contraTxt, correoTxt, telefTxt, edadTxt;
    public static String nom, usu,password,email,tel,ed;
    private static final String TAG = "Registrado";
    public static List<MyInfo> list =new ArrayList<MyInfo>();
    public static final String KEY = "+4xij6jQRSBdCymMxweza/uMYo+o0EUg";
    public MyDesUtil myDesUtil= new MyDesUtil().addStringKeyBase64(KEY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        guardarButton = findViewById(R.id.guardarID);
        iniciarButton = findViewById(R.id.IniciarID);
        nombreTxt = findViewById(R.id.nombreID);
        usuarioTxt = findViewById(R.id.usuarioID);
        contraTxt = findViewById(R.id.passwordID);
        correoTxt = findViewById(R.id.emailID);
        telefTxt = findViewById(R.id.numeroID);
        edadTxt = findViewById(R.id.edadID);

        iniciarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciar = new Intent(Registro.this, Login.class);
                startActivity(iniciar);
            }
        });

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyInfo info= new MyInfo();
                nom=String.valueOf(nombreTxt.getText());
                usu= String.valueOf(usuarioTxt.getText());
                password = String.valueOf(contraTxt.getText());
                email= String.valueOf(correoTxt.getText());
                tel = String.valueOf(telefTxt.getText());
                ed = String.valueOf(edadTxt.getText());
                if(usu.equals("") || nom.equals("") || password.equals("")|| email.equals("")) {
                    Toast.makeText(Registro.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }else{
                    if(PATD.validarEmail(email)){
                        PATD.fillInfo(info);
                        DbUsuarios dbUsuarios = new DbUsuarios(Registro.this);
                        long id = dbUsuarios.save(info);
                        if (id>0){
                            Toast.makeText(Registro.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                            limpiar();
                    }else {
                            Toast.makeText(Registro.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                            Toast.makeText(Registro.this, "Error con el correo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void limpiar(){
        nombreTxt.setText("");
        usuarioTxt.setText("");
        contraTxt.setText("");
        correoTxt.setText("");
        telefTxt.setText("");
        edadTxt.setText("");
    }
}
package com.example.pru2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.pru2.DB.DbHelper;

public class Login extends AppCompatActivity {

    private EditText userTxt, passwordTxt;
    private Button buttonIngreso, buttonOlvide, buttonRegistro;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DbHelper(this);

        userTxt = findViewById(R.id.userLog);
        passwordTxt = findViewById(R.id.passwordLog);
        buttonIngreso = findViewById(R.id.loginButton);
        buttonOlvide = findViewById(R.id.olvideButton);
        buttonRegistro = findViewById(R.id.registrarButton);

        buttonIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = userTxt.getText().toString();
                String contrasena = passwordTxt.getText().toString();
                boolean validacionInterfaz = validarCampos(usuario, contrasena);

            }
        });
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(Login.this, Registro.class);
                startActivity(registro);
            }
        });
    }

    public boolean validarCampos(String usuario, String contrasenas){
        if (usuario.isEmpty() || contrasenas.isEmpty()){
            Toast.makeText(this, "Ingrese usuario y contraseña", Toast.LENGTH_LONG).show();
            return false;
        } else if(usuario.length()<2 || contrasenas.length()<2){
            Toast.makeText(this, "Deben ser mayor a dos caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}
package com.example.pru2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import BD.BDUsuarios;

public class Login extends AppCompatActivity {

    private Button buttonIngreso, buttonOlvide, buttonRegistro;
    public static String user,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonIngreso = findViewById(R.id.loginButton);
        buttonRegistro = findViewById(R.id.registrarButton);
        buttonOlvide = findViewById(R.id.olvideButton);
        EditText usuario = findViewById(R.id.usuario);
        EditText pass = findViewById(R.id.password);

        buttonIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = String.valueOf(usuario.getText());
                password = String.valueOf(pass.getText());
                /*
                acceso(user,password); */
            }
        });

        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(Login.this, Registro.class);
                startActivity(reg);
            }
        });

        buttonOlvide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ol = new Intent(Login.this, Olvide.class);
                startActivity(ol);
            }
        });
    }
    /*
    public void acceso(String user , String password){
        if(user.equals("")||password.equals("")){
            Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
        }else{
            BDUsuarios BDUsuarios = new BDUsuarios(Login.this);
            MyInfo myInfo = dbUsuarios.GetUsuario(usr);
            if(myInfo!=null){
                if(myInfo.getContrasena().equals(pswd)){
                    Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, principal.class);
                    intent.putExtra("Objeto", myInfo);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Usuario NOT FOUND", Toast.LENGTH_LONG).show();

            }*/

}
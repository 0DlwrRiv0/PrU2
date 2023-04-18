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
import com.example.pru2.DB.DbUsuarios;
import com.example.pru2.MyData.MyInfo;

import java.util.List;

public class Login extends AppCompatActivity {

    private EditText userTxt, passwordTxt;
    private Button buttonIngreso, buttonOlvide, buttonRegistro;
    public static final String KEY = "+4xij6jQRSBdCymMxweza/uMYo+o0EUg";
    public static List<MyInfo> list;
    public static String json = null;
    public static String usr,pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userTxt = findViewById(R.id.userLog);
        passwordTxt = findViewById(R.id.passwordLog);
        buttonIngreso = findViewById(R.id.loginButton);
        buttonOlvide = findViewById(R.id.olvideButton);
        buttonRegistro = findViewById(R.id.registrarButton);

        buttonIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr=String.valueOf(userTxt.getText());
                pswd=String.valueOf(passwordTxt.getText());
                acceso(usr,pswd);
            }
        });

        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(Login.this, Registro.class);
                startActivity(registro);
            }
        });

        buttonOlvide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent olvide = new Intent(Login.this, Olvide.class);
                startActivity(olvide);
            }
        });
    }

    public void acceso(String usr , String pswd) {
        if (usr.equals("") || pswd.equals("")) {
            Toast.makeText(getApplicationContext(), "Se tienen que llenar todos los campos para acceder", Toast.LENGTH_LONG).show();
        } else {
            DbUsuarios dbUsuarios = new DbUsuarios(Login.this);
            MyInfo myInfo = dbUsuarios.GetUsuario(usr);
            if (myInfo != null) {
                if (myInfo.getContrasena().equals(pswd)) {
                    Toast.makeText(getApplicationContext(), "Iniciando sesión...", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, principal.class);
                    intent.putExtra("Objeto", myInfo);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "El usuario no ha sido encontrado", Toast.LENGTH_LONG).show();
            }
        }
    }
}
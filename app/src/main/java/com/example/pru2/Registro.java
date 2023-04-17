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

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {

    private Button guardarButton, iniciarButton;
    private EditText nombreTxt, usuarioTxt, contraTxt, correoTxt, telefTxt, edadTxt;

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
                String nombre = nombreTxt.getText().toString();
                String usuario = usuarioTxt.getText().toString();
                String contrasena = contraTxt.getText().toString();
                String correo = correoTxt.getText().toString();
                String telefono = telefTxt.getText().toString();
                boolean validacionInterfaz = validarCampos(nombre, usuario, contrasena, correo, telefono);
                if(validacionInterfaz){
                    DbUsuarios dbUsuarios = new DbUsuarios(Registro.this);
                    long id = dbUsuarios.insertarUsuario(nombreTxt.getText().toString(), usuarioTxt.getText().toString(), contraTxt.getText().toString(),
                            correoTxt.getText().toString(), telefTxt.getText().toString());
                    if (id > 0){
                        Toast.makeText(Registro.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                        limpiar();
                    }else{
                        Toast.makeText(Registro.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
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

    public boolean validarCampos(String usuario, String contrasenas, String nombre, String correo, String telefono){
        if (usuario.isEmpty() || contrasenas.isEmpty() || nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()){
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_LONG).show();
            return false;
        } else if(usuario.length()<2 || contrasenas.length()<2){
            Toast.makeText(this, "Deben ser mayor a dos caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

}
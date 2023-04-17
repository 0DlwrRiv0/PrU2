package com.example.pru2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Olvide extends AppCompatActivity {
    public String usr = null;
    public String correo,mensaje;

    private EditText useR, corR ;
    private Button enviaR, inicioR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide);

        enviaR = findViewById(R.id.enviarRec);
        inicioR = findViewById(R.id.iniciarRec);
        useR = findViewById(R.id.usuarioRec);
        corR = findViewById(R.id.emailRec);

        inicioR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Olvide.this, Login.class);
                startActivity(intent);
            }
        });

        enviaR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr = String.valueOf(useR.getText());
                correo = String.valueOf(corR.getText());
            }
        });
    }
}
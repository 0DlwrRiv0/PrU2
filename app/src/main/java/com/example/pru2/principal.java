package com.example.pru2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pru2.DB.DbContraseñas;
import com.example.pru2.MyData.MyData;
import com.example.pru2.MyData.MyInfo;

import java.util.List;

public class principal extends AppCompatActivity {

    private ListView listView;
    private List<MyData> listo;
    public int posicion=0;
    public static MyInfo myInfo= null;
    MyData data = new MyData();
    private Button agregar, borrar, editar;
    private EditText usr, psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Object object = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        agregar = findViewById(R.id.agregarButton);
        borrar = findViewById(R.id.borrarButton);
        editar = findViewById(R.id.borrarButton);
        usr = findViewById(R.id.userP);
        psw = findViewById(R.id.conP);
        Intent con = getIntent();
        if (con != null) {
            if (con.getExtras() != null) {
                object = con.getExtras().get("Objeto");
                if (object != null) {
                    if (object instanceof MyInfo) {
                        myInfo = (MyInfo) object;
                    }
                }
            }
        }
        DbContraseñas dbContraseñas = new DbContraseñas(principal.this);
        listo = dbContraseñas.getContras(myInfo.getId_usr());
        listView = (ListView) findViewById(R.id.ListID);
        MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
        listView.setAdapter(myAdapter);
        button.setEnabled(false);
        button1.setEnabled(false);
        if(listo==null){
            Toast.makeText(getApplicationContext(), "Para agregar una contraseña de clic en el menú o en el botón de agregar", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Escriba en los campos", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), String.valueOf(myInfo.getId_usr()), Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(), "Para modificar o eliminar una contraseña da click sobre ella", Toast.LENGTH_LONG).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    }



    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listview, menu);
        return true;
    }
}
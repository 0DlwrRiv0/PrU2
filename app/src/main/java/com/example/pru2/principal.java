package com.example.pru2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    public int pos=0;
    public static MyInfo myInfo= null;
    EditText editTextUsu,editTextCon;
    Button buttonBorrar,buttonEditar,buttonAgregar;
    MyData data = new MyData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Object object = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras() != null) {
                object = intent.getExtras().get("Objeto");
                if (object != null) {
                    if (object instanceof MyInfo) {
                        myInfo = (MyInfo) object;
                    }
                }
            }
        }
        editTextUsu = findViewById(R.id.userP);
        editTextCon = findViewById(R.id.conP);
        buttonAgregar = findViewById(R.id.agregarButton);
        buttonBorrar = findViewById(R.id.borrarButton);
        buttonEditar = findViewById(R.id.editarButton);

        DbContraseñas dbContraseñas = new DbContraseñas(principal.this);
        listo = dbContraseñas.getContras(myInfo.getId_usr());
        listView = (ListView) findViewById(R.id.ListID);
        MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
        listView.setAdapter(myAdapter);
        buttonBorrar.setEnabled(false);
        buttonEditar.setEnabled(false);
        if(listo==null){
            Toast.makeText(getApplicationContext(), String.valueOf(myInfo.getId_usr()), Toast.LENGTH_LONG).show();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                data = listo.get(i);
                editTextUsu.setText(data.getUsuario());
                editTextCon.setText(data.getContra());
                pos=i;
                buttonBorrar.setEnabled(true);
                buttonEditar.setEnabled(true);
            }
        });

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr= String.valueOf(editTextUsu.getText());
                String contra = String.valueOf(editTextCon.getText());
                if(usr.equals("")||contra.equals("")){
                    Toast.makeText(getApplicationContext(), "Se deben llenar los campos", Toast.LENGTH_LONG).show();
                }else{
                    MyData myData = new MyData();
                    myData.setContra(contra);
                    myData.setUsuario(usr);
                    myData.setId_usr(myInfo.getId_usr());
                    Toast.makeText(getApplicationContext(), String.valueOf(myInfo.getId_usr()), Toast.LENGTH_LONG).show();
                    DbContraseñas dbContraseñas = new DbContraseñas(principal.this);
                    long id = dbContraseñas.saveContraseña(myData);
                    if (id > 0){
                        listo = dbContraseñas.getContras(myInfo.getId_usr());
                        MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
                        listView.setAdapter(myAdapter);
                        editTextUsu.setText("");
                        editTextCon.setText("");
                        Toast.makeText(getApplicationContext(), myData.getUsuario()+" "+myData.getContra(), Toast.LENGTH_LONG).show();
                        Toast.makeText(principal.this, "Se ha guardado con exito",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(principal.this, "No se ha podido guardar",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        buttonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContraseñas dbContras = new DbContraseñas(principal.this);
                boolean id=dbContras.eliminarContacto(myInfo.getId_usr(),data.getUsuario(),data.getContra());
                if(id){
                    listo=dbContras.getContras(myInfo.getId_usr());
                    MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
                    listView.setAdapter(myAdapter);
                    editTextUsu.setText("");
                    editTextCon.setText("");
                    Toast.makeText(getApplicationContext(), "Se eliminó la contraseña", Toast.LENGTH_LONG).show();
                    buttonBorrar.setEnabled(false);
                    buttonEditar.setEnabled(false);
                }else{
                    Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr= String.valueOf(editTextUsu.getText());
                String contra = String.valueOf(editTextCon.getText());
                if(usr.equals("")||contra.equals("")){
                    Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
                }else{
                    DbContraseñas dbContraseñas = new DbContraseñas(principal.this);
                    boolean id = dbContraseñas.AlterContra(usr,contra,myInfo.getId_usr(),data.getId_contra());
                    if(id){
                        listo = dbContraseñas.getContras(myInfo.getId_usr());
                        MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
                        listView.setAdapter(myAdapter);
                        editTextUsu.setText("");
                        editTextCon.setText("");
                        Toast.makeText(getApplicationContext(), "Se ha modificado la contraseña", Toast.LENGTH_LONG).show();
                        buttonBorrar.setEnabled(false);
                        buttonEditar.setEnabled(false);
                    }else{
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean flag = false;
        flag = super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.listview,  menu);
        return flag;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.item1){
            Toast.makeText(getApplicationContext(), "JAJAJ TODOS LOS TUTORIALES ERAN DE POKEMON", Toast.LENGTH_LONG).show();
            Intent intent= new Intent(principal.this, API.class);
            startActivity(intent);
            return true;
        }
        if(id==R.id.item2){
            Intent intent= new Intent(principal.this,Login.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


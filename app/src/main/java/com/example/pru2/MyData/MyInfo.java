package com.example.pru2.MyData;

import java.io.Serializable;

public class MyInfo implements Serializable {

    private String nombre;
    private String user;
    private String contrasena;
    private String correo;
    private String numero;
    private String edad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }


    private int id_usr;

    public int getId_usr() {
        return id_usr;
    }
    public void setId_usr(int id_usr) {
        this.id_usr = id_usr;
    }




}

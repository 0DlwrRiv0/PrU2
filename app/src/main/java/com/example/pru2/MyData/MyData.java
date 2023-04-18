package com.example.pru2.MyData;

import java.io.Serializable;

public class MyData implements Serializable {

    private String contra;
    private int id_contra;
    private String usuario;
    private int id_usr;

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getId_contra() {
        return id_contra;
    }

    public void setId_contra(int id_contra) {
        this.id_contra = id_contra;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId_usr() {
        return id_usr;
    }

    public void setId_usr(int id_usr) {
        this.id_usr = id_usr;
    }
}

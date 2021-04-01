package com.example.tallergrupalandroid;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Dialogo {
    AlertDialog.Builder builder;
    AlertDialog dialog;

    public Dialogo(Context contexto) {
        builder = new AlertDialog.Builder(contexto);
    }

    public void settitle (int title){
        builder.setTitle(title);
    }
    public void setmsg(int msg){
        builder.setMessage(msg);
    }
    public void show(){
        dialog = builder.create();
        dialog.show();
    }
    public void setpositivo(int textoboton, DialogInterface.OnClickListener escucha){
        builder.setPositiveButton(textoboton,escucha);
    }

    public void setnegativo(int textoboton, DialogInterface.OnClickListener escucha){
        builder.setNegativeButton(textoboton,escucha);
    }
}

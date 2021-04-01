package com.example.tallergrupalandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contraseña;
    private CheckBox terminos, recordarDatos;
    private Button ingresar, registrar;
    public static ArrayList<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario=(EditText)findViewById(R.id.txt_usuario);
        contraseña=(EditText)findViewById(R.id.txt_contraseña);
        terminos=(CheckBox)findViewById(R.id.checkTerminos);
        recordarDatos=(CheckBox)findViewById(R.id.checkRecordar);
        ingresar=(Button)findViewById(R.id.btnIngresar);
        ingresar.setEnabled(false);

        usuarios = new ArrayList<Usuario>();
        crearUsuarios();
        // si existe un shared preferences con el usuario
        getUsuario();
        //

        terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(terminos.isChecked() == true) {
                    ingresar.setEnabled(true);
                }else{
                    ingresar.setEnabled(false);
                }
            }
        });
        registrar=(Button)findViewById(R.id.btnRegistrar);
    }

    public void getUsuario(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String usuario = sharedPref.getString("usuario","");
        if(!usuario.isEmpty()){
            Intent i = new Intent(this,PrincipalActivity.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        }
    }

    public void crearUsuarios(){
        usuarios.add(new Usuario("usuario1","123456","example1@gmail.com"));
        usuarios.add(new Usuario("usuario2","123456","example2@gmail.com"));
        usuarios.add(new Usuario("usuario3","123456","example3@gmail.com"));
    }

    public void Ingresar(View view){
        String nombre = usuario.getText().toString();
        String contra = contraseña.getText().toString();
        Usuario user = new Usuario(nombre,contra);

        if(usuarios.contains(user)){
            if(recordarDatos.isChecked()==true){
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("usuario",user.usuario);
                editor.putString("password",user.password);
                editor.commit();
            }
            // irme a la actividad principal
            Intent i = new Intent(this,PrincipalActivity.class);
            i.putExtra("usuario",user.usuario);
            startActivity(i);
            //
        }else{
            Dialogo dialogo = new Dialogo(this);
            dialogo.settitle(R.string.dialogERROR);
            dialogo.setmsg(R.string.loginIncorrecto);
            for(int i = 0; i < usuarios.size(); i ++){
                if(usuarios.get(i).usuario.equalsIgnoreCase(user.usuario)){
                    dialogo.setmsg(R.string.passwordIncorrecto);
                    dialogo.setnegativo(R.string.botonrecuperarcontraseña, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent k = new Intent(getApplicationContext(),RecordarPasswordActivity.class);
                            startActivity(k);
                        }
                    });
                    break;
                }
            }
            dialogo.show();
            terminos.setChecked(false);
            recordarDatos.setChecked(false);
            ingresar.setEnabled(false);
        }

    }

    public void Registrar(View view){
        Intent registro = new Intent(this,RegistroActivity.class);
        startActivity(registro);
    }

    @Override
    protected void onResume() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().remove("usuario").commit();
        super.onResume();
    }
}
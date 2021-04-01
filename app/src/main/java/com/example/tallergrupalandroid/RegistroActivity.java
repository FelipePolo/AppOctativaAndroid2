package com.example.tallergrupalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {

    public int CODIGO_RESPUESTA;
    boolean gooduser = false;
    private EditText usuario, contraseña,repetir,correo;
    private Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario=(EditText)findViewById(R.id.edtUsuario);
        contraseña=(EditText)findViewById(R.id.edtPassword);
        repetir=(EditText)findViewById(R.id.edtRepetirPass);
        correo = (EditText)findViewById(R.id.Edcorreo);
        registrar=(Button)findViewById(R.id.btnRegistro);
    }

    public void Registrar(View view){
        Dialogo dialogo = new Dialogo(this);
        dialogo.settitle(R.string.dialogERROR);
        if(usuario.getText().toString().equals("") || contraseña.getText().toString().equals("") || repetir.getText().toString().equals("") || correo.getText().toString().equals("")){
            dialogo.setmsg(R.string.LlenarCampos);
        }else if(contraseña.getText().length()<6){
            dialogo.setmsg(R.string.passwordcorta);
        }else if(contraseña.getText().toString().equals(repetir.getText().toString()) == false){
            dialogo.setmsg(R.string.passwordnoiguales);
        }else{
            dialogo.settitle(R.string.dialogSUCCESS);
            dialogo.setmsg(R.string.registrocorrecto);
            gooduser = true;
            MainActivity.usuarios.add(new Usuario(usuario.getText().toString(),contraseña.getText().toString(),correo.getText().toString()));
            dialogo.setpositivo(R.string.botonregistropositivo, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
        dialogo.show();
    }

    public void Volver(View view){
        finish();
    }
}
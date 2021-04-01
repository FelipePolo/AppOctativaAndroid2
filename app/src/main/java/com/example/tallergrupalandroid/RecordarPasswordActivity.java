package com.example.tallergrupalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecordarPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout bloque;
    Button enviar, validar, restablecer;
    EditText correo, edCodigo, pa1, pa2;
    int codigo = 0, pos = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordar_password);

        bloque = findViewById(R.id.bloque);
        bloque.setVisibility(View.INVISIBLE);

        enviar = findViewById(R.id.Benviar);
        validar = findViewById(R.id.Bvalidar);
        validar.setEnabled(false);
        restablecer = findViewById(R.id.Brestablecer);

        correo = findViewById(R.id.Edcorreo);
        edCodigo = findViewById(R.id.Edcodigo);
        pa1 = findViewById(R.id.Edpassword1);
        pa2 = findViewById(R.id.Edpassword2);

        edCodigo.setEnabled(false);

        enviar.setOnClickListener(this);
        validar.setOnClickListener(this);
        restablecer.setOnClickListener(this);
    }

    public int existeCorreo(String correo) {
        for (int i = 0; i < MainActivity.usuarios.size(); i++) {
            if (MainActivity.usuarios.get(i).correo.equalsIgnoreCase(correo)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onClick(View v) {
        Dialogo dialogo = new Dialogo(this);
        switch (v.getId()) {
            case R.id.Benviar:
                dialogo.settitle(R.string.dialogERROR);
                if (!correo.getText().toString().isEmpty()) {
                    pos = existeCorreo(correo.getText().toString());
                    if (pos != -1) {
                        codigo = generarCodigo();
                        Toast.makeText(this, "El codigo es:" + String.valueOf(codigo), Toast.LENGTH_LONG).show();
                        dialogo.settitle(R.string.dialogSUCCESS);
                        dialogo.setmsg(R.string.codigoenviado);
                        edCodigo.setEnabled(true);
                        validar.setEnabled(true);
                    } else {
                        dialogo.setmsg(R.string.emailincorrecto);
                    }
                } else {
                    dialogo.setmsg(R.string.emailvacio);
                }
                break;
            case R.id.Bvalidar:
                dialogo.settitle(R.string.dialogERROR);
                if (!edCodigo.getText().toString().isEmpty()) {
                    int auxcodigo = Integer.parseInt(edCodigo.getText().toString());
                    if (auxcodigo == codigo) {
                        dialogo.settitle(R.string.dialogSUCCESS);
                        dialogo.setmsg(R.string.codigocorrecto);
                        bloque.setVisibility(View.VISIBLE);
                        correo.setEnabled(false);
                        enviar.setEnabled(false);
                        edCodigo.setEnabled(false);
                        validar.setEnabled(false);
                    } else {
                        dialogo.setmsg(R.string.codigoincorrecto);
                    }
                } else {
                    dialogo.setmsg(R.string.codigovacio);
                }
                break;
            case R.id.Brestablecer:
                dialogo.settitle(R.string.dialogERROR);
                if (!pa1.getText().toString().isEmpty() && !pa2.getText().toString().isEmpty()) {
                    if (pa1.getText().toString().equalsIgnoreCase(pa2.getText().toString())) {
                        if (pa1.getText().length() >= 6) {
                            Usuario useraux = MainActivity.usuarios.get(pos);
                            useraux.password = pa1.getText().toString();
                            MainActivity.usuarios.set(pos,useraux);
                            dialogo.settitle(R.string.dialogSUCCESS);
                            dialogo.setmsg(R.string.Restablecimientocorrecto);
                            dialogo.setpositivo(R.string.botonregistropositivo, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                        } else {
                            dialogo.setmsg(R.string.passwordcorta);
                        }
                    } else {
                        dialogo.setmsg(R.string.passwordnoiguales);
                    }
                } else {
                    dialogo.setmsg(R.string.LlenarCampos);
                }
                break;
        }
        dialogo.show();
    }

    public int generarCodigo() {
        int num1 = (int) ((Math.random() * 9) + 1);
        int num2 = (int) ((Math.random() * 9) + 1);
        int num3 = (int) ((Math.random() * 9) + 1);
        int num4 = (int) ((Math.random() * 9) + 1);

        return Integer.parseInt(String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3) + String.valueOf(num4));
    }
}
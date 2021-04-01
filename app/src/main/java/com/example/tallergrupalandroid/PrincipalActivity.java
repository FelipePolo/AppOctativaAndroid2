package com.example.tallergrupalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button bfisica,btexto,bgeometria,bayuda,bcerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("APP V1");
        setSupportActionBar(toolbar);

        bfisica = findViewById(R.id.Bfisica);
        btexto = findViewById(R.id.Btexto);
        bgeometria = findViewById(R.id.Bgeometria);
        bayuda = findViewById(R.id.Bayuda);
        bcerrar = findViewById(R.id.Bcerrar);

        bfisica.setOnClickListener(this);
        btexto.setOnClickListener(this);
        bgeometria.setOnClickListener(this);

        bayuda.setOnClickListener(this);
        bcerrar.setOnClickListener(this);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprincipal,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.Bfisica :
                i = new Intent(this, FisicaActivity.class);
                startActivity(i);
                break;
            case R.id.Bgeometria:
                i = new Intent(this, GeometriaActivity.class);
                startActivity(i);
                break;
            case R.id.Btexto:
                i = new Intent(this, TextoActivity.class);
                startActivity(i);
                break;
            case R.id.Bayuda:
                Dialogo dialogo = new Dialogo(this);
                dialogo.settitle(R.string.Ayuda);
                dialogo.setmsg(R.string.nombres);
                dialogo.show();
                break;
            case R.id.Bcerrar:
                finish();
                break;
        }

    }
}
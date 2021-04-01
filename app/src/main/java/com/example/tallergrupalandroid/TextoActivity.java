package com.example.tallergrupalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TextoActivity extends AppCompatActivity implements View.OnKeyListener{

    CheckBox negrita,italica,subrayada;
    EditText texto;
    TextView textor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto);

        negrita = findViewById(R.id.negrita);
        italica = findViewById(R.id.italica);
        subrayada = findViewById(R.id.subrayada);
        texto = findViewById(R.id.texto);
        textor = findViewById(R.id.textor);

        texto.setOnKeyListener(this);

        negrita.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                italica.setChecked(false);
                textor.setTypeface(Typeface.DEFAULT_BOLD);
            }else{
                textor.setTypeface(Typeface.DEFAULT);
            }
        });

        italica.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                negrita.setChecked(false);
                textor.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            }else{
                textor.setTypeface(Typeface.DEFAULT);
            }
        });

        subrayada.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                SpannableString content = new SpannableString(texto.getText().toString());
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

                textor.setText(content);
            }else{
                textor.setText(texto.getText().toString());
            }
        });

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(subrayada.isChecked()){
            SpannableString content = new SpannableString(texto.getText().toString());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            textor.setText(content);
        }else{
            textor.setText(texto.getText().toString());
        }
        return false;
    }
}
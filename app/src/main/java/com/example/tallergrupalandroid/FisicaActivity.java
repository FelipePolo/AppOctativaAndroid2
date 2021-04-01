package com.example.tallergrupalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FisicaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner calculo, recistencias;

    TextView titulo, valor1, valor2, resultado;

    EditText para1, para2, recis1, recis2, recis3;

    CheckBox paralelo;

    LinearLayout bloqueR;

    Button calcular;

    private static void onClick2(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisica);
        String[] opciones = {"Velocidad", "Fuerza", "Voltaje"};
        calculo = findViewById(R.id.spcalculo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        calculo.setAdapter(adapter);
        calculo.setOnItemSelectedListener(this);

        String[] nums = {"2", "3"};
        recistencias = findViewById(R.id.sprecistencias);
        ArrayAdapter<String> adapterRecistencias = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nums);
        recistencias.setAdapter(adapterRecistencias);
        recistencias.setOnItemSelectedListener(this);

        titulo = findViewById(R.id.tvtitulo);
        valor1 = findViewById(R.id.valor1);
        valor2 = findViewById(R.id.valor2);
        resultado = findViewById(R.id.tvresultado);

        para1 = findViewById(R.id.etpara1);
        para2 = findViewById(R.id.etpara2);
        recis1 = findViewById(R.id.recistencia1);
        recis2 = findViewById(R.id.recistencia2);
        recis3 = findViewById(R.id.recistencia3);

        paralelo = findViewById(R.id.cbparalelo);

        bloqueR = findViewById(R.id.bloque_recistencias);

        calcular = findViewById(R.id.Bcalcular);
        calcular.setOnClickListener(this);
    }


    public void onCalculoClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                velocidadInterface();
                break;
            case 1:
                fuerzaInterface();
                break;
            case 2:
                voltajeInterface();
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spcalculo) {
            switch (position) {
                case 0:
                    velocidadInterface();
                    break;
                case 1:
                    fuerzaInterface();
                    break;
                case 2:
                    voltajeInterface();
                    break;

            }
        } else {
            switch (position) {
                case 0:
                    recis3.setEnabled(false);
                    break;
                case 1:
                    recis3.setEnabled(true);
                    break;
            }
        }
    }

    public void velocidadInterface() {
        titulo.setText("Calculo de la velocidad");
        valor1.setText("Digite la distancia(metros) recorrida:");
        valor2.setText("Digite el tiempo(segundos) que demoro:");
        recistencias.setVisibility(View.INVISIBLE);
        recistencias.setEnabled(false);
        bloqueR.setVisibility(View.INVISIBLE);
        paralelo.setVisibility(View.INVISIBLE);
    }

    public void fuerzaInterface() {
        titulo.setText("Calculo de la Fuerza");
        valor1.setText("Digite la masa(kg) del objeto");
        valor2.setText("Digite la magnitud de la gravedad");
        recistencias.setVisibility(View.INVISIBLE);
        recistencias.setEnabled(false);
        bloqueR.setVisibility(View.INVISIBLE);
        paralelo.setVisibility(View.INVISIBLE);
    }

    public void voltajeInterface() {
        titulo.setText("Calculo del voltaje");
        valor1.setText("Digite el amperaje(amperios) del circuito");
        valor2.setText("Digite la cantidad de recistencias");
        recistencias.setVisibility(View.VISIBLE);
        recistencias.setEnabled(true);
        para2.setVisibility(View.INVISIBLE);
        bloqueR.setVisibility(View.VISIBLE);
        paralelo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {
        switch (calculo.getSelectedItemPosition()) {
            case 0:
                if(!para1.getText().toString().isEmpty() && !recis2.getText().toString().isEmpty()){
                    Double distancia = Double.parseDouble(para1.getText().toString());
                    Double tiempo = Double.parseDouble(para2.getText().toString());
                    if (distancia <= 0 || tiempo <= 0) {
                        resultado.setText("ERROR VALOR");
                    } else {
                        Double aux = distancia / tiempo;
                        Double aux2 = Math.round(aux * 100.0) / 100.0;
                        resultado.setText(String.valueOf(aux2) + " m/s");
                    }
                }else{
                    Toast.makeText(this, "PORFAVOR LLENE TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if(!para1.getText().toString().isEmpty() && !recis2.getText().toString().isEmpty()){
                    Double masa = Double.parseDouble(para1.getText().toString());
                    Double gravedad = Double.parseDouble(para2.getText().toString());
                    if (masa <= 0 || gravedad <= 0) {
                        resultado.setText("ERROR VALOR");
                    } else {
                        Double aux = masa * gravedad;
                        Double aux2 = Math.round(aux * 100.0) / 100.0;
                        resultado.setText(String.valueOf(aux2) + "N");
                    }
                }else{
                    Toast.makeText(this, "PORFAVOR LLENE TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(!para1.getText().toString().isEmpty() && !recis1.getText().toString().isEmpty() && !recis2.getText().toString().isEmpty()){
                    Double amperes = Double.parseDouble(para1.getText().toString());
                    Double recistencia1 = Double.parseDouble(recis1.getText().toString());
                    Double recistencia2 = Double.parseDouble(recis2.getText().toString());
                    Double recistencia3 = 0.0;
                    Boolean bandera = true;
                    if (recis3.isEnabled()) {
                        if(!recis3.getText().toString().isEmpty()){
                            recistencia3 = Double.parseDouble(recis3.getText().toString());
                        }else{
                            bandera = false;
                        }
                    }
                    if (paralelo.isChecked()) {
                        Double aux;
                        if (recis3.isEnabled()) {
                            if(bandera){
                                aux = 1 / ( (1/recistencia1) + (1/recistencia2) + (1 / recistencia3) );
                            }else{
                                aux = null;
                            }
                        }else{
                            aux = 1 / ((1/recistencia1) + (1/recistencia2));
                        }
                        if(aux != null){
                            aux = aux * amperes;
                            Double aux2 = Math.round(aux * 100.0) / 100.0;
                            resultado.setText(String.valueOf(aux2) + "V");
                        }else{
                            Toast.makeText(this, "PORFAVOR LLENE TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (recis3.isEnabled()) {
                            if(!recis3.getText().toString().isEmpty()){
                                Double aux = (recistencia1 + recistencia2 + recistencia3) * amperes;
                                Double aux2 = Math.round(aux * 100.0) / 100.0;
                                resultado.setText(String.valueOf(aux2) + "V");
                            }else{
                                Toast.makeText(this, "PORFAVOR LLENE TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Double aux = (recistencia1 + recistencia2) * amperes;
                            Double aux2 = Math.round(aux * 100.0) / 100.0;
                            resultado.setText(String.valueOf(aux2) + "V");
                        }
                    }
                }else{
                    Toast.makeText(this, "PORFAVOR LLENE TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
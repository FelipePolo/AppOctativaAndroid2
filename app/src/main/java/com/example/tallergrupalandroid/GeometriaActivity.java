package com.example.tallergrupalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GeometriaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText x1,y1,x2,y2;
    TextView resultado;
    Button calcular;

    Spinner opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometria);

        x1 = findViewById(R.id.x1);
        x2 = findViewById(R.id.x2);
        y1 = findViewById(R.id.y1);
        y2 = findViewById(R.id.y2);

        resultado = findViewById(R.id.resultado);

        calcular = findViewById(R.id.calcular);

        opciones = findViewById(R.id.opciones);

        String[] valores = {"Cuadrante", "Pendiente", "Distancia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores);
        opciones.setAdapter(adapter);

        calcular.setOnClickListener(this);
    }

    public String cuadrante(int x, int y){
        if(x > 0 && y > 0){
            return "CUADRANTE 1";
        }else if(x < 0 && y > 0) {
            return "CUADRANTE 2";
        }else if(x < 0 && y < 0){
            return "CUADRANTE 3";
        }else if(x > 0 && y < 0){
            return "CUADRANTE 4";
        }else{
            return "NN";
        }
    }


    @Override
    public void onClick(View v) {

        if(!x1.getText().toString().isEmpty() && !x2.getText().toString().isEmpty() && !y1.getText().toString().isEmpty() && !y2.getText().toString().isEmpty()){
            int xa = Integer.parseInt(x1.getText().toString());
            int ya = Integer.parseInt(y1.getText().toString());
            int xb = Integer.parseInt(x2.getText().toString());
            int yb = Integer.parseInt(y2.getText().toString());

            switch (opciones.getSelectedItemPosition()){
                case 0:
                    resultado.setText("Punto 1:" + cuadrante(xa,ya) + "\n" + "punto 2:" + cuadrante(xb,yb));
                    break;
                case 1:
                    int pendiente = (yb - ya) / (xb - xa);
                    resultado.setText("Pendiente es : " + String.valueOf(pendiente));
                    break;
                case 2:
                    resultado.setText("Distancia x: " + String.valueOf(xb - xa) +"\n"+ "Distancia y: " + String.valueOf(yb - ya));
                    break;
            }
        }else{
            Toast.makeText(this, "PORFAVOR LLENE TODOS LOS DATOS", Toast.LENGTH_SHORT).show();
        }
    }
}
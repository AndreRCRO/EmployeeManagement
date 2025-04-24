package com.example.gestionempleados.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gestionempleados.R;
import com.example.gestionempleados.models.DatosEmpleados;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatosEmpleados.inicializarDatos();

        CardView cardListaEmpleados = findViewById(R.id.cardListaEmpleados);
        CardView cardAgregarEmpleado = findViewById(R.id.cardAgregarEmpleado);
        CardView cardBuscarEmpleado = findViewById(R.id.cardBuscarEmpleado);
        CardView cardEstadisticas = findViewById(R.id.cardEstadisticas);
        CardView cardEmpleadosApi = findViewById(R.id.cardEmpleadosApi);

        cardListaEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_lista_empleados.class);
                startActivity(intent);
            }
        });

        cardAgregarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_agregar_empleado.class);
                startActivity(intent);
            }
        });

        cardBuscarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_buscar_empleado.class);
                startActivity(intent);
            }
        });

        cardEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_estadisticas.class);
                startActivity(intent);
            }
        });

       cardEmpleadosApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_lista_empleado_api.class);
                startActivity(intent);
            }
        });


    }
}
package com.example.gestionempleados.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gestionempleados.R;
import com.example.gestionempleados.models.DatosEmpleados;

import java.util.HashMap;

public class activity_estadisticas extends AppCompatActivity {

    private TextView tvEmpleadosTotal;
    private TextView tvGerentesTotal;
    private TextView tvTecnicosTotal;
    private TextView tvTecnicosSeniorTotal;
    private TextView tvTotalEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        tvEmpleadosTotal = findViewById(R.id.tvEmpleadosTotal);
        tvGerentesTotal = findViewById(R.id.tvGerentesTotal);
        tvTecnicosTotal = findViewById(R.id.tvTecnicosTotal);
        tvTecnicosSeniorTotal = findViewById(R.id.tvTecnicosSeniorTotal);
        tvTotalEmpleados = findViewById(R.id.tvTotalEmpleados);

        mostrarEstadisticas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarEstadisticas();
    }

    private void mostrarEstadisticas() {
        HashMap<String, Integer> conteo = DatosEmpleados.obtenerConteoPorTipo();

        tvEmpleadosTotal.setText("Empleados: " + conteo.get("Empleados"));
        tvGerentesTotal.setText("Gerentes: " + conteo.get("Gerentes"));
        tvTecnicosTotal.setText("Técnicos: " + conteo.get("Técnicos"));
        tvTecnicosSeniorTotal.setText("Técnicos Senior: " + conteo.get("Técnicos Senior"));

        int total = conteo.get("Empleados") + conteo.get("Gerentes") + conteo.get("Técnicos") + conteo.get("Técnicos Senior");
        tvTotalEmpleados.setText("Total General: " + total);
    }
}
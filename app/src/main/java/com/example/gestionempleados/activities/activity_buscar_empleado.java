package com.example.gestionempleados.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionempleados.R;
import com.example.gestionempleados.adapters.EmpleadoAdapter;
import com.example.gestionempleados.models.DatosEmpleados;
import com.example.gestionempleados.models.Empleado;

import java.util.ArrayList;

public class activity_buscar_empleado extends AppCompatActivity {

    private EditText etBusqueda;
    private Button btnBuscar;
    private TextView tvResultadosBusqueda;
    private RecyclerView recyclerViewResultados;
    private EmpleadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_empleado);

        etBusqueda = findViewById(R.id.etBusqueda);
        btnBuscar = findViewById(R.id.btnBuscar);
        tvResultadosBusqueda = findViewById(R.id.tvResultadosBusqueda);
        recyclerViewResultados = findViewById(R.id.recyclerViewResultados);

        recyclerViewResultados.setLayoutManager(new LinearLayoutManager(this));

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarEmpleados();
            }
        });
    }

    private void buscarEmpleados() {
        String textoBusqueda = etBusqueda.getText().toString().trim();

        if (textoBusqueda.isEmpty()) {
            Toast.makeText(this, "Ingrese un texto para buscar", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Empleado> resultados = DatosEmpleados.buscarPorNombre(textoBusqueda);

        if (resultados.isEmpty()) {
            tvResultadosBusqueda.setText("No se encontraron resultados para: " + textoBusqueda);
            tvResultadosBusqueda.setVisibility(View.VISIBLE);
            recyclerViewResultados.setVisibility(View.GONE);
        } else {
            tvResultadosBusqueda.setText("Resultados para: " + textoBusqueda + " (" + resultados.size() + ")");
            tvResultadosBusqueda.setVisibility(View.VISIBLE);

            adapter = new EmpleadoAdapter(this, resultados);
            recyclerViewResultados.setAdapter(adapter);
            recyclerViewResultados.setVisibility(View.VISIBLE);

            adapter.setOnItemClickListener(new EmpleadoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Empleado empleado) {
                    Intent intent = new Intent(activity_buscar_empleado.this, activity_detalle_empleado.class);
                    intent.putExtra("EMPLEADO_ID", empleado.id);
                    startActivity(intent);
                }
            });
        }
    }
}
package com.example.gestionempleados.activities;

import android.content.Intent;
import android.os.Bundle;

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

public class activity_lista_empleados extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmpleadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);

        recyclerView = findViewById(R.id.recyclerViewEmpleados);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EmpleadoAdapter(this, DatosEmpleados.listaEmpleados);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new EmpleadoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Empleado empleado) {
                Intent intent = new Intent(activity_lista_empleados.this, activity_detalle_empleado.class);
                intent.putExtra("EMPLEADO_ID", empleado.id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
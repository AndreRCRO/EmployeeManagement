package com.example.gestionempleados.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gestionempleados.R;
import com.example.gestionempleados.models.DatosEmpleados;
import com.example.gestionempleados.models.Empleado;
import com.example.gestionempleados.models.Gerente;
import com.example.gestionempleados.models.Tecnico;
import com.example.gestionempleados.models.TecnicoSenior;

public class activity_agregar_empleado extends AppCompatActivity {

    private Spinner spinnerTipoEmpleado;
    private LinearLayout layoutCamposGerente;
    private LinearLayout layoutCamposTecnico;
    private LinearLayout layoutCamposTecnicoSenior;
    private Button btnGuardar;

    // Campos comunes
    private EditText etNombre;
    private EditText etApellido;
    private EditText etSalarioBase;
    private EditText etFechaContratacion;

    // Campos para Gerente
    private EditText etDepartamento;
    private EditText etBonoAnual;
    private EditText etCantidadSubordinados;

    // Campos para Técnico
    private EditText etEspecialidad;
    private EditText etNivelCertificacion;
    private EditText etHorasExtra;

    // Campos para Técnico Senior
    private EditText etProyectosCompletados;
    private EditText etClientesAtendidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_empleado);

        spinnerTipoEmpleado = findViewById(R.id.spinnerTipoEmpleado);
        layoutCamposGerente = findViewById(R.id.layoutCamposGerente);
        layoutCamposTecnico = findViewById(R.id.layoutCamposTecnico);
        layoutCamposTecnicoSenior = findViewById(R.id.layoutCamposTecnicoSenior);
        btnGuardar = findViewById(R.id.btnGuardar);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etSalarioBase = findViewById(R.id.etSalarioBase);
        etFechaContratacion = findViewById(R.id.etFechaContratacion);

        etDepartamento = findViewById(R.id.etDepartamento);
        etBonoAnual = findViewById(R.id.etBonoAnual);
        etCantidadSubordinados = findViewById(R.id.etCantidadSubordinados);

        etEspecialidad = findViewById(R.id.etEspecialidad);
        etNivelCertificacion = findViewById(R.id.etNivelCertificacion);
        etHorasExtra = findViewById(R.id.etHorasExtra);

        etProyectosCompletados = findViewById(R.id.etProyectosCompletados);
        etClientesAtendidos = findViewById(R.id.etClientesAtendidos);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_empleado, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoEmpleado.setAdapter(adapter);

        spinnerTipoEmpleado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Empleado
                        layoutCamposGerente.setVisibility(View.GONE);
                        layoutCamposTecnico.setVisibility(View.GONE);
                        layoutCamposTecnicoSenior.setVisibility(View.GONE);
                        break;
                    case 1: // Gerente
                        layoutCamposGerente.setVisibility(View.VISIBLE);
                        layoutCamposTecnico.setVisibility(View.GONE);
                        layoutCamposTecnicoSenior.setVisibility(View.GONE);
                        break;
                    case 2: // Técnico
                        layoutCamposGerente.setVisibility(View.GONE);
                        layoutCamposTecnico.setVisibility(View.VISIBLE);
                        layoutCamposTecnicoSenior.setVisibility(View.GONE);
                        break;
                    case 3: // Técnico Senior
                        layoutCamposGerente.setVisibility(View.GONE);
                        layoutCamposTecnico.setVisibility(View.VISIBLE);
                        layoutCamposTecnicoSenior.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarEmpleado();
            }
        });
    }

    private void guardarEmpleado() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String salarioBaseStr = etSalarioBase.getText().toString().trim();
        String fechaContratacion = etFechaContratacion.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || salarioBaseStr.isEmpty() || fechaContratacion.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        double salarioBase = Double.parseDouble(salarioBaseStr);
        int id = DatosEmpleados.listaEmpleados.size() + 1;

        int tipoSeleccionado = spinnerTipoEmpleado.getSelectedItemPosition();

        switch (tipoSeleccionado) {
            case 0:
                Empleado empleado = new Empleado(id, nombre, apellido, salarioBase, fechaContratacion);
                DatosEmpleados.agregarEmpleado(empleado);
                break;

            case 1:
                String departamento = etDepartamento.getText().toString().trim();
                String bonoAnualStr = etBonoAnual.getText().toString().trim();
                String cantidadSubordinadosStr = etCantidadSubordinados.getText().toString().trim();

                if (departamento.isEmpty() || bonoAnualStr.isEmpty() || cantidadSubordinadosStr.isEmpty()) {
                    Toast.makeText(this, "Complete todos los campos del Gerente", Toast.LENGTH_SHORT).show();
                    return;
                }

                double bonoAnual = Double.parseDouble(bonoAnualStr);
                int cantidadSubordinados = Integer.parseInt(cantidadSubordinadosStr);

                Gerente gerente = new Gerente(id, nombre, apellido, salarioBase, fechaContratacion,
                        departamento, bonoAnual, cantidadSubordinados);
                DatosEmpleados.agregarEmpleado(gerente);
                break;

            case 2:
                String especialidad = etEspecialidad.getText().toString().trim();
                String nivelCertificacion = etNivelCertificacion.getText().toString().trim();
                String horasExtraStr = etHorasExtra.getText().toString().trim();

                if (especialidad.isEmpty() || nivelCertificacion.isEmpty() || horasExtraStr.isEmpty()) {
                    Toast.makeText(this, "Complete todos los campos del Técnico", Toast.LENGTH_SHORT).show();
                    return;
                }

                int horasExtra = Integer.parseInt(horasExtraStr);

                Tecnico tecnico = new Tecnico(id, nombre, apellido, salarioBase, fechaContratacion,
                        especialidad, nivelCertificacion, horasExtra);
                DatosEmpleados.agregarEmpleado(tecnico);
                break;

            case 3:
                String especialidadSenior = etEspecialidad.getText().toString().trim();
                String nivelCertificacionSenior = etNivelCertificacion.getText().toString().trim();
                String horasExtraSeniorStr = etHorasExtra.getText().toString().trim();
                String proyectosCompletadosStr = etProyectosCompletados.getText().toString().trim();
                String clientesAtendidosStr = etClientesAtendidos.getText().toString().trim();

                if (especialidadSenior.isEmpty() || nivelCertificacionSenior.isEmpty() ||
                        horasExtraSeniorStr.isEmpty() || proyectosCompletadosStr.isEmpty() || clientesAtendidosStr.isEmpty()) {
                    Toast.makeText(this, "Complete todos los campos del Técnico Senior", Toast.LENGTH_SHORT).show();
                    return;
                }

                int horasExtraSenior = Integer.parseInt(horasExtraSeniorStr);
                int proyectosCompletados = Integer.parseInt(proyectosCompletadosStr);
                int clientesAtendidos = Integer.parseInt(clientesAtendidosStr);

                TecnicoSenior tecnicoSenior = new TecnicoSenior(id, nombre, apellido, salarioBase, fechaContratacion,
                        especialidadSenior, nivelCertificacionSenior, horasExtraSenior,
                        proyectosCompletados, clientesAtendidos);
                DatosEmpleados.agregarEmpleado(tecnicoSenior);
                break;
        }

        Toast.makeText(this, "Empleado guardado con éxito", Toast.LENGTH_SHORT).show();
        finish();
    }
}
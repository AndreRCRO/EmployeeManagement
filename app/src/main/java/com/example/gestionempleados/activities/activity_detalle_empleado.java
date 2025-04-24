package com.example.gestionempleados.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

public class activity_detalle_empleado extends AppCompatActivity {

    private TextView tvTituloDetalle;
    private TableLayout tableLayoutDetalles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);

        tvTituloDetalle = findViewById(R.id.tvTituloDetalle);
        tableLayoutDetalles = findViewById(R.id.tableLayoutDetalles);

        int empleadoId = getIntent().getIntExtra("EMPLEADO_ID", -1);

        if (empleadoId != -1) {
            Empleado empleadoEncontrado = null;
            for (Empleado emp : DatosEmpleados.listaEmpleados) {
                if (emp.id == empleadoId) {
                    empleadoEncontrado = emp;
                    break;
                }
            }

            if (empleadoEncontrado != null) {
                mostrarDetallesEmpleado(empleadoEncontrado);
            } else {
                mostrarError();
            }
        } else {
            mostrarError();
        }
    }

    private void mostrarDetallesEmpleado(Empleado empleado) {
        // Configurar el título según el tipo de empleado
        if (empleado instanceof Gerente) {
            tvTituloDetalle.setText("Detalles del Gerente");
        } else if (empleado instanceof TecnicoSenior) {
            tvTituloDetalle.setText("Detalles del Técnico Senior");
        } else if (empleado instanceof Tecnico) {
            tvTituloDetalle.setText("Detalles del Técnico");
        } else {
            tvTituloDetalle.setText("Detalles del Empleado");
        }

        // Añadir los campos comunes
        agregarCampo("ID", String.valueOf(empleado.id));
        agregarCampo("Nombre", empleado.nombre);
        agregarCampo("Apellido", empleado.apellido);
        agregarCampo("Salario Base", "$" + empleado.salarioBase);
        agregarCampo("Fecha Contratación", empleado.fechaContratacion);

        // Añadir campos específicos
        if (empleado instanceof Gerente) {
            Gerente gerente = (Gerente) empleado;
            agregarCampo("Departamento", gerente.departamento);
            agregarCampo("Bono Anual", "$" + gerente.bonoAnual);
            agregarCampo("Cantidad Subordinados", String.valueOf(gerente.cantidadSubordinados));
        } else if (empleado instanceof TecnicoSenior) {
            TecnicoSenior tecnicoSenior = (TecnicoSenior) empleado;
            agregarCampo("Especialidad", tecnicoSenior.especialidad);
            agregarCampo("Nivel Certificación", tecnicoSenior.nivelCertificacion);
            agregarCampo("Horas Extra", String.valueOf(tecnicoSenior.horasExtra));
            agregarCampo("Proyectos Completados", String.valueOf(tecnicoSenior.proyectosCompletados));
            agregarCampo("Clientes Atendidos", String.valueOf(tecnicoSenior.clientesAtendidos));
        } else if (empleado instanceof Tecnico) {
            Tecnico tecnico = (Tecnico) empleado;
            agregarCampo("Especialidad", tecnico.especialidad);
            agregarCampo("Nivel Certificación", tecnico.nivelCertificacion);
            agregarCampo("Horas Extra", String.valueOf(tecnico.horasExtra));
        }
    }

    private void agregarCampo(String etiqueta, String valor) {
        TableRow row = new TableRow(this);

        TextView tvEtiqueta = new TextView(this);
        tvEtiqueta.setText(etiqueta);
        tvEtiqueta.setPadding(5, 10, 10, 10);
        tvEtiqueta.setTextSize(16);
        tvEtiqueta.setTypeface(null, Typeface.BOLD);

        TextView tvValor = new TextView(this);
        tvValor.setText(valor);
        tvValor.setPadding(5, 10, 5, 10);
        tvValor.setTextSize(16);

        row.addView(tvEtiqueta);
        row.addView(tvValor);

        tableLayoutDetalles.addView(row);
    }

    private void mostrarError() {
        tvTituloDetalle.setText("Error");

        TableRow row = new TableRow(this);
        TextView tvError = new TextView(this);
        tvError.setText("No se encontró el empleado solicitado");
        tvError.setGravity(Gravity.CENTER);
        tvError.setTextSize(18);
        row.addView(tvError);

        tableLayoutDetalles.addView(row);
    }
}
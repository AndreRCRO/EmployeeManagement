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

        // Obtener datos del Intent
        int empleadoId = getIntent().getIntExtra("EMPLEADO_ID", -1);
        String nombre = getIntent().getStringExtra("NOMBRE");
        String apellido = getIntent().getStringExtra("APELLIDO");
        double salarioBase = getIntent().getDoubleExtra("SALARIO_BASE", 0);
        String fechaContratacion = getIntent().getStringExtra("FECHA_CONTRATACION");
        String tipo = getIntent().getStringExtra("TIPO");

        if (empleadoId != -1 && nombre != null) {
            // Mostrar los datos del empleado
            agregarCampo("ID", String.valueOf(empleadoId));
            agregarCampo("Nombre", nombre);
            agregarCampo("Apellido", apellido);
            agregarCampo("Salario Base", "$" + salarioBase);
            agregarCampo("Fecha Contratación", fechaContratacion);

            // Mostrar campos específicos según el tipo de empleado
            if ("Gerente".equals(tipo)) {
                String departamento = getIntent().getStringExtra("DEPARTAMENTO");
                double bonoAnual = getIntent().getDoubleExtra("BONO_ANUAL", 0);
                int cantidadSubordinados = getIntent().getIntExtra("CANTIDAD_SUBORDINADOS", 0);

                agregarCampo("Departamento", departamento);
                agregarCampo("Bono Anual", "$" + bonoAnual);
                agregarCampo("Cantidad Subordinados", String.valueOf(cantidadSubordinados));
            } else if ("TecnicoSenior".equals(tipo)) {
                String especialidad = getIntent().getStringExtra("ESPECIALIDAD");
                String nivelCertificacion = getIntent().getStringExtra("NIVEL_CERTIFICACION");
                int horasExtra = getIntent().getIntExtra("HORAS_EXTRA", 0);
                int proyectosCompletados = getIntent().getIntExtra("PROYECTOS_COMPLETADOS", 0);
                int clientesAtendidos = getIntent().getIntExtra("CLIENTES_ATENDIDOS", 0);

                agregarCampo("Especialidad", especialidad);
                agregarCampo("Nivel Certificación", nivelCertificacion);
                agregarCampo("Horas Extra", String.valueOf(horasExtra));
                agregarCampo("Proyectos Completados", String.valueOf(proyectosCompletados));
                agregarCampo("Clientes Atendidos", String.valueOf(clientesAtendidos));
            } else if ("Tecnico".equals(tipo)) {
                String especialidad = getIntent().getStringExtra("ESPECIALIDAD");
                String nivelCertificacion = getIntent().getStringExtra("NIVEL_CERTIFICACION");
                int horasExtra = getIntent().getIntExtra("HORAS_EXTRA", 0);

                agregarCampo("Especialidad", especialidad);
                agregarCampo("Nivel Certificación", nivelCertificacion);
                agregarCampo("Horas Extra", String.valueOf(horasExtra));
            }
        } else {
            mostrarError();
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
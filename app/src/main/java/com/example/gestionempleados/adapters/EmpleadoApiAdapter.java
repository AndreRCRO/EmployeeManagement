package com.example.gestionempleados.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionempleados.R;
import com.example.gestionempleados.activities.activity_detalle_empleado;
import com.example.gestionempleados.models.Empleado;
import com.example.gestionempleados.models.Gerente;
import com.example.gestionempleados.models.Tecnico;
import com.example.gestionempleados.models.TecnicoSenior;

import java.util.List;

public class EmpleadoApiAdapter extends RecyclerView.Adapter<EmpleadoApiAdapter.ViewHolder> {

    private Context context;
    private List<Empleado> empleados;

    public EmpleadoApiAdapter(Context context, List<Empleado> empleados) {
        this.context = context;
        this.empleados = empleados;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_empleado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Empleado empleado = empleados.get(position);
        holder.tvNombreEmpleado.setText(empleado.nombre + " " + empleado.apellido);
        holder.tvFechaContratacion.setText("Contratado: " + empleado.fechaContratacion);

        if (empleado instanceof TecnicoSenior) {
            TecnicoSenior tecnicoSenior = (TecnicoSenior) empleado;
            holder.tvTipoEmpleado.setText("Técnico Senior - " + tecnicoSenior.especialidad);
            holder.ivTipoEmpleado.setImageResource(R.drawable.ic_person);
            holder.layoutFondo.setBackgroundColor(Color.parseColor("#FFF9C4"));
        } else if (empleado instanceof Tecnico) {
            Tecnico tecnico = (Tecnico) empleado;
            holder.tvTipoEmpleado.setText("Técnico - " + tecnico.especialidad);
            holder.ivTipoEmpleado.setImageResource(R.drawable.ic_person);
            holder.layoutFondo.setBackgroundColor(Color.parseColor("#E3F2FD"));
        } else if (empleado instanceof Gerente) {
            Gerente gerente = (Gerente) empleado;
            holder.tvTipoEmpleado.setText("Gerente - " + gerente.departamento);
            holder.ivTipoEmpleado.setImageResource(R.drawable.ic_person);
            holder.layoutFondo.setBackgroundColor(Color.parseColor("#E8F5E9"));
        } else {
            holder.tvTipoEmpleado.setText("Empleado");
            holder.ivTipoEmpleado.setImageResource(R.drawable.ic_person);
            holder.layoutFondo.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, activity_detalle_empleado.class);
                intent.putExtra("EMPLEADO_ID", empleado.id);
                intent.putExtra("NOMBRE", empleado.nombre);
                intent.putExtra("APELLIDO", empleado.apellido);
                intent.putExtra("SALARIO_BASE", empleado.salarioBase);
                intent.putExtra("FECHA_CONTRATACION", empleado.fechaContratacion);

                if (empleado instanceof Gerente) {
                    Gerente gerente = (Gerente) empleado;
                    intent.putExtra("TIPO", "Gerente");
                    intent.putExtra("DEPARTAMENTO", gerente.departamento);
                    intent.putExtra("BONO_ANUAL", gerente.bonoAnual);
                    intent.putExtra("CANTIDAD_SUBORDINADOS", gerente.cantidadSubordinados);
                } else if (empleado instanceof TecnicoSenior) {
                    TecnicoSenior tecnicoSenior = (TecnicoSenior) empleado;
                    intent.putExtra("TIPO", "TecnicoSenior");
                    intent.putExtra("ESPECIALIDAD", tecnicoSenior.especialidad);
                    intent.putExtra("NIVEL_CERTIFICACION", tecnicoSenior.nivelCertificacion);
                    intent.putExtra("HORAS_EXTRA", tecnicoSenior.horasExtra);
                    intent.putExtra("PROYECTOS_COMPLETADOS", tecnicoSenior.proyectosCompletados);
                    intent.putExtra("CLIENTES_ATENDIDOS", tecnicoSenior.clientesAtendidos);
                } else if (empleado instanceof Tecnico) {
                    Tecnico tecnico = (Tecnico) empleado;
                    intent.putExtra("TIPO", "Tecnico");
                    intent.putExtra("ESPECIALIDAD", tecnico.especialidad);
                    intent.putExtra("NIVEL_CERTIFICACION", tecnico.nivelCertificacion);
                    intent.putExtra("HORAS_EXTRA", tecnico.horasExtra);
                }

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return empleados.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombreEmpleado;
        public TextView tvTipoEmpleado;
        public TextView tvFechaContratacion;
        public ImageView ivTipoEmpleado;
        public LinearLayout layoutFondo;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombreEmpleado = itemView.findViewById(R.id.tvNombreEmpleado);
            tvTipoEmpleado = itemView.findViewById(R.id.tvTipoEmpleado);
            tvFechaContratacion = itemView.findViewById(R.id.tvFechaContratacion);
            ivTipoEmpleado = itemView.findViewById(R.id.ivTipoEmpleado);
            layoutFondo = itemView.findViewById(R.id.layoutFondo);
        }
    }
}

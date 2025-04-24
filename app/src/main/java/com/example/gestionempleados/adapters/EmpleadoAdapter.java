package com.example.gestionempleados.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionempleados.R;
import com.example.gestionempleados.models.Empleado;
import com.example.gestionempleados.models.Gerente;
import com.example.gestionempleados.models.Tecnico;
import com.example.gestionempleados.models.TecnicoSenior;

import java.util.List;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.ViewHolder> {

    private List<Empleado> empleados;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Empleado empleado);
    }

    public EmpleadoAdapter(Context context, List<Empleado> empleados) {
        this.context = context;
        this.empleados = empleados;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_empleado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
                if (listener != null) {
                    listener.onItemClick(empleado);
                }
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreEmpleado = itemView.findViewById(R.id.tvNombreEmpleado);
            tvTipoEmpleado = itemView.findViewById(R.id.tvTipoEmpleado);
            tvFechaContratacion = itemView.findViewById(R.id.tvFechaContratacion);
            ivTipoEmpleado = itemView.findViewById(R.id.ivTipoEmpleado);
            layoutFondo = itemView.findViewById(R.id.layoutFondo);
        }
    }
}

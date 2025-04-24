package com.example.gestionempleados.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionempleados.R;
import com.example.gestionempleados.adapters.EmpleadoApiAdapter;
import com.example.gestionempleados.models.EmpleadosApiResponse;
import com.example.gestionempleados.models.Empleado;
import com.example.gestionempleados.models.EmpleadoApi;
import com.example.gestionempleados.models.EmpleadoApiService;
import com.example.gestionempleados.models.Gerente;
import com.example.gestionempleados.models.Tecnico;
import com.example.gestionempleados.models.TecnicoSenior;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_lista_empleado_api extends AppCompatActivity {

    private RecyclerView rvEmpleadosApi;
    private ProgressBar progressBar;
    private EmpleadoApiAdapter adapter;
    private List<Empleado> empleados = new ArrayList<>();
    private final String baseUrl = "https://d6ab734e-086e-4513-9276-d6af8d21a59c.mock.pstmn.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleado_api);

        rvEmpleadosApi = findViewById(R.id.rvEmpleadosApi);
        progressBar = findViewById(R.id.progressBar);

        rvEmpleadosApi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmpleadoApiAdapter(this, empleados);
        rvEmpleadosApi.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmpleadoApiService empleadoApiService = retrofit.create(EmpleadoApiService.class);

        Call<EmpleadosApiResponse> call = empleadoApiService.getEmpleados();

        call.enqueue(new Callback<EmpleadosApiResponse>() {
            @Override
            public void onResponse(Call<EmpleadosApiResponse> call, Response<EmpleadosApiResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    EmpleadosApiResponse empleadosApiResponse = response.body();
                    if (empleadosApiResponse != null && empleadosApiResponse.empleados != null) {
                        for (EmpleadoApi empleadoApi : empleadosApiResponse.empleados) {
                            Empleado empleado = null;

                            switch (empleadoApi.tipo) {
                                case "TecnicoSenior":
                                    empleado = new TecnicoSenior(
                                            empleadoApi.id,
                                            empleadoApi.nombre,
                                            empleadoApi.apellido,
                                            empleadoApi.salarioBase,
                                            empleadoApi.fechaContratacion,
                                            empleadoApi.especialidad,
                                            empleadoApi.nivelCertificacion,
                                            empleadoApi.horasExtra,
                                            empleadoApi.proyectosCompletados,
                                            empleadoApi.clientesAtendidos
                                    );
                                    break;
                                case "Tecnico":
                                    empleado = new Tecnico(
                                            empleadoApi.id,
                                            empleadoApi.nombre,
                                            empleadoApi.apellido,
                                            empleadoApi.salarioBase,
                                            empleadoApi.fechaContratacion,
                                            empleadoApi.especialidad,
                                            empleadoApi.nivelCertificacion,
                                            empleadoApi.horasExtra
                                    );
                                    break;
                                case "Gerente":
                                    empleado = new Gerente(
                                            empleadoApi.id,
                                            empleadoApi.nombre,
                                            empleadoApi.apellido,
                                            empleadoApi.salarioBase,
                                            empleadoApi.fechaContratacion,
                                            empleadoApi.departamento,
                                            empleadoApi.bonoAnual,
                                            empleadoApi.cantidadSubordinados
                                    );
                                    break;
                            }

                            if (empleado != null) {
                                empleados.add(empleado);
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(activity_lista_empleado_api.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EmpleadosApiResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(activity_lista_empleado_api.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ListaEmpleadoApiActivity", "Error en la llamada a la API", t);
            }
        });
    }
}
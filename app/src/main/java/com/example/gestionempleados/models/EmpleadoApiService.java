package com.example.gestionempleados.models;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmpleadoApiService {
    @GET("empleados.json")
    Call<EmpleadosApiResponse> getEmpleados();
}

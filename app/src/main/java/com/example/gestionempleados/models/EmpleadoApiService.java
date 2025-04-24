package com.example.gestionempleados.models;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmpleadoApiService {
    @GET("https://d6ab734e-086e-4513-9276-d6af8d21a59c.mock.pstmn.io/api/empleados")
    Call<EmpleadosApiResponse> getEmpleados();
}

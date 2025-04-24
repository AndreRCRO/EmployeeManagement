package com.example.gestionempleados.models;

import java.util.List;

public class EmpleadosApiResponse {
    public int page;
    public int total;
    public String status;
    public List<EmpleadoApi> empleados;

    // Constructor vacío (necesario para Gson)
    public EmpleadosApiResponse() {
    }

    // Constructor con parámetros
    public EmpleadosApiResponse(int page, int total, String status, List<EmpleadoApi> empleados) {
        this.page = page;
        this.total = total;
        this.status = status;
        this.empleados = empleados;
    }
}

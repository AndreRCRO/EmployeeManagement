package com.example.gestionempleados.models;

public class TecnicoSenior extends Tecnico {
    public int proyectosCompletados;
    public int clientesAtendidos;

    public TecnicoSenior(int id, String nombre, String apellido, double salarioBase, String fechaContratacion,
                         String especialidad, String nivelCertificacion, int horasExtra,
                         int proyectosCompletados, int clientesAtendidos) {
        super(id, nombre, apellido, salarioBase, fechaContratacion, especialidad, nivelCertificacion, horasExtra);
        this.proyectosCompletados = proyectosCompletados;
        this.clientesAtendidos = clientesAtendidos;
    }
}

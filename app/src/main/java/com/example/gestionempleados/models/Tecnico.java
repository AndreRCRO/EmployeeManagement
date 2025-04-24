package com.example.gestionempleados.models;

public class Tecnico extends Empleado {
    public String especialidad;
    public String nivelCertificacion;
    public int horasExtra;

    public Tecnico(int id, String nombre, String apellido, double salarioBase, String fechaContratacion,
                   String especialidad, String nivelCertificacion, int horasExtra) {
        super(id, nombre, apellido, salarioBase, fechaContratacion);
        this.especialidad = especialidad;
        this.nivelCertificacion = nivelCertificacion;
        this.horasExtra = horasExtra;
    }
}

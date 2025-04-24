package com.example.gestionempleados.models;

public class EmpleadoApi {
    public int id;
    public String nombre;
    public String apellido;
    public double salarioBase;
    public String fechaContratacion;
    public String tipo;
    public String departamento;
    public Double bonoAnual;
    public Integer cantidadSubordinados;
    public String especialidad;
    public String nivelCertificacion;
    public Integer horasExtra;
    public Integer proyectosCompletados;
    public Integer clientesAtendidos;

    public EmpleadoApi() {
    }

    public EmpleadoApi(int id, String nombre, String apellido, double salarioBase, String fechaContratacion, String tipo, String departamento, Double bonoAnual, Integer cantidadSubordinados, String especialidad, String nivelCertificacion, Integer horasExtra, Integer proyectosCompletados, Integer clientesAtendidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salarioBase = salarioBase;
        this.fechaContratacion = fechaContratacion;
        this.tipo = tipo;
        this.departamento = departamento;
        this.bonoAnual = bonoAnual;
        this.cantidadSubordinados = cantidadSubordinados;
        this.especialidad = especialidad;
        this.nivelCertificacion = nivelCertificacion;
        this.horasExtra = horasExtra;
        this.proyectosCompletados = proyectosCompletados;
        this.clientesAtendidos = clientesAtendidos;
    }
}

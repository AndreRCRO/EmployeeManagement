package com.example.gestionempleados.models;

public class Empleado {
    public int id;
    public String nombre;
    public String apellido;
    public double salarioBase;
    public String fechaContratacion;

    public Empleado(int id, String nombre, String apellido, double salarioBase, String fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salarioBase = salarioBase;
        this.fechaContratacion = fechaContratacion;
    }

}

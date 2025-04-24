package com.example.gestionempleados.models;

public class Gerente extends Empleado{
    public String departamento;
    public double bonoAnual;
    public int cantidadSubordinados;

    public Gerente(int id, String nombre, String apellido, double salarioBase, String fechaContratacion, String departamento, double bonoAnual, int cantidadSubordinados) {
        super(id, nombre, apellido, salarioBase, fechaContratacion);
        this.departamento = departamento;
        this.bonoAnual = bonoAnual;
        this.cantidadSubordinados = cantidadSubordinados;
    }
}

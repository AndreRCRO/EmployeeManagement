package com.example.gestionempleados.models;

import java.util.ArrayList;
import java.util.HashMap;

public class DatosEmpleados {
    public static ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    public static void inicializarDatos() {
        if (listaEmpleados.isEmpty()) {
            listaEmpleados.add(new Empleado(
                    1,
                    "Ana",
                    "García",
                    25000.0,
                    "15/03/2022"
            ));

            listaEmpleados.add(new Gerente(
                    2,
                    "Carlos",
                    "López",
                    45000.0,
                    "10/01/2018",
                    "Recursos Humanos",
                    10000.0,
                    12
            ));

            listaEmpleados.add(new Tecnico(
                    3,
                    "Laura",
                    "Martínez",
                    32000.0,
                    "22/06/2020",
                    "Desarrollo Web",
                    "Intermedio",
                    45
            ));

            listaEmpleados.add(new TecnicoSenior(
                    4,
                    "Miguel",
                    "Fernández",
                    38000.0,
                    "03/12/2015",
                    "Desarrollo Móvil",
                    "Avanzado",
                    60,
                    15,
                    8
            ));

            listaEmpleados.add(new Gerente(
                    5,
                    "Elena",
                    "Sánchez",
                    50000.0,
                    "05/09/2010",
                    "Desarrollo",
                    15000.0,
                    20
            ));
        }
    }

    public static void agregarEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
    }

    public static ArrayList<Empleado> buscarPorNombre(String nombre) {
        ArrayList<Empleado> resultados = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.nombre.toLowerCase().contains(nombre.toLowerCase()) ||
                    empleado.apellido.toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(empleado);
            }
        }
        return resultados;
    }

    public static HashMap<String, Integer> obtenerConteoPorTipo() {
        HashMap<String, Integer> conteo = new HashMap<>();
        conteo.put("Empleados", 0);
        conteo.put("Gerentes", 0);
        conteo.put("Técnicos", 0);
        conteo.put("Técnicos Senior", 0);

        for (Empleado empleado : listaEmpleados) {
            if (empleado instanceof TecnicoSenior) {
                conteo.put("Técnicos Senior", conteo.get("Técnicos Senior") + 1);
            } else if (empleado instanceof Tecnico) {
                conteo.put("Técnicos", conteo.get("Técnicos") + 1);
            } else if (empleado instanceof Gerente) {
                conteo.put("Gerentes", conteo.get("Gerentes") + 1);
            } else {
                conteo.put("Empleados", conteo.get("Empleados") + 1);
            }
        }

        return conteo;
    }
}

package com.mycompany.app;

/**
 * Representa una fila de la tabla "cliente".
 * Ajusta los campos si tu tabla tiene más columnas.
 */
public class Cliente {

    private final int id;
    private final String nombre;

    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}

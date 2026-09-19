package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a datos para la tabla "cliente".
 * Basado en tu ConsultarClient.java original, pero como método reutilizable
 * en vez de un main(), para poder invocarlo desde un Servlet/JSP.
 */
public class ClienteDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/web";
    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "postgres";

    static {
        // Registro explícito del driver: evita "No suitable driver found"
        // cuando el classloading de Tomcat no dispara el registro automático vía SPI.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                "No se encontró el driver de PostgreSQL en el classpath: " + e.getMessage());
        }
    }

    public static List<Cliente> obtenerClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cliente")) {

            while (rs.next()) {
                // Cambia "id" y "nombre" por las columnas reales de tu tabla
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                clientes.add(new Cliente(id, nombre));
            }
        }

        return clientes;
    }
}

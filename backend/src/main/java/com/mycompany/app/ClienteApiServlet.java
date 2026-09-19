package com.mycompany.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Endpoint JSON pensado para ser consumido desde JavaScript (fetch)
 * en un frontend estático (index.html), en vez de un JSP renderizado en servidor.
 */
@WebServlet("/api/clientes")
public class ClienteApiServlet extends HttpServlet {

    private static final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        // CORS: el index.html se sirve desde Apache (otro origen/puerto),
        // así que el navegador necesita este permiso explícito para el fetch().
        response.setHeader("Access-Control-Allow-Origin", "*");

        try {
            List<Cliente> clientes = ClienteDAO.obtenerClientes();
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(clientes));
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.print("{\"error\": \"" + e.getMessage().replace("\"", "'") + "\"}");
            }
        }
    }
}

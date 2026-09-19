<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.mycompany.app.Cliente" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de clientes</title>
</head>
<body>
    <h1>Clientes</h1>
    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
        </tr>
        <%
            List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
            for (Cliente c : clientes) {
        %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getNombre() %></td>
        </tr>
        <%
            }
        %>
    </table>
    <p><a href="index.jsp">Volver</a></p>
</body>
</html>

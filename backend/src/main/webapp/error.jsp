<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
    <h1>Error al consultar la base de datos</h1>
    <p><%= request.getAttribute("error") %></p>
    <p><a href="index.jsp">Volver</a></p>
</body>
</html>

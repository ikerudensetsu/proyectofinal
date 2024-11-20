<%@page import="Model.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    HttpSession sesion = request.getSession(true);
    ArrayList<Producto> productos = sesion.getAttribute("productos") == null ? null : (ArrayList) sesion.getAttribute("productos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
</head>
<body class="bg-gray-100 text-gray-800">
    <div class="max-w-4xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-lg">
        <h2 class="text-3xl font-semibold text-center mb-6">Productos</h2>

        <!-- Verificar si el contenido de productos está vacío -->
        <c:if test="${empty productos}">
            <p class="text-center text-gray-600">No existen productos. <a href="menu.jsp" class="text-blue-500 hover:underline">Volver al Menú</a></p>
        </c:if>

        <!-- Mostrar contenido de clientes si no está vacío -->
        <c:if test="${not empty productos}">
            <table class="min-w-full bg-white border border-gray-300 mb-6">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="py-2 px-4 border-b text-left">ID</th>
                        <th class="py-2 px-4 border-b text-left">Nombre</th>
                        <th class="py-2 px-4 border-b text-left">Tipo</th>
                        <th class="py-2 px-4 border-b text-left">Precio</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (Producto p : productos) {%>
                        <tr>
                            <td class="py-2 px-4 border-b"><%=p.getId()%></td>
                            <td class="py-2 px-4 border-b"><%=p.getNombre()%></td>
                            <td class="py-2 px-4 border-b"><%=p.getTipo()%></td>
                            <td class="py-2 px-4 border-b"><%=p.getPrecio()%></td>
                        </tr>
                    <%}%>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>

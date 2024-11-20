<%@page import="Model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    HttpSession sesion = request.getSession(true);
    ArrayList<Usuario> clientes = sesion.getAttribute("clientes") == null ? null : (ArrayList) sesion.getAttribute("clientes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clientes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
</head>
<body class="bg-gray-100 text-gray-800">
    <div class="max-w-4xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-lg">
        <h2 class="text-3xl font-semibold text-center mb-6">Clientes</h2>

        <!-- Verificar si el contenido de clientes está vacío -->
        <c:if test="${empty clientes}">
            <p class="text-center text-gray-600">No existen clientes. <a href="menu.jsp" class="text-blue-500 hover:underline">Volver al Menú</a></p>
        </c:if>

        <!-- Mostrar contenido de clientes si no está vacío -->
        <c:if test="${not empty clientes}">
            <table class="min-w-full bg-white border border-gray-300 mb-6">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="py-2 px-4 border-b text-left">ID</th>
                        <th class="py-2 px-4 border-b text-left">Nombre</th>
                        <th class="py-2 px-4 border-b text-left">Email</th>
                        <th class="py-2 px-4 border-b text-left">Dirección</th>
                        <th class="py-2 px-4 border-b text-left">Teléfono</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (Usuario c : clientes) {%>
                        <tr>
                            <td class="py-2 px-4 border-b"><%=c.getId()%></td>
                            <td class="py-2 px-4 border-b"><%=c.getNombre()%></td>
                            <td class="py-2 px-4 border-b"><%=c.getEmail()%></td>
                            <td class="py-2 px-4 border-b"><%=c.getDireccion()%></td>
                            <td class="py-2 px-4 border-b"><%=c.getTelefono()%></td>
                        </tr>
                    <%}%>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>

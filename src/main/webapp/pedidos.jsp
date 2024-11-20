<%@page import="Model.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    HttpSession sesion = request.getSession(true);
    ArrayList<Pedido> pedidos = sesion.getAttribute("pedidos") == null ? null : (ArrayList) sesion.getAttribute("pedidos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pedidos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
</head>
<body class="bg-gray-100 text-gray-800">
    <div class="max-w-4xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-lg">
        <h2 class="text-3xl font-semibold text-center mb-6">Pedidos</h2>

        <!-- Verificar si el contenido de pedidos está vacío -->
        <c:if test="${empty pedidos}">
            <p class="text-center text-gray-600">No existen pedidos. <a href="menu.jsp" class="text-blue-500 hover:underline">Volver al Menú</a></p>
        </c:if>

        <!-- Mostrar contenido de pedidos si no está vacío -->
        <c:if test="${not empty pedidos}">
            <table class="min-w-full bg-white border border-gray-300 mb-6">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="py-2 px-4 border-b text-left">ID</th>
                        <th class="py-2 px-4 border-b text-left">ID Usuario</th>
                        <th class="py-2 px-4 border-b text-left">Total</th>
                        <th class="py-2 px-4 border-b text-left">Fecha</th>
                        <th class="py-2 px-4 border-b text-left">Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (Pedido p : pedidos) {
                    %>
                        <tr>
                            <td class="py-2 px-4 border-b"><%=p.getId()%></td>
                            <td class="py-2 px-4 border-b"><%=p.getIdUsuario()%></td>
                            <td class="py-2 px-4 border-b"><%=p.getTotal()%></td>
                            <td class="py-2 px-4 border-b"><%=p.getFecha()%></td>
                            <td class="py-2 px-4 border-b"><%=p.getEstado()%></td>
                            <%if (p.getEstado().equals("pendiente")) {%>
                            <td class="py-2 px-4">
                                <a href="PedidoServlet?pedidoId=<%=p.getId()%>" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-3 rounded">
                                    <i class="fas fa-cart-plus"></i> Finalizar
                                </a>
                            </td>
                            <%}%>
                        </tr>
                    <%}%>
                </tbody>
            </table>
            <a href="GenerarPDFServlet" class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-3 rounded">
                <i class="fas fa-cart-plus"></i> Generar Reporte
            </a>
        </c:if>
    </div>
</body>
</html>

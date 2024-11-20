<%@page import="DAO.ProductoDAO"%>
<%@page import="Model.Producto"%>
<%@page import="Model.Usuario"%>
<%@page import="Model.Articulo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    HttpSession sesion = request.getSession(true);
    ArrayList<Articulo> pedido = sesion.getAttribute("pedido") == null ? null : (ArrayList) sesion.getAttribute("pedido");
    Usuario usuario = sesion.getAttribute("usuario") == null ? null : (Usuario) sesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle del Pedido</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
</head>
<body class="bg-gray-100 text-gray-800">
    <div class="max-w-3xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-lg">
        <h2 class="text-3xl font-semibold text-center mb-6">Pedido</h2>

        <c:if test="${empty pedido}">
            <p class="text-center text-gray-600">Aún no has realizado un pedido. <a href="menu.jsp" class="text-blue-500 hover:underline">Volver al Menú</a></p>
        </c:if>
        
        <c:if test="${not empty pedido}">
        <!-- Datos del pedido -->
        <div class="mb-6">
            <p class="text-lg"><strong>Estado de Pedido:</strong> Pendiente</p>
            <p class="text-lg"><strong>Email del Cliente:</strong> <%=usuario.getEmail()%></p>
        </div>

        <!-- Tabla de productos -->
        <div class="overflow-x-auto">
            <table class="min-w-full bg-white border border-gray-300">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="py-2 px-4 border-b text-left">Producto</th>
                        <th class="py-2 px-4 border-b text-left">Cantidad</th>
                        <th class="py-2 px-4 border-b text-left">Precio Unitario</th>
                        <th class="py-2 px-4 border-b text-left">Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        double total = 0;
                        for (Articulo a : pedido) {
                            Producto p = ProductoDAO.obtenerProducto(a.getCodigoProducto());
                            total += p.getPrecio() * a.getCantidad();
                    %>
                        <tr>
                            <td class="py-2 px-4 border-b"><%=p.getNombre()%></td>
                            <td class="py-2 px-4 border-b"><%=a.getCantidad()%></td>
                            <td class="py-2 px-4 border-b">S/. <%=p.getPrecio()%></td>
                            <td class="py-2 px-4 border-b">S/. <%=p.getPrecio() * a.getCantidad()%></td>
                        </tr>
                    <%}%>
                </tbody>
            </table>
        </div>

        <!-- Total del pedido -->
        <div class="mt-4 text-right">
            <p class="text-2xl font-bold">Total: S/. <%= total %></p>
        </div>

        <!-- Enlace para descargar PDF -->
        <div class="text-center mt-6">
            <a href="GenerarPDFServlet?id=1" class="inline-block bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                Descargar PDF del Pedido
            </a>
        </div>
        </c:if>
    </div>
</body>
</html>

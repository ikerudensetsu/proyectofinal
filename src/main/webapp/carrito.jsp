<%@page import="DAO.ProductoDAO"%>
<%@page import="Model.Articulo"%>
<%@page import="Model.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    HttpSession sesion = request.getSession(true);
    ArrayList<Articulo> carrito = sesion.getAttribute("carrito") == null ? null : (ArrayList) sesion.getAttribute("carrito");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
    <script>
        // Función para actualizar la cantidad de un producto en el carrito
        function actualizarCantidad(productoId) {
            const cantidad = document.getElementById("cantidad-" + productoId).value;
            window.location.href = `CarritoController?action=update&productoId=`+productoId+`&cantidad=`+cantidad;
        }

        // Función para eliminar un producto del carrito
        function eliminarProducto(productoId) {
            window.location.href = `CarritoController?action=delete&productoId=`+productoId;
        }
    </script>
</head>
<body class="bg-gray-100 text-gray-800">
    <div class="max-w-4xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-lg">
        <h2 class="text-3xl font-semibold text-center mb-6">Carrito de Compras</h2>

        <!-- Verificar si el carrito está vacío -->
        <c:if test="${empty carrito}">
            <p class="text-center text-gray-600">Tu carrito está vacío. <a href="menu.jsp" class="text-blue-500 hover:underline">Volver al Menú</a></p>
        </c:if>

        <!-- Mostrar contenido del carrito si no está vacío -->
        <c:if test="${not empty carrito}">
            <table class="min-w-full bg-white border border-gray-300 mb-6">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="py-2 px-4 border-b text-left">Producto</th>
                        <th class="py-2 px-4 border-b text-center">Cantidad</th>
                        <th class="py-2 px-4 border-b text-left">Precio Unitario</th>
                        <th class="py-2 px-4 border-b text-left">Subtotal</th>
                        <th class="py-2 px-4 border-b text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        double total = 0;
                        for (Articulo a : carrito) {
                            Producto p = ProductoDAO.obtenerProducto(a.getCodigoProducto());
                            total += p.getPrecio() * a.getCantidad();
                    %>
                        <tr>
                            <td class="py-2 px-4 border-b"><%=p.getNombre()%></td>
                            <td class="py-2 px-4 border-b text-center">
                                <input type="number" id="cantidad-<%=a.getCodigoProducto()%>" name="cantidad" value="<%=a.getCantidad()%>" min="1" class="w-16 text-center border rounded" onchange="actualizarCantidad(<%=a.getCodigoProducto()%>)">
                            </td>
                            <td class="py-2 px-4 border-b">S/. <%=p.getPrecio()%></td>
                            <td class="py-2 px-4 border-b">S/. <%=p.getPrecio() * a.getCantidad()%></td>
                            <td class="py-2 px-4 border-b text-center">
                                <button class="text-red-500 hover:text-red-700" onclick="eliminarProducto(<%=a.getCodigoProducto()%>)">Eliminar</button>
                            </td>
                        </tr>
                    <%}%>
                </tbody>
            </table>

            <!-- Total del carrito -->
            <div class="text-right mb-6">
                <p class="text-2xl font-bold">Total: S/. <%= total %></p>
            </div>

            <!-- Botones para finalizar compra o regresar al menú -->
            <div class="flex justify-between">
                <a href="menu.jsp" class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded">
                    Seguir Agregando
                </a>
                <a href="FinalizarPedidoServlet?total=<%= total %>" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
                    Finalizar Pedido
                </a>
            </div>
        </c:if>
    </div>
</body>
</html>

<%-- 
    Document   : verCarrito
    Created on : 16 oct. 2024, 17:49:45
    Author     : jacs2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body class="bg-gray-100">

    <!-- Contenedor del carrito -->
    <div class="max-w-4xl mx-auto mt-10 bg-white rounded-lg shadow-lg p-6">
        <h2 class="text-2xl font-bold text-gray-700 mb-4">
            <i class="fas fa-shopping-cart"></i> Tu carrito
        </h2>

        <!-- Tabla de productos -->
        <table class="min-w-full bg-white border-collapse">
            <thead>
                <tr class="bg-gray-200">
                    <th class="py-3 px-4 text-left">Platillo</th>
                    <th class="py-3 px-4 text-left">Cantidad</th>
                    <th class="py-3 px-4 text-left">Precio</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="producto" items="${carrito.productos}">
                    <tr class="border-b">
                        <td class="py-3 px-4">${producto.key.nombre}</td>
                        <td class="py-3 px-4">${producto.value}</td>
                        <td class="py-3 px-4">S/. ${producto.key.precio}</td>
                        <td class="py-3 px-4">
                            <!-- Botón de eliminar -->
                            <a href="EliminarProductoServlet?productoId=${producto.key.id}" class="text-red-500 hover:text-red-700 font-bold">
                                <i class="fas fa-trash-alt"></i> Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Total a pagar -->
        <div class="mt-6">
            <p class="text-xl font-bold text-gray-700">Total a pagar: 
                <span class="text-green-600">S/. ${carrito.calcularTotal()}</span>
            </p>
        </div>

        <!-- Botón para confirmar pedido -->
        <div class="mt-6">
            <a href="confirmarpedido.jsp" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded inline-flex items-center">
                <i class="fas fa-check-circle mr-2"></i> Confirmar Pedido
            </a>
        </div>
    </div>

</body>
</html>

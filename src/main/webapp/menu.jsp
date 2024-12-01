<%-- 
    Document   : menu
    Created on : 16 oct. 2024, 01:42:28
    Author     : jacs2
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL para manejo de la lógica de iteración -->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú de Productos</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body class="bg-gray-100">

<div class="max-w-4xl mx-auto mt-10 bg-white rounded-lg shadow-lg p-6">
    <h2 class="text-2xl font-bold text-gray-700 mb-4">
        <i class="fas fa-home"></i> Menú
    </h2>

    <!-- Mostrar rol del usuario -->
    <div class="mb-4">
        <c:if test="${not empty sessionScope.rolUsuario}">
            <p class="text-lg">Rol: <strong>${sessionScope.rolUsuario}</strong></p>
        </c:if>
    </div>
<!-- Título -->
<h1 class="text-3xl font-bold text-center mt-6 mb-8">Elige una categoría del menú</h1>

<!-- Formulario para seleccionar la categoría -->
<div class="max-w-4xl mx-auto p-6 bg-white rounded-lg shadow-md">
    <form action="MenuServlet" method="get" class="mb-6">
        <label for="categoria" class="block text-gray-700 font-semibold">Categoría:</label>
        <select id="categoria" name="tipo" class="mt-1 block w-full p-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            <option value="comida" ${categoriaSeleccionada == 'comida' ? 'selected' : ''}>Comida</option>
            <option value="bebida" ${categoriaSeleccionada == 'bebida' ? 'selected' : ''}>Bebida</option>
            <option value="postre" ${categoriaSeleccionada == 'postre' ? 'selected' : ''}>Postre</option>
        </select>
        <input type="submit" value="Ver productos" class="mt-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
    </form>

    <!-- Búsqueda de platillos -->
    <form action="BuscarPlatilloServlet" method="get" class="mb-6">
        <div class="relative">
            <input type="text" name="buscar" placeholder="Buscar platillo..." class="block w-full p-2 pl-10 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            <button type="submit" class="absolute left-2 top-2 text-gray-500"><i class="fas fa-search"></i></button>
        </div>
    </form>

    <!-- Opciones del carrito de compras -->
    <div class="flex space-x-4 mb-6">
        <button onclick="window.location.href='CarritoServlet'" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
            <i class="fas fa-shopping-cart"></i> Ver Carrito
        </button>
        <button onclick="window.location.href='FinalizarPedidoServlet'" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
            <i class="fas fa-check"></i> Finalizar Pedido
        </button>
    </div>
</div>

<!-- Productos disponibles -->
<div class="max-w-4xl mx-auto bg-white rounded-lg shadow-md p-6">
    <h2 class="text-2xl font-semibold text-gray-700 mb-4">Productos disponibles:</h2>
    <table class="min-w-full bg-white">
        <thead class="bg-gray-200">
            <tr>
                <th class="py-2 px-4 text-left">ID</th>
                <th class="py-2 px-4 text-left">Nombre</th>
                <th class="py-2 px-4 text-left">Tipo</th>
                <th class="py-2 px-4 text-left">Precio</th>
                <th class="py-2 px-4 text-left">Agregar al carrito</th>
            </tr>
        </thead>
        <tbody>
            <!-- Itera sobre la lista de productos enviada por el servlet -->
            <c:forEach var="producto" items="${productos}">
                <tr class="border-b">
                    <td class="py-2 px-4">${producto.id}</td>
                    <td class="py-2 px-4">${producto.nombre}</td>
                    <td class="py-2 px-4">${producto.tipo}</td>
                    <td class="py-2 px-4">S/. ${producto.precio}</td>
                    <td class="py-2 px-4">
                        <a href="AgregarCarritoServlet?productoId=${producto.id}&cantidad=1" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-3 rounded">
                            <i class="fas fa-cart-plus"></i> Agregar
                        </a>
                    </td>
                </tr>
            </c:forEach>

            <!-- Si no hay productos en la categoría, muestra este mensaje -->
            <c:if test="${empty productos}">
                <tr>
                    <td colspan="5" class="py-4 text-center text-gray-500">No se encontraron productos en esta categoría.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

</body>
</html>



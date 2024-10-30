<%-- 
    Document   : admin
    Created on : 16 oct. 2024, 00:46:15
    Author     : jacs2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>Web Delivery - Restaurante Sorpresa</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
</head>
<body class="bg-gradient-to-br from-blue-400 to-indigo-600 text-center min-h-screen p-6 text-gray-800">
    <!-- Header -->
    <div class="bg-white p-4 rounded-lg shadow-lg mb-6">
        <div class="flex justify-between items-center">
            <div class="text-lg font-semibold text-blue-600">
                Restaurante Sorpresa
            </div>
            <c:if test="${not empty sessionScope.rolUsuario}">
                <p class="text-md">Rol: <strong>${sessionScope.rolUsuario}</strong></p>
            </c:if>
        </div>
    </div>

    <!-- Navegación -->
    <div class="bg-white p-4 rounded-lg shadow-lg mb-6">
        <div class="flex justify-center space-x-4">
            <button class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition duration-300" onclick="window.location.href='platos.jsp';">Menú</button>
            <button class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition duration-300">Pedidos</button>
            <button class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition duration-300">Pagos</button>
            <button class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition duration-300">Clientes</button>
        </div>
    </div>

    <!-- Logo y Sorpresa -->
    <div class="bg-white p-8 rounded-lg shadow-lg mb-6">
        <img alt="Restaurante Sorpresa logo" class="mx-auto mb-4" height="200" src="https://i.postimg.cc/zXbBgXNt/104162668-3152138588181921-5802797647913938028-n.jpg" width="200"/>
        <button class="bg-blue-500 text-white px-4 py-2 mt-4 rounded hover:bg-blue-600 transition duration-300">
            Descubre tu Sorpresa
        </button>
    </div>
</body>
</html>

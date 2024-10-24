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
<body class="bg-white text-center">
    <div class="bg-gray-200 p-4 flex justify-between items-center">
       <div class="mb-4">
        <c:if test="${not empty sessionScope.rolUsuario}">
            <p class="text-lg">Rol: <strong>${sessionScope.rolUsuario}</strong></p>
        </c:if>
    </div>
        </div>
        <div class="text-lg font-semibold">
            Restaurante Sorpresa
        </div>
       
 
    <div class="mt-4">
        <div class="flex justify-center space-x-4">
            <button class="bg-blue-500 text-white px-4 py-2 rounded" onclick="window.location.href='menu.jsp';" >Menu</button>
            <button class="bg-blue-500 text-white px-4 py-2 rounded">pedidos</button>
            <button class="bg-blue-500 text-white px-4 py-2 rounded">pagos </button>
            <button class="bg-blue-500 text-white px-4 py-2 rounded">clientes</button>
        </div>
    </div>

    <div class="mt-8">
        <img alt="Restaurant Sorpresa logo" class="mx-auto" height="200" src="https://i.postimg.cc/zXbBgXNt/104162668-3152138588181921-5802797647913938028-n.jpg" width="200"/>
        <button class="border px-4 py-2 mt-4">Descubre tu Sorpresa</button>
    </div>

    
</body>
</html>
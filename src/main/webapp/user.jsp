<%-- 
    Document   : user
    Created on : 16 oct. 2024, 00:46:23
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
       
        </div>
        <div class="text-lg font-semibold">
            Web Delivery - Restaurante Sorpresa
        </div>
        <div class="mb-4">
        <c:if test="${not empty sessionScope.rolUsuario}">
            <p class="text-lg">Rol: <strong>${sessionScope.rolUsuario}</strong></p>
        </c:if>
    </div>
   

    <div class="mt-4">
        <div class="flex justify-center space-x-4">
            <button class="bg-blue-500 text-white px-4 py-2 rounded" onclick="window.location.href='menu.jsp';" >Menu</button>
            <button class="border px-4 py-2 rounded">Mi Pedido</button>
            <button class="border px-4 py-2 rounded">Promociones</button>
            <button class="border px-4 py-2 rounded">Contacto</button>
        </div>
    </div>

    <div class="mt-8">
        <img alt="Restaurant Sorpresa logo" class="mx-auto" height="200" src="https://i.postimg.cc/zXbBgXNt/104162668-3152138588181921-5802797647913938028-n.jpg" width="200"/>
        <button class="border px-4 py-2 mt-4">Descubre tu Sorpresa</button>
    </div>

    <div class="mt-8">
        <h2 class="text-xl font-semibold">Un toque de frescura en cada bocado</h2>
        <div class="grid grid-cols-2 gap-4 mt-4">
            <div>
                <img alt="Imagen de Entradas" class="mx-auto" height="100" src="https://i.postimg.cc/QN6mMSNg/causa.png" width="100"/>
                <p>Entradas</p>
            </div>
            <div>
                <img alt="Imagen de Platos Fuertes" class="mx-auto" height="100" src="https://i.postimg.cc/TYgccxq0/plato-fuerte.png" width="100"/>
                <p>Platos Fuertes</p>
            </div>
            <div>
                <img alt="Imagen de Postres" class="mx-auto" height="100" src="https://storage.googleapis.com/a1aa/image/9PELHYOjqjq4M9IjWTC4wwIRWgdCsbdaoeve8k4oLUAfdVFnA.jpg" width="100"/>
                <p>Postres</p>
            </div>
            <div>
                <img alt="Imagen de Bebidas" class="mx-auto" height="100" src="https://storage.googleapis.com/a1aa/image/SVjfBqU62x08TSrFl7f7XCY6bDeLJpsPMRpj1IOzFZmAeqKOB.jpg" width="100"/>
                <p>Bebidas</p>
            </div>
        </div>
    </div>

    <div class="mt-8 flex justify-between border-t pt-4">
        <p>Información de Contacto</p>
        <p>Términos y Condiciones</p>
    </div>
</body>
</html>
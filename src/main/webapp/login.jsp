<%-- 
    Document   : login
    Created on : 16 oct. 2024, 00:41:47
    Author     : jacs2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Web Delivery - Restaurante Sorpresa</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
</head>
<body class="bg-gradient-to-br from-blue-400 to-indigo-600 flex items-center justify-center min-h-screen p-6">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md transform transition duration-500 hover:scale-105">
        <% if (request.getParameter("error") != null) { %>
            <p class="text-red-500 text-sm text-center mb-4">Credenciales incorrectas. Inténtalo de nuevo.</p>
        <% } %>
        <div class="text-center mb-6">
            <img alt="Restaurante Sorpresa logo" class="mx-auto mb-2 h-20 w-20" src="https://i.postimg.cc/9FjSGL56/2mryccyx-Kxaiek-Yyy-T97eztctf5.png"/>
            <h2 class="text-3xl font-bold text-gray-800">Iniciar sesión</h2>
            <p class="text-gray-500">Bienvenido de nuevo a Restaurante Sorpresa</p>
        </div>
        <form action="LoginServlet" method="post" class="space-y-4">
            <div>
                <label class="block text-gray-700 text-sm font-medium" for="email">Correo:</label>
                <input type="email" id="email" name="email" class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="Ingresa tu correo" required>
            </div>
            <div>
                <label class="block text-gray-700 text-sm font-medium" for="password">Contraseña:</label>
                <input type="password" id="password" name="password" class="w-full px-4 py-2 mt-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="Ingresa tu contraseña" required>
            </div>
            <div class="flex items-center justify-between">
                <button type="submit" class="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 rounded-lg transition duration-200 transform hover:scale-105">
                    <i class="fas fa-sign-in-alt mr-2"></i> Iniciar sesión
                </button>
            </div>
        </form>
        <div class="text-center mt-6">
            <a href="#" class="text-sm text-blue-500 hover:text-blue-600 transition duration-200">¿Olvidaste tu contraseña?</a>
        </div>
    </div>
</body>
</html>


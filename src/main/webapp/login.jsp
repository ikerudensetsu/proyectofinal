<%-- 
    Document   : login
    Created on : 16 oct. 2024, 00:41:47
    Author     : jacs2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Web Delivery - Restaurante Sorpresa</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
    <div class="bg-white p-8 rounded shadow-md w-full max-w-md">
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Credenciales incorrectas. Inténtalo de nuevo.</p>
    <% } %>
     <div class="text-center mb-6">
            <img alt="Restaurante Sorpresa logo" class="mx-auto mb-2" height="100" src="https://i.postimg.cc/9FjSGL56/2mryccyx-Kxaiek-Yyy-T97eztctf5.png" width="100"/>
            
        </div>
     
    <h2 class="text-2xl font-bold text-center mb-6">Iniciar sesión</h2>
    <form action="LoginServlet" method="post">
        <div class="mb-4">
        <label class="block text-gray-700" for="email"> Correo:</label>
        <input type="email" id="email" name="email" required>
        <br><br>
        </div>
        <div class="mb-4">
        <label class="block text-gray-700" for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required><br><br>
         </div>
        <div class="mb-4">
        <br><br>
           </div>
        <div class="flex items-center justify-between mb-4">
        <input class="px-4 py-2 bg-blue-500 text-white rounded" type="submit" value="Iniciar sesión">
        </div>
    </form>
     
</body>
</html>


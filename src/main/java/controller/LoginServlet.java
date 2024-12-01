/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.User;


public class LoginServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener los parámetros del formulario de login
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Creamos un objeto User e intentamos autenticar
        User user = new User(email, password);  // Constructor de User con email y password
        
        if (user.authenticate()) {
            // Autenticación exitosa, obtener el rol
            String rol = user.getRole();  // Obtener el rol del usuario autenticado

            // Guardar el rol en la sesión
            request.getSession().setAttribute("rolUsuario", rol);
            request.getSession().setAttribute("usuario", user);

            // Redirigir al menú principal (o según el rol)
            if ("admin".equals(rol)) {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else if ("user".equals(rol)) {
                request.getRequestDispatcher("user.jsp").forward(request, response);
            } else {
                response.sendRedirect("menu.jsp");
                 response.sendRedirect("user.jsp");
            }
        } else {
            // Autenticación fallida, redirigir a login con un mensaje de error
            response.sendRedirect("login.jsp?error=1");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description of LoginServlet";
    }

}

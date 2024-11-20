package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.Database;
import Model.Usuario;

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

        // Creamos un objeto Usuario e intentamos autenticar
        Usuario user = new Usuario(email, password);  // Constructor de Usuario con email y password
        
        boolean isAuthenticated = false;
        
        try {
            Connection cn = Database.getConnection();
            String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setRol(rs.getString("rol"));
                user.setNombre(rs.getString("nombre"));
                isAuthenticated = true;
            }
            
            rs.close();
            ps.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (isAuthenticated == true) {
            // Guardar usuario en la sesión
            request.getSession().setAttribute("usuario", user);

            // Redirigir al menú principal (o según el rol)
            if (user.getRol().equals("admin")) {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else if (user.getRol().equals("cliente")) {
                request.getRequestDispatcher("user.jsp").forward(request, response);
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

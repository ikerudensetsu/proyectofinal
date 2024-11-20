package Controller;

import Connection.Database;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MenuServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MenuServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo"); // Recibe el parámetro de la categoría (comida, bebida, postre)
        
        if (tipo == null || tipo.isEmpty()) {
            tipo = "comida";  // Valor por defecto
        }

        List<Producto> productos = obtenerProductosPorCategoria(tipo);  // Método para obtener productos según la categoría

        request.setAttribute("productos", productos); // Pasa la lista de productos al JSP
        request.setAttribute("categoriaSeleccionada", tipo); // Pasa la categoría seleccionada

        request.getRequestDispatcher("menu.jsp").forward(request, response); // Reenvía a `menu.jsp` para mostrar los productos
    }

    private List<Producto> obtenerProductosPorCategoria(String tipo) {
        List<Producto> productos = new ArrayList<>();

        try {
            Connection cn = Database.getConnection();
            String query = "SELECT * FROM productos WHERE tipo = ?"; // Consulta SQL por categoría
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, tipo);  // Establece la categoría seleccionada

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id"), 
                    rs.getString("nombre"),
                    rs.getString("tipo"), 
                    rs.getDouble("precio")
                );
                productos.add(producto);  // Añade cada producto a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Maneja la excepción
        }
        return productos;  // Devuelve la lista de productos
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

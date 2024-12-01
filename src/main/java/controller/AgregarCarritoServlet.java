/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Producto;

/**
 *
 * @author jacs2
 */
public class AgregarCarritoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgregarCarritoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarCarritoServlet at " + request.getContextPath() + "</h1>");
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
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    Carrito carrito = (Carrito) session.getAttribute("carrito");

    if (carrito == null) {
        carrito = new Carrito();
        session.setAttribute("carrito", carrito);
    }

    // Obtener el producto y la cantidad
    int productoId = Integer.parseInt(request.getParameter("productoId"));
    int cantidad = Integer.parseInt(request.getParameter("cantidad"));

    // Buscar el producto
    Producto producto = buscarProductoPorId(productoId);

    if (producto != null) {
        carrito.agregarProducto(producto, cantidad);
    }

    // Redirigir al carrito
    response.sendRedirect("verCarrito.jsp");
}

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el carrito de la sesión
        HttpSession session = request.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        // Obtener el producto y la cantidad a agregar
        int productoId = Integer.parseInt(request.getParameter("productoId"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        // Buscar el producto en la base de datos
        Producto producto = buscarProductoPorId(productoId);

        if (producto != null) {
            carrito.agregarProducto(producto, cantidad);
        }

        // Redirigir al carrito
        response.sendRedirect("verCarrito.jsp");
    }

    
   
    
    private Producto buscarProductoPorId(int id) {
        
          Producto producto = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Establecer la conexión a la base de datos (asegúrate de tener el JDBC MySQL connector)
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deliverydb", "root", "@12Juan202005");

        // Consulta SQL para obtener el producto por su ID
        String sql = "SELECT * FROM productos WHERE id = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        // Ejecutar la consulta
        rs = ps.executeQuery();

        // Si se encuentra el producto, crear un objeto Producto
        if (rs.next()) {
            producto = new Producto(
                rs.getInt("id"),        // ID del producto
                rs.getString("nombre"), // Nombre del producto
                rs.getString("tipo"),   // Tipo de producto (ej. comida, bebida, etc.)
                rs.getDouble("precio")  // Precio del producto
            );
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
    }

    return producto;
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

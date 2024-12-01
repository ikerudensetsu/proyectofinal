package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Dao.PagoDAO;
import Dao.PedidoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Carrito;
import modelo.Pago;
import modelo.Pedido;

/**
 *
 * @author jacs2
 */
public class FinalizarPedidoServlet extends HttpServlet {

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
            out.println("<title>Servlet FinalizarPedidoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinalizarPedidoServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
// Obtener el carrito y el usuario de la sesión
        Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
        String emailUsuario = (String) request.getSession().getAttribute("emailUsuario");

        if (carrito != null && emailUsuario != null) {
            double total = carrito.calcularTotal();

            // Crear un objeto Pedido con los productos del carrito
            Pedido pedido = new Pedido(emailUsuario, carrito.getProductos(), total);

            // Guardar el pedido en la base de datos
            PedidoDAO pedidoDAO = new PedidoDAO();
            try {
                // Guardar el pedido en la base de datos
                int pedidoId = pedidoDAO.guardarPedido(pedido); // Retorna el ID generado del pedido

                // Crear un pago para el pedido
                PagoDAO pagoDAO = new PagoDAO();
                Pago pago = new Pago(pedidoId, "Tarjeta", total, "pagado"); // Aquí puedes cambiar el método de pago
                pagoDAO.guardarPago(pago);

                // Limpiar el carrito después de confirmar el pedido
                request.getSession().setAttribute("carrito", null);

                // Redirigir a la página de confirmación
                response.sendRedirect("confirmacionPedido.jsp");

            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp"); // Redirigir a una página de error si algo sale mal
            }
        } else {
            // Si no hay carrito o usuario, redirigir a la página del carrito
            response.sendRedirect("carrito.jsp");
        }    }

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

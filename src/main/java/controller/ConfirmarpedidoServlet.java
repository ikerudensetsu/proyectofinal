/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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


public class ConfirmarpedidoServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConfirmarpedidoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmarpedidoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // Obtener el carrito de la sesión
        Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
        String emailUsuario = (String) request.getSession().getAttribute("emailUsuario");

        if (carrito != null && emailUsuario != null) {
            // Obtener el método de pago del formulario
            String metodoPago = request.getParameter("metodoPago");

            // Calcular el total del carrito
            double total = carrito.calcularTotal();

            // Crear un objeto Pedido
            Pedido pedido = new Pedido(emailUsuario, carrito.getProductos(), total);

            // Guardar el pedido en la base de datos
            PedidoDAO pedidoDAO = new PedidoDAO();
            try {
                // Guardar el pedido y obtener el ID del pedido generado
                int pedidoId = pedidoDAO.guardarPedido(pedido);

                // Crear un objeto Pago con el método de pago seleccionado
                PagoDAO pagoDAO = new PagoDAO();
                Pago pago = new Pago(pedidoId, metodoPago, total, "pendiente");
                pagoDAO.guardarPago(pago);

                // Limpiar el carrito después de confirmar el pedido
                request.getSession().setAttribute("carrito", null);
                

                // Redirigir a la página de confirmación de pedido
                response.sendRedirect("confirmacionpedido.jsp");

            } catch (SQLException e) {
                e.printStackTrace();
                // En caso de error, redirigir a la página de error
                response.sendRedirect("error.jsp");
            }
        } else {
            // Si no hay carrito o usuario, redirigir al carrito
            response.sendRedirect("verCarrito.jsp");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

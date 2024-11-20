package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.Articulo;
import java.util.ArrayList;

public class CarritoServlet extends HttpServlet {

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
            out.println("<title>Servlet CarritoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CarritoController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        ArrayList <Articulo> carrito = session.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList)session.getAttribute("carrito");

        String action = request.getParameter("action");
        int productoId = Integer.parseInt(request.getParameter("productoId"));

        switch (action) {
            case "add":
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                boolean nuevo = false;
                if (carrito.size() > 0) {
                    //Recorremos todos los articulos
                    for (Articulo a : carrito) {
                        //Preguntamos si existe uno con el mismo id, en caso de ser así, actualizamos su cantidad
                        if (productoId == a.getCodigoProducto()) {
                            a.setCantidad(a.getCantidad() + cantidad);
                            nuevo = true;
                            break;
                        }
                    }
                }
                if (!nuevo) {
                    carrito.add(new Articulo(productoId, cantidad));
                }
                break;
            case "update":
                int cantidad1 = Integer.parseInt(request.getParameter("cantidad"));
                if (carrito.size() > 0) {
                    //Recorremos todos los articulos
                    for (Articulo a : carrito) {
                        //Preguntamos si existe uno con el mismo id, en caso de ser así, actualizamos su cantidad
                        if (productoId == a.getCodigoProducto()) {
                            a.setCantidad(cantidad1);
                            break;
                        }
                    }
                }
                break;
            case "delete":
                for (int i = 0; i < carrito.size(); i++) {
                    if (carrito.get(i).getCodigoProducto() == productoId) {
                        carrito.remove(i);
                        break;
                    }
                }
                break;
        }

        session.setAttribute("carrito", carrito);
        response.sendRedirect("carrito.jsp");
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
        processRequest(request, response);
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

package Controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Connection.Database;
import DAO.ProductoDAO;
import Model.Articulo;
import Model.Pedido;
import Model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        HttpSession session = request.getSession();
        Usuario usuario = session.getAttribute("usuario") == null ? null : (Usuario) session.getAttribute("usuario");
        ArrayList <Articulo> carrito = session.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList)session.getAttribute("carrito");
        double total = Double.parseDouble(request.getParameter("total"));
        int pedidoId = 0;
        Pedido p = new Pedido(usuario.getId(), total);
        
        try {          
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO pedidos (id_usuario, total) VALUES (?, ?)";
            String sqlD = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getIdUsuario());
            ps.setDouble(2, p.getTotal());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                pedidoId = rs.getInt(1);
            }

            for (int i = 0; i < carrito.size(); i++) {
                ps = conn.prepareStatement(sqlD);
                ps.setInt(1, pedidoId);
                ps.setInt(2, carrito.get(i).getCodigoProducto());
                ps.setInt(3, carrito.get(i).getCantidad());
                ps.setDouble(4, ProductoDAO.obtenerProducto(carrito.get(i).getCodigoProducto()).getPrecio() * carrito.get(i).getCantidad());
                ps.executeUpdate();
            }
            session.setAttribute("pedido", carrito);
            
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "token=qwyfp7z6mjoaocb3&to=51"+usuario.getTelefono()+"&body=Restaurante Sorpresa: Hemos recibido tu pedido&priority=10&referenceId=");
            Request request1 = new Request.Builder()
                .url("https://api.ultramsg.com/instance99728/messages/chat")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
            Response response1 = client.newCall(request1).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("usuario", usuario);
        response.sendRedirect("pedido.jsp");
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

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Connection.Database;
import Model.Articulo;
import Model.Pedido;
import java.util.ArrayList;

public class PedidoDAO {

    public static int guardarPedido(Pedido pedido, ArrayList <Articulo> carrito) {
        int pedidoId = 0;

        try {
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO pedidos (id_usuario, total) VALUES (?, ?)";
            String sqlD = "INSERT INTO detalle_pedido (pedido_id, producto_id, cantidad, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pedido.getIdUsuario());
            ps.setDouble(2, pedido.getTotal());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            // Obtener el ID del pedido generado
            if (rs.next()) {
                pedidoId = rs.getInt(1);
            }

            // Insertar detalles del pedido
            for (int i = 0; i < carrito.size(); i++) {
                ps = conn.prepareStatement(sqlD);
                ps.setInt(1, pedidoId);
                ps.setInt(2, carrito.get(i).getCodigoProducto());
                ps.setInt(3, carrito.get(i).getCantidad());  // Cantidad
                ps.setDouble(4, ProductoDAO.obtenerProducto(carrito.get(i).getCodigoProducto()).getPrecio() * carrito.get(i).getCantidad());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidoId;
    }
}

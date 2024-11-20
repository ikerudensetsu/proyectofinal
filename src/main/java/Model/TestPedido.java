
package Model;

import Connection.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestPedido {
    public static int guardarPedido(Pedido pedido) {
        int pedidoId = 0;

        try {
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO pedidos (id_usuario, total) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pedido.getIdUsuario());
            ps.setDouble(2, pedido.getTotal());

            ps.executeUpdate();
            /*ResultSet rs = ps.getGeneratedKeys();

            // Obtener el ID del pedido generado
            if (rs.next()) {
                pedidoId = rs.getInt(1);
            }

            // Insertar detalles del pedido
            for (Map.Entry<Producto, Integer> entry : pedido.getProductos().entrySet()) {
                String sqlDetalle = "INSERT INTO detalle_pedido (pedido_id, producto_id, cantidad, precio) VALUES (?, ?, ?, ?)";
                PreparedStatement detalleStmt = conn.prepareStatement(sqlDetalle);
                detalleStmt.setInt(1, pedidoId);
                detalleStmt.setInt(2, entry.getKey().getId());
                detalleStmt.setInt(3, entry.getValue());  // Cantidad
                detalleStmt.setDouble(4, entry.getKey().getPrecio());
                detalleStmt.executeUpdate();
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidoId;
    }
    
    public static void main(String[] args) {
        guardarPedido(new Pedido(1, 38.0));
    }
}

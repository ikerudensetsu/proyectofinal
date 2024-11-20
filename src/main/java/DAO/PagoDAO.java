
package DAO;

import Model.Pago;
import Connection.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PagoDAO {

    public void guardarPago(Pago pago) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Database.getConnection();
            String sql = "INSERT INTO pagos (pedido_id, metodo_pago, monto, estado_pago) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pago.getPedidoId());
            stmt.setString(2, pago.getMetodoPago());
            stmt.setDouble(3, pago.getMonto());
            stmt.setString(4, pago.getEstadoPago());

            stmt.executeUpdate();
        } finally {
            Database.close(stmt, conn);
        }
    }
}

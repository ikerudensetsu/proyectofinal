/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import modelo.Pedido;
import Conexión.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Map;
import modelo.Producto;

public class PedidoDAO {

    public int guardarPedido(Pedido pedido) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int pedidoId = 0;

        try {
            conn = Database.getConnection();
            String sql = "INSERT INTO pedidos (email_usuario, total) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pedido.getEmail());
            stmt.setDouble(2, pedido.getTotal());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

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
            }
        } finally {
            Database.close(rs, stmt, conn);
        }

        return pedidoId;
    }
}
package DAO;

import Connection.Database;
import Model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {
    public static ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM productos";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getDouble(4));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static Producto obtenerProducto(int codigo){
        Producto p = null;
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM productos WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    
    public static Producto obtenerProductoPorId(int id) {
        Producto producto = null;

        try {
            Connection cn = Database.getConnection();
            String sql = "SELECT * FROM productos WHERE id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"), rs.getDouble("precio"));
            } else {
                producto = new Producto(id, "Producto no encontrado", "desconocido", 0.0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }
}

package Model;

import Connection.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestCarrito {
    public static void main(String[] args) {
        ArrayList <Producto> carrito = new ArrayList<>();
        ArrayList <Articulo> articulos = new ArrayList<>();
        
        carrito.add(obtenerProductoPorId(1));
        articulos.add(new Articulo(1, 1));
        carrito.add(obtenerProductoPorId(3));
        articulos.add(new Articulo(3, 1));
        carrito.add(obtenerProductoPorId(5));
        articulos.add(new Articulo(5, 1));
        
        for (int i = 0; i < carrito.size(); i++) {
            System.out.println("producto: " + carrito.get(i).getNombre());
        }
        
        //carrito.remove(obtenerProductoPorId(3));
        
        //carrito.remove(1);
        
        for (int i = 0; i < carrito.size(); i++) {
            if (carrito.get(i).getId() == 3) {
                carrito.remove(i);
                break;
            }
        }
        
        System.out.println("removed: " + obtenerProductoPorId(3).getNombre());
        
        for (int i = 0; i < carrito.size(); i++) {
            System.out.println("producto: " + carrito.get(i).getNombre());
        }
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
                producto = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getDouble("precio")
                );
            } else {
                producto = new Producto(id, "Producto no encontrado", "desconocido", 0.0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }
}
